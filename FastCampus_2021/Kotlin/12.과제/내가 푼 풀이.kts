// 1.
fun firstMethod(i: Int): Unit {
    for (a in 1..i) {
        println("주어진 문자열 " + a + "번 반복")
    }
}
firstMethod(3)

// 2.
fun secondMethod(i: Int): Unit {
    var j = 0
    for (a in 1..i) {
        j = j + a
    }
    println("2번 : 주어진 숫자" + i + "까지의 합은 " + j)
}
secondMethod(5)

// 3.
fun thirdMethod() {
    var j = 0
    for (a in 1..100) {
        if (a%7 == 0) j += a
        if (a == 100) println("3번 : " + j)
    }
}
thirdMethod()

// 4.
fun forthMethod(i: Int){
    if (i <= 100) println(i + 1)
    else if (i == 100) println("4번 : " + i)
}
forthMethod(100)