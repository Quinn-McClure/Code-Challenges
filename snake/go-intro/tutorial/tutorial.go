package main

import "fmt"

func main() {
	fmt.Println("Hello World")

	// -- Variables --

	//long version of variable declaration
	// var <name> <data-type> = <value>
	var x int = 10
	x = 30 //reassign

	//short variable declaration, auto detect type
	// <name> := <value>
	y := 70

	// -- Conditionals --
	// if <condition> {} else {}

	if y == 20 {

	} else if x == 35 {

	} else {

	}

	// -- Arrays & Slices --

	// arrays are fixed structures
	// var <name> <[size]><data-type>
	var z [5]int
	z[0] = 3

	u := [5]int{1, 2, 3, 4, 5}

	// slices can be changed, dynamic array
	e := []int{1, 2, 3, 4, 5}
	e = append(e, 6)

	//-- Maps --

}