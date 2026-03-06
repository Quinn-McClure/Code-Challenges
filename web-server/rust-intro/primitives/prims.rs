fn main() {
    /* Primites */
    
    //type annotated vars
    let logical: bool = true;
    let a_float: f64 = 23.5666666;
    let an_integer = 5i32;

    //default typing
    let default_float = 3.0;
    let default_int = 7;

    //inferred type
    let mut inferred = 12;
    inferred = 4294967296i64;

    //variables can be overwritten when shadowing
    let mut mutable = 12;
    mutable = 21; // a mutable's variable value can be changed, its type cannot

    let mutable = true //shadowing, only way to change a mutable value

    //compound types
    let my_array: [i32; 5] = [1, 2, 3, 4, 5];

    let my_tuple = (5u32, 1u8, true, -5.04f32)

    /* Literals and operators */

    //int addition
    println!("1 + 2 = {}", 1u32 + 2)

    //int subtraction
}