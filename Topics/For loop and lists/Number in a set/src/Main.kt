import java.util.LinkedList

fun main() {
    var mylist = LinkedList<Int>()
    repeat(readln().toInt()) {
        mylist.add(readln().toInt())
    }
    if (readln().toInt() in mylist) {
        println("YES")
    } else {
        println("NO")
    }
}