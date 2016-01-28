let fizzBuzz input = 
    match input with
    | _ when input % 3 = 0 && input % 5 = 0 -> "fizzbuzz"
    | _ when input % 3 = 0 -> "fizz"
    | _ when input % 5 = 0 -> "buzz"
    | _ -> input.ToString()

[1 .. 100] |> List.map(fizzBuzz)