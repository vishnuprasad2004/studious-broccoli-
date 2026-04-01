package main

import (
	"fmt" // full form -> format
	"math"
)  


func main() {
    const n = 120292910
    const d = 20e3 / n
    fmt.Println(d)
    // fmt.Println(int32(d))
    var a int
    b := 30
    for {
        fmt.Println("Enter a number: ")
        i, err := fmt.Scan(&a)
        fmt.Println("Number of items scanned:", i)
        if err != nil {
            fmt.Println("Error reading input:", err)
            break
        }
        if a == 0 {
            fmt.Println("Exiting...")
            break
        }
        message := fmt.Sprintf("You entered: %d", a)
        fmt.Println(message)
    }
    fmt.Println(a+b)
    fmt.Println(math.Sin(math.Pi))
}