open Microsoft.FSharp.Compiler.SimpleSourceCodeServices
open System.IO

let dna = 
    seq [
         "open Microsoft.FSharp.Compiler.SimpleSourceCodeServices";
         "open System.IO";
         "";
         "let dna =";
         "    seq [";
         "    ]";
         "";
         "[<EntryPoint>]";
         "let main argv =";
         "   let version = 0";
         "   let quote = string(char(34))";
         "   let semicolon = string(char(59))";
         "   let newline = string(char(10))";
         "   let tab = string(char(9))";
         "   let head = ";
         "       dna |> Seq.take 4 ";
         "   let whole = ";
         "       dna ";
         "       |> Seq.map(fun s -> tab + quote + s + quote + semicolon)";       
         "   let tail =  ";
         "       dna ";
         "       |> Seq.skip 5";
         "       |> Seq.tail";
         "   let code =";
         "       Seq.concat[head; seq[string(char(93))]; whole; tail]";
         "       |> String.concat newline";
         "";
         "   System.Console.WriteLine(code)";
         "   0"; ]

[<EntryPoint>]
let main argv = 
    let version = 0
    let quote = string(char(34))
    let semicolon = string(char(59))
    let newline = string(char(10))
    let tab = string(char(9))
    let head = 
        dna |> Seq.take 5 
    let whole = 
        dna 
        |> Seq.map(fun s -> tab + quote + s + quote + semicolon)        
    let tail =  
        dna 
        |> Seq.skip 5
        |> Seq.tail
    let code =
        Seq.concat[head; whole; seq[string(char(93))]; tail]
        |> String.concat newline
    
    System.Console.WriteLine(code)          
    0 