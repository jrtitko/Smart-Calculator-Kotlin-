fun main() {
    repeat(4) { println("\\d".toRegex().matches(readln())) }
}