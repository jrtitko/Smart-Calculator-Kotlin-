class Kitty {

    val color: String
    val age: Int

    constructor(age: Int, color: String = "black") {
        this.color = color
        this.age = age
    }

    constructor(color: String, age: Int = 0) {
        this.color = color
        this.age = age
    }
}