package main

import (
	"fmt"
	"strings"
)

func main() {
	var str string = "résumé"
	var indexed = str[0]
	fmt.Printf("%v %T\n", indexed, indexed)

	for i, v := range str {
		fmt.Println(i, v)
	}

	var str2 = []rune("résumé")
	fmt.Printf("%v %T\n", str2[0], str2[0])
	for i, v := range str2 {
		fmt.Println(i, v)
	}

	var char rune = 'é'
	fmt.Printf("%c %T\n", char, char)

	var strSlice = []string{"v", "i", "s", "h", "a", "l"}
	var strBuilder strings.Builder
	for _, v := range strSlice {
		strBuilder.WriteString(v)
	}
	var result string = strBuilder.String()
	fmt.Println(result)
}