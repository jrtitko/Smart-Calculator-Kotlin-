import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val listSize = scanner.nextLine().toInt()
    var elementList = mutableListOf<Int>()
    for (i in 0 until listSize) {
        elementList.add(scanner.nextLine().toInt())
    }
    val p = scanner.nextInt()
    val m = scanner.nextInt()

    println(if (p in elementList && m in elementList) "YES" else "NO")
}