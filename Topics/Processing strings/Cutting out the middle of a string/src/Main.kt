fun main() {
    val input = readLine()!!
    var output = ""
    if ((input.length % 2) == 0) {
        output = input.substring(0, (input.length / 2 - 1)) + input.substring((input.length / 2 + 1))
    } else {
        output = input.substring(0, (input.length / 2)) + input.substring((input.length / 2 + 1))
    }

    println(output)
}