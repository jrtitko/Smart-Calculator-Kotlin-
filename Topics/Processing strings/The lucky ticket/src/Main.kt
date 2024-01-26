import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val input = scanner.nextLine()

    var startVal = 0
    var endVal = 0

    input.substring(0, 3).forEach { startVal += it.toString().toInt() }
    input.substring(3).forEach { endVal += it.toString().toInt() }

    println( if (startVal == endVal) "Lucky" else "Regular" )
}