import java.math.BigInteger
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val a = scanner.nextBigInteger()
    val b = scanner.nextBigInteger()

    println(a.add(b).plus(a.minus(b).abs()).divide(BigInteger.TWO))
}