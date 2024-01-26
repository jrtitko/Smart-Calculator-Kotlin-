import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val myString = scanner.nextLine()
    val mySubstring = scanner.nextLine()

    var count = 0

    for (i in 0 .. (myString.length - mySubstring.length)) {
        if (myString.substring(i).startsWith(mySubstring)) count++
    }

    println(count)
}