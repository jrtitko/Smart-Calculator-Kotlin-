class Patient(val name: String) {

}

fun Patient.say() = println("Hello, my name is $name, I need a doctor.")