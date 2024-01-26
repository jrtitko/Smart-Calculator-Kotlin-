fun main() {    
    val numbers = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
    // Do not touch lines above
    // Write only exchange actions here.

//    var swap = numbers.first()
//    numbers[0] = numbers.last()
//    numbers[numbers.lastIndex] = swap

    numbers[0] = numbers[numbers.lastIndex].also {
        numbers[numbers.lastIndex] = numbers[0]
    }

    // Do not touch lines below
    println(numbers.joinToString(separator = " "))
}