package main

import (
	"fmt"
)

// arrays are fixed length, same size, same type, indexed collection of elements, continuous memory allocation, value type, zero value is 0 for int, 0.0 for float, "" for string, false for bool
// slices are dynamic length, same size, same type, indexed collection of elements, continuous memory allocation, reference type, zero value is nil
// maps are unordered collection of key-value pairs, dynamic length, same type for keys and values, reference type, zero value is nil

func main() {
	var intArr = [...]int32{1,2,3} 

	var intArr2 [3]int32 = [3]int32{1,2,3}

	fmt.Println(intArr2)
	fmt.Println(&intArr)
	fmt.Println(&intArr[0])
	fmt.Println(&intArr[1])
	fmt.Println(&intArr[2])
	
	var intSlice []int32 = []int32{1,2,3}
	fmt.Printf("The length is %d and the capacity is %d\n", len(intSlice), cap(intSlice))
	intSlice = append(intSlice, 10)
	
	fmt.Printf("The length is %d and the capacity is %d\n", len(intSlice), cap(intSlice))
	// [1 2 3] -> [1 2 3 10 * *]

	fmt.Println(intSlice)

	var intSlice3 []int32 = make([]int32, 3, 5) // length is 3, capacity is 5
	fmt.Printf("The length is %d and the capacity is %d\n", len(intSlice3), cap(intSlice3))
	intSlice3 = append(intSlice3, 10)
	fmt.Printf("The length is %d and the capacity is %d\n", len(intSlice3), cap(intSlice3))
	fmt.Println(intSlice3)

	var myMap map[string]uint8 = map[string]uint8{
		"Vishnu":10,
		"Fernando": 20,
		"Jessica": 30,
	}
	fmt.Println(myMap)
	fmt.Printf("The value for key Vishal is: %d\n", myMap["Vishal"]) // zero value is 0 for uint8, so it will print 0
	fmt.Printf("The value for key Vishnu is: %d\n", myMap["Vishnu"]) // it will print 10


	var num, ok = myMap["Vishal"]
	if ok {
		fmt.Printf("The value for key Vishnu is: %d\n", num)
	} else {
		fmt.Println("Key not found")
	}

	delete(myMap, "Adam") // key may or may not exist 
	fmt.Println(myMap)

	fmt.Println("===========================================")

	for key, value := range myMap {
		fmt.Printf("Key: %s, Value: %d\n", key, value)
	}

	for key := range myMap {
		fmt.Printf("Key: %s\n", key)
	}

	for i:=0;i<len(intSlice);i++ {
		fmt.Printf("Index: %d, Value: %d\n", i, intSlice[i])
	}

	var i int = 0
	for i<10 {
		i++
	}

	// NO WHILE LOOP IN GO, only for loop with different forms


}