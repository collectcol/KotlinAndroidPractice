//1번
val A: Int = 10
val B: Int = 10
val C: Boolean = if (A == B) true else false
println(C)
// 자료형이 다르면 비교연산자를 사용할수 없다

// 2번
val AA: Int = 10
val BB: Int = AA * 2

//3번
fun checkGrade(score: Int): String {
//    if (score >= 90) return "A학점"
//    else if (score >= 80) return "B학점"
//    else if (score >= 70) return "C학점"
//    else return "F학점"
    when (score) {
        in 90..100 -> return "A학점"
        in 80..89 -> return "A학점"
        in 70..70 -> return "A학점"
        else -> return "A학점"
    }
}

val reuslt: String = checkGrade(90)
println(reuslt)

// 4번
fun gradeTest(correctCount: Int): Int {
    var result: Int = correctCount * 5
    return result
}

fun gradeTest2(correctCount: Int): Int = correctCount * 5
println(gradeTest(10))
println(gradeTest2(10))


// 5번
fun plustTwoNumbers(firstNum: Int?, secondNum: Int?): Int {
    // 함수에서 받은 인자 firstNum, secondNum -> val 이다
//    if (firstNum == null) {
//        firstNum = 0
//    }
//    if (secondNum == null) {
//        secondNum = 0
//    }
//    return firstNum + secondNum

    // 정답
    val first: Int = if (firstNum == null) 0 else firstNum
    val second: Int = if (secondNum == null) 0 else secondNum
    return first + second

//    val firstNum :Int? =  if (firstNum == null) 0 else firstNum
//    val secondNum :Int? =  if (secondNum == null) 0 else secondNum
//    return first + second
    // 함수안에서 똑같은 이름을 갖고 있는 다른 두 변수가 있으면 안된다
}
println(plustTwoNumbers(null, null))


// 내가 한거
1.
var a: Int = 5
var b: Int = 6
var c: Boolean = if (a == b) true else false

2.
var a: Int = 2
var b: Int = a * 2

3.
when (점수)
in 90<=...<=100 -> return "A"
in 80<=...<=89 -> return "B"
in 70<=...<=79 -> return "C"
else -> return "F"

4.
fun score(점수: Int): Int = 점수 * 5

5.
fun plus(a: Int?, b: Int?): Int? = if (a == null || b == null) 0 else a!! + b!!


