fun calculateRoom(buffer: StringBuffer): Int { // Do not change the function's name
    return buffer.capacity() - buffer.length
}

// Do not change the code below
fun main() {
    val buffer = StringBuffer("Hello, Kotlin!")
    val remainingRoom = calculateRoom(buffer)
    println("Remaining room in the buffer: $remainingRoom")

    buffer.append(" This is Hyperskill!")
    val newRemainingRoom = calculateRoom(buffer)
    println("Updated remaining room in the buffer: $newRemainingRoom")
}