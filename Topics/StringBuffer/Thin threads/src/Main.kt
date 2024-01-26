import kotlin.concurrent.thread

fun addNumbers(numbers: StringBuffer) {
    for (i in 0 until 100_000) numbers.append(i.toString().first())
}

fun main() {
    val numbers = StringBuffer(200_000)
    val thread = thread(start = false, name = "Thread 1") {
        addNumbers(numbers)
    }

    thread.start()
    addNumbers(numbers)

    thread.join()

    println(numbers.length)
}