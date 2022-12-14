fun main(args: Array<String>) {
    val lines = object {}.javaClass.getResourceAsStream("dayOne.txt")?.bufferedReader()?.readLines()
    var count = 0
    var max = -1
    var index = 0
    var counts = mutableListOf<Int>()
    lines?.forEach {
        if (it.isEmpty()) {
            counts.add(count)
            count = 0
        } else {
            val num = it.toInt()
            count += num
        }
    }
    println(counts.sortedDescending().take(3).sum())

}