//List
var numbers = listOf<Int>(1, 2, 3)
println(numbers[0])
println(numbers.get(1))
println(numbers.indexOf(2))
println(numbers.last())
println(numbers.first())

val numbers2 = mutableListOf<Int>(1, 1, 1, 2, 3)
numbers2.add(50)
println(numbers2)
numbers2.removeAt(0)
println(numbers2)
numbers2.addAll(2, listOf<Int>(100, 100)) // 2번째부터 순서대로 넣는다(뒤에껀 순서가 밀린다)
println(numbers2)


//Set
val numbers3 = setOf<Int>(1, 1, 1, 3, 3, 4)
println(numbers3.contains(5))
println(numbers3.containsAll(setOf<Int>(1, 3)))

val numbers4 = mutableSetOf<Int>(1, 1, 1, 3, 3, 4)
numbers4.remove(10)
println(numbers4)

//Map
val numbers5 = mapOf<Int, String>(1 to "one", 2 to "two")
println(numbers5.keys)
println(numbers5.values)
println(numbers5.size)
println(numbers5.get(1))
println(numbers5[2])
println(numbers5.getOrDefault(1, "dafault"))
println(numbers5.getOrDefault(3, "dafault"))
println(numbers5.get(3)) // 존재 하지 않는 키로 값을 찾으려고 하면 null이 나온다.


val numbers6 = mapOf<Int, String>(Pair(1, "one"), Pair(2, "two"))


Iterable {  }