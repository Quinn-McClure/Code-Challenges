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
	// make(map[<key-type>]<value-type>)
	// key, value pairs

	a := make(map[string]int)
	a["test"] = 10

	delete(a, "test")

	//-- Loops --
	// for <declaration>; <condition>; <update> {}

	//standard for loop
	for i := 0; i < 10; i ++ {

	}

	//while loop
	b := 0

	for b < 10 {
		b ++
	}

	//endless loop
	for {

	}

}

// -- Functions --
// func <name>(<name> <data-type>) <return-type> {}
func add(a int, b int) int{
	return a + b
}