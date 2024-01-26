fun main() {
    val text = readLine()!!
    val regexColors = "#[0-9a-fA-F]{6}\\b".toRegex()

    val matchResults = regexColors.findAll(text)
    matchResults.forEach {
        println(it.value)
    }
}