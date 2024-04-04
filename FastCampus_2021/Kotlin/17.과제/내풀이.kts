import java.lang.reflect.Constructor

// 1.
class Calculator {
    var result: Int = 0
        set(value) {
            field = value
            println("중간 계산 값 : " + field)
        }

    fun plus(input: Int) {
        result += input
    }

    fun minus(input: Int) {
        result -= input
    }

    fun multiply(input: Int) {
        result *= input
    }

    fun devide(input: Int) {
        result /= input
    }
}

var calculator = Calculator()
calculator.plus(10)
println(calculator.result)

// 2.
class Calculator2 constructor(var initValue: Int = 0) {
    var result: Int

    init {
        this.result = initValue
    }

    fun calculate(operater: Char, inputValue: Int) {
        when (operater) {
            '+' -> this.result += inputValue
            '-' -> this.result -= inputValue
            '/' -> this.result /= inputValue
            '*' -> this.result *= inputValue
            else -> println("잘못된 연산")
        }

    }
}

val calculator2 = Calculator2(5)
calculator2.calculate('+', 10)
println(calculator2.result)
