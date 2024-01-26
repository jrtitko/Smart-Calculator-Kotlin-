fun main() {
    val text = readln()

    Regex("""(\b\w*pa\w*)""").findAll(text).forEach { println(it.value) }

}