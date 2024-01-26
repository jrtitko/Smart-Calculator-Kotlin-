import java.util.*

fun findSerialNumberForGame(listGames: List<String>) {

    val scanner = Scanner(System.`in`)
    val gameName = scanner.nextLine()!!

    listGames.forEach {
        if ("\\b$gameName@.*".toRegex().matches(it)) {
            val nodes = it.split("@".toRegex())
            println("Code for Xbox - ${nodes[1]}, for PC - ${nodes[2]}")
        }
    }

}
