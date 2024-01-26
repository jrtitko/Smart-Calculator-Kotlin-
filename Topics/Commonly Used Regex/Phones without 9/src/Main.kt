fun main() {

    val regex = """(\(?[0-8]{3}\)?-?[0-8]{3}-?[0-8]{4})""".toRegex()

    regex.findAll(readln()).forEach { println(it.value) }
}