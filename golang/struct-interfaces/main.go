package main

import (
	"fmt"
)

/**
is go object oriented ?
The official answer from the Go FAQ is "yes and no". While Go allows for an object-oriented style of programming, it intentionally avoids several traditional features found in "classic" OOP languages like Java or C++.

Why Go is considered Object-Oriented
Go supports several core OOP principles using its own unique constructs:
Encapsulation: Achieved through Packages. Visibility is controlled by the capitalization of identifiers: names starting with an uppercase letter are "exported" (public), while those starting with a lowercase letter are private to the package.
Polymorphism: Implemented through Interfaces. Unlike Java, Go interfaces are satisfied implicitly—a type implements an interface simply by having the required methods, without needing an explicit implements keyword.
Methods on Types: You can bind Methods to any user-defined type, including structs and even basic types like integers, which allows data and behavior to be grouped together.

Why Go is NOT a traditional OOP language
Go deliberately excludes features that its designers felt added unnecessary complexity:
No Classes: Go uses Structs to hold state instead of class-based blueprints.
No Type Hierarchy (Inheritance): There is no extends keyword or class hierarchy. Instead, Go follows the principle of Composition over Inheritance.
No Method Overloading: You cannot have multiple methods with the same name but different signatures; every method name must be unique within its scope.
No Constructors/Destructors: Go lacks formal constructor syntax. Instead, it is idiomatic to use "New" factory functions (e.g., NewClient()) to initialize data.
*/

// interface
type employee interface {
	displayInfo() 
}

// kinda inheritance using composition - polymorphism using interfaces
func (e fullTimeEmployee) displayInfo() {
	fmt.Printf("Full Time Employee: %s, Age: %d, Email: %s\n", e.name, e.age, e.email.emailAddress)
}

func (e partTimeEmployee) displayInfo() {
	fmt.Printf("Part Time Employee: %s, Age: %d, Email: %s\n", e.name, e.age, e.email.emailAddress)
}

type fullTimeEmployee struct {
	name        string
	age         int
	credentials credentials
	email
}

type partTimeEmployee struct {
	name        string
	age         int
	email
}

type credentials struct {
	username string
	password string
}

type email struct {
	emailAddress string
}


// useless method
func showInfo(e employee) {
	e.displayInfo()
}

func main() {
	var emp1 = fullTimeEmployee{
		name: "John Doe",
		age:  30,
		credentials: credentials{
			username: "johndoe",
			password: "secret",
		},
		email: email{"John.Doe@example.com"},
	}

	var emp2 = partTimeEmployee{
		name: "Jane Smith",
		age:  25,
		email: email{"Jane.Smith@example.com"},
	}

	showInfo(emp1)
	showInfo(emp2)
	// Anonymous struct
	var emp3 = struct {
		name        string
		age         int
		credentials credentials
		email
	}{
		name: "Jane Smith",
		age:  25,
		credentials: credentials{
			username: "janesmith",
			password: "supersecret",
		},
		email: email{"Jane.Smith@example.com"},
	}

	fmt.Println(emp3)
}
