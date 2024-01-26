package calculator

import java.math.BigDecimal
import java.math.BigInteger
import java.util.Scanner
import javax.management.InvalidAttributeValueException
import kotlin.math.pow

private const val PLUS = "+"
private const val MINUS = "-"
private const val MULTIPLY = "*"
private const val DIVIDE = "/"
private const val POWER = "^"

private val functionValidationRegex = ".*\\s*=\\s*.*".toRegex()
private val variableValidationRegex = "[a-zA-Z]+".toRegex()
private val numberValidationRegex = "[\\-\\+]?\\d+".toRegex()
private val equationValidationRegex = "([a-zA-Z]+|[\\-\\+]?\\d+|\\s[\\-\\+]+\\s|\\s[*\\/]\\s|\\(|\\)|\\^)*".toRegex()
private val commandValidationRegex = "\\B\\/.*".toRegex()

private val functionSplitRegex = "=".toRegex()
private val equationSplitRegex = "((?<=[\\-\\+]\\D)|(?<=\\s[\\-\\+]\\s)|(?<=\\s[*\\/]\\s)|(?<=[\\(\\)\\^])|(?=[\\-\\+]\\D)|(?=\\s[\\-\\+]+\\s+)|(?=\\s[*\\/]\\s+)|(?=[\\(\\)\\^]))".toRegex()

private val operationValidation = "^*/+-"
private val operationPriorities = "32211"
fun operationPriority(op: String) = operationPriorities[operationValidation.indexOf(op)].digitToInt()

fun <T> ArrayDeque<T>.push(element: T) = addLast(element)    // Push on top of the stack
fun <T> ArrayDeque<T>.pop() = removeLastOrNull()             // Pop the top of the stack, null if empty
fun <T> ArrayDeque<T>.peek() = lastOrNull()                  // Peek the top of the stack, null if empty

private var variableMap = mutableMapOf<String, BigInteger>()

fun reducePluses(it: String) = ("\\++".toRegex().matches(it))
fun reduceMinusesOdd(it: String): Boolean {
    val count = it.count { it == '-' }
    return (count == it.length) && ((count % 2) == 1)
}

fun reduceMinusesEven(it: String): Boolean {
    val count = it.count { it == '-' }
    return (count == it.length) && (count % 2 == 0)
}

fun main() {
    val scanner = Scanner(System.`in`)

    while (scanner.hasNextLine()) {
        var inputLine = scanner.nextLine().trim()

        inputLine = inputLine.replace("\\-\\-".toRegex(), "+").replace("\\++-".toRegex(), "-")
        inputLine = inputLine.replace("\\s\\++\\s".toRegex(), " + ")
        
        try {
            when {
                inputLine.isEmpty()                        -> continue
                inputLine == "/exit"                       -> break
                inputLine == "/help"                       -> println("The program calculates an equation")
                inputLine.matches(commandValidationRegex)  -> println("Unknown command")
                inputLine.matches(functionValidationRegex) -> processFunction(inputLine.split(functionSplitRegex, 2))
                inputLine.matches(equationValidationRegex) -> println(processEquation(inputLine))
                else                                       -> println("Invalid expression")
            }
        } catch (e: InvalidAttributeValueException) {
            println(e.message)
        } catch (e: ArithmeticException) {
            println(e.message)
        }
    }
    println("Bye!")
}

fun processFunction(split: List<String>) {
    val variableName = split[0].trim()
    if (!variableName.matches(variableValidationRegex)) throw InvalidAttributeValueException("Invalid identifier")

    if (split[1].trim().matches(equationValidationRegex)) {
        variableMap.put(variableName, processEquation(split[1]))
    } else {
        throw InvalidAttributeValueException("Invalid assignment")
    }
}

fun processEquation(inputLine: String): BigInteger {
    val elements = inputLine.split(equationSplitRegex).filter{ it.isNotBlank() }.map { it.trim() }

    val postfixNotation = convertToPostfix(elements)

    return calculate(postfixNotation)
}

fun convertToPostfix(elements: List<String>): List<String> {

    var stack = ArrayDeque<String>()
    var postfix = mutableListOf<String>()

    elements.forEach {elem ->
        when {
            elem == ")"                                                                                                                                                                         -> {
                while (stack.peek() != "(") {
                    postfix.add(stack.pop() ?: break)
                }
                if (stack.isEmpty()) {
                    throw InvalidAttributeValueException("Invalid expression")
                } else {
                    stack.pop()
                }
            }
            elem == "("                                                                                                                                                                         -> stack.push(elem)
            elem in operationValidation && (stack.isEmpty() || stack.peek() == "(")                                                                                                             -> stack.push(elem)
            elem in operationValidation && operationPriority(elem) > if (stack.isEmpty()) -1 else operationPriority(stack.peek()!!)  -> stack.push(elem)    // Incoming operator has higher precedence than top of stack
            elem in operationValidation && operationPriority(elem) <= if (stack.isEmpty()) 99 else operationPriority(stack.peek()!!) -> {                  // Incoming operator has lower or equal precedence than top of stack
                while (!stack.isEmpty() && (stack.peek() != "(" && operationPriority(elem) <= operationPriority(stack.peek()!!))) {   // Until stack has operator with a smaller precedence or left paren
                    postfix.add(stack.pop() ?: break)
                }
                stack.push(elem)
            }
            else                                                                                                                                                                                -> postfix.add(elem)     // Add operands (numbers and variables)
        }
    }

    while(true) {
        postfix.add(stack.pop() ?: break)
    }

    return postfix
}

fun convertOpToInt(op: String): BigInteger {
    if (op.matches(variableValidationRegex)) {
        if (variableMap.containsKey(op)) return variableMap.get(op)!!  // TODO what to use instead of bangs
        throw InvalidAttributeValueException("Unknown variable")        // This is not correct as it could be an assignment from a function or an identifier. Depends on source.
    } else {
        try {
            return op.toBigInteger()
        } catch (e: NumberFormatException) {
            throw InvalidAttributeValueException("Invalid identifier") // This is not correct as it could be an assignment from a function or an identifier. Depends on source.
        }
    }
}

fun calculate(elements: List<String>): BigInteger {

    var stack = ArrayDeque<BigInteger>()

    elements.forEach { op ->
        when {
            op.matches(numberValidationRegex) -> stack.push(convertOpToInt(op))
            op.matches(variableValidationRegex) -> stack.push(convertOpToInt(op))
            op == PLUS || reducePluses(op) || reduceMinusesEven(op) -> stack.push(stack.pop()!! + stack.pop()!!)
            op == MINUS || reduceMinusesOdd(op) -> {
                val a = stack.pop()!!
                val b = stack.pop()!!
                stack.push(b - a)
            }
            op == MULTIPLY -> stack.push(stack.pop()!! * stack.pop()!!)
            op == DIVIDE -> {
                val a = stack.pop()!!
                if (a == BigInteger("0")) throw ArithmeticException("/ by zero")
                val b = stack.pop()!!
                stack.push(b / a)
            }
//            op == POWER -> { // Power requires a double - cant use BigInteger for power value
//                val a = stack.pop()!!
//                val b = stack.pop()!!
//                stack.push(b.pow(a)))
//            }
            else -> throw InvalidAttributeValueException("Invalid identifier")
        }
    }

    return stack.pop()!!
}