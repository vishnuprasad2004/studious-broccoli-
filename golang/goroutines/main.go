package main

import (
	"fmt"
	"time"
	// "math/rand"
	"sync"
)

// multithreading in golang is achieved using goroutines, which are lightweight threads managed by the Go runtime.
// Goroutines are created using the "go" keyword followed by a function call. They run concurrently with other goroutines and can communicate using channels.
// Goroutines are more efficient than traditional threads, as they have a smaller memory footprint and are scheduled by the Go runtime, allowing for better performance and scalability.
// Mutex is used to synchronize access to shared resources between goroutines, ensuring that only one goroutine can access the resource at a time to prevent race conditions.

// without goroutines -> 6.5190091s
// with goroutines -> 1.9435297s

var wg =  sync.WaitGroup{}
var m = sync.Mutex{}
var dbData = []string {"id1", "id2", "id3", "id4", "id5", "id6"}
var results = []string {}

func dbCall(i int) {
	var delay float32 = 2000
	time.Sleep(time.Duration(delay)*time.Millisecond)
	fmt.Printf("The result from the database is: %s \n", dbData[i]) 
	m.Lock()
	results = append(results, dbData[i])
	m.Unlock()
	wg.Done()
}

func main() {
	t0 := time.Now()
	for i:=range dbData {
		wg.Add(1)
		go dbCall(i)
	}
	wg.Wait()

	fmt.Println("Total Execution Time: ", time.Since(t0))
	fmt.Println(results)

}