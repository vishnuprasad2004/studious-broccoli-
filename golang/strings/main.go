package main

import (
	"fmt"
)

func main() {
	var str string = "résumé"
	var indexed = str[0]
	fmt.Printf("%v %T\n", indexed, indexed)

	for i, v := range str {
		fmt.Println(i, v)
	}

}