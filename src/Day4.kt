fun main(args: Array<String>) {
    val lines = readInput("dayFour")
    var count1 = 0
    var count2 = 0
    lines.forEach {
        val assignments = it.split(",")
        val a1 = assignments[0].split("-")
        val a2 = assignments[1].split("-")
        val r1 = a1[0].toInt() .. a1[1].toInt()
        val r2 = a2[0].toInt() .. a2[1].toInt()
        val test1 = r1.all { r2.contains(it) } || r2.all { r1.contains(it) }
        val test2 = r1.any { r2.contains(it) }
        if (test1) count1++
        if (test2) count2++
    }
    println(count1)
    println(count2)
}