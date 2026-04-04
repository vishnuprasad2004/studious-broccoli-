package main

import "fmt"

// Struct for pointer-to-struct demo
type Person struct {
	name string
	age  int
}

// Function using pointer (modifies original value)
func updateValue(x *int) {
	*x = *x + 10
}

// Function returning pointer
func createNumber() *int {
	n := 42
	return &n // safe in Go (escapes to heap)
}

// Function with struct pointer
func updatePerson(p *Person) {
	p.name = "Updated Name" // no need (*p).name
	p.age += 5
}

func main() {

	// 1. Basic Pointer
	x := 10
	var p *int = &x // pointer stores address of x

	fmt.Println("Value of x:", x)
	fmt.Println("Address of x:", &x)
	fmt.Println("Pointer p:", p)
	fmt.Println("Value via pointer:", *p)

	// 2. Modify value using pointer
	*p = 20
	fmt.Println("Updated x via pointer:", x)

	// 3. Nil pointer
	var ptr *int
	fmt.Println("Nil pointer:", ptr)
	// fmt.Println(*ptr) // ❌ panic: nil pointer dereference

	// 4. Passing pointer to function (reference behavior)
	val := 5
	updateValue(&val)
	fmt.Println("After function call:", val)

	// 5. Pointer to pointer
	a := 100
	p1 := &a
	p2 := &p1

	fmt.Println("a:", a)
	fmt.Println("*p1:", *p1)
	fmt.Println("**p2:", **p2)

	// 6. Pointer with struct
	person := Person{"Vis", 21}
	updatePerson(&person)
	fmt.Println("Updated struct:", person)

	// 7. new() keyword
	numPtr := new(int) // allocates memory, default 0
	fmt.Println("Value from new:", *numPtr)

	*numPtr = 55
	fmt.Println("Updated value:", *numPtr)

	// 8. Returning pointer from function
	ptrFromFunc := createNumber()
	fmt.Println("Pointer from function:", *ptrFromFunc)

	// 9. Arrays vs slices (pointer behavior)
	arr := [3]int{1, 2, 3}
	arrCopy := arr
	arrCopy[0] = 100
	fmt.Println("Array original:", arr)     // unchanged
	fmt.Println("Array copy:", arrCopy)

	slice := []int{1, 2, 3}
	sliceCopy := slice
	sliceCopy[0] = 100
	fmt.Println("Slice original:", slice)   // changed
	fmt.Println("Slice copy:", sliceCopy)

	// 10. Pointer comparison
	m := 10
	n := 10
	pm := &m
	pn := &n

	fmt.Println("pm == pn:", pm == pn) // false (different addresses)

	// 11. Dereferencing safely
	if ptr != nil {
		fmt.Println(*ptr)
	} else {
		fmt.Println("Pointer is nil, safe check")
	}
}