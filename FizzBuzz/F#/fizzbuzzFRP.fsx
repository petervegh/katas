//Record for Events
//3 - fizz
//5 - buzz
//15 - fizzBuzz
type FizzBuzzEvent = {label:int; time:System.DateTime}

let createTimerAndObservable runTime tickInterval =
    let timer = new System.Timers.Timer(float tickInterval)
    timer.AutoReset <- true

    let observable = timer.Elapsed

    let task = async {
        timer.Start()
        do! Async.Sleep runTime

        timer.Stop()
    }

    (task,observable)

//partial application
let createTimerAndObservable38sec = createTimerAndObservable 38000

let (timer3,fizzTickEventStream) = createTimerAndObservable38sec 3000
let (timer5,buzzTickEventStream) = createTimerAndObservable38sec 5000

let threeTickStream = 
    fizzTickEventStream |> Observable.map (fun _ -> {label=3; time=System.DateTime.Now})

let fiveTickStream = 
    buzzTickEventStream |> Observable.map (fun _ -> {label=5; time=System.DateTime.Now})

//helper function to decide whatever two events are simoultaneous
let areSimoultaneous (prevEvent:FizzBuzzEvent, nextEvent:FizzBuzzEvent)= 
    System.Math.Abs((nextEvent.time - prevEvent.time).TotalMilliseconds) < 50.0

let splitToSimAndNotSim stream1 stream2 =
    Observable.merge stream1 stream2
    |>  Observable.scan(fun (f,s) n -> (s,n)) ({label=0;time=System.DateTime.Now},{label=0;time=System.DateTime.Now})
    |>  Observable.partition areSimoultaneous

let (simultaneousStream, notSimultaneousStream) = 
    splitToSimAndNotSim threeTickStream fiveTickStream    

//not clean because they contain 0 event and fizzBuzz event as a fizz and a buzz
let (fizzStreamNotClean, buzzStreamNotClean) = 
    notSimultaneousStream
    |> Observable.map(fun (event1,event2) -> event2)
    |> Observable.partition(fun {label=id;time=tt} -> id=3)    

let fizzBuzzStream =
    simultaneousStream
    |> Observable.map(fun (event1,event2) -> event1)
    |> Observable.map(fun event1 -> ({label = 15; time=event1.time}))

//helper function to clean up the not clean streams
let cleanStream notCleanStream =    
    splitToSimAndNotSim notCleanStream fizzBuzzStream
    |> snd  //second element of the tuple, notSimultaneousStream 
    |> Observable.map(fun (event1,event2) -> event1)
    |> Observable.filter(fun {label=id;time=tt} -> (id<>0 && id<>15)) //removing initialEvent and fizzBuzz event

let fizzStream =
    cleanStream fizzStreamNotClean

let buzzStream = 
    cleanStream buzzStreamNotClean

fizzStream
    |> Observable.subscribe (fun (event:FizzBuzzEvent) -> System.Console.WriteLine("fizz {0},{1}", event.label, event.time))
    
buzzStream
    |> Observable.subscribe (fun (event:FizzBuzzEvent) -> System.Console.WriteLine("buzz {0},{1}", event.label, event.time))

fizzBuzzStream 
    |> Observable.subscribe (fun (event:FizzBuzzEvent) -> System.Console.WriteLine("fizzbuzz {0},{1}", event.label, event.time))

[timer3;timer5]
|> Async.Parallel
|> Async.RunSynchronously