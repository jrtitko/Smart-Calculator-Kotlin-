const val SENDER = "From: Ahmed@hyperskill.org\n"
const val RECEIVER = "To: Yusuf@hyperskill.org\n"
const val SUBJECT = "Subject: A Very Gentle Reminder\n"
const val FIRST_LINE = "\"Fix the bug in your code..\n"
const val SECOND_LINE = "or I will fix a bug for you.\""
// Do not change the lines above

fun main() {
    val friendlyMessage = StringBuffer()

    // write your solution here
    friendlyMessage
        .append(SENDER)
        .append(RECEIVER)
        .append(SUBJECT)
        .append(FIRST_LINE)
        .append(SECOND_LINE)

    println(friendlyMessage)
}