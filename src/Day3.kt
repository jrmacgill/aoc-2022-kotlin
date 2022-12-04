fun main(args: Array<String>) {
    val lines = object {}.javaClass.getResourceAsStream("dayThree.txt")?.bufferedReader()?.readLines()
    var total = 0
    lines?.forEach {
        val c1 = it.substring(0, it.length/2)
        val c2 = it.substring(it.length/2, it.length)
        val paired = (c1.toSet().intersect((c2.toSet()))).first()

        total = total + getScore(paired)
    }
    println(total)


    // part 2
    total = 0
    lines?.chunked(3)?.forEach() {
        println(it)
        val badge = it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()).first()
        println(badge)
        total += getScore(badge)
    }
    print(total)
}

private fun getScore(paired: Char): Int {
    var value = 0
    if (paired.isLowerCase()) {
        value = paired.code - 'a'.code + 1
    } else {
        value = paired.code - 'A'.code + 27
    }
    return value
}