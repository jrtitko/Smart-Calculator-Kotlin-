import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val exbibytes = scanner.nextBigInteger()

    println(exbibytes.multiply("9223372036854775808".toBigInteger()))
}