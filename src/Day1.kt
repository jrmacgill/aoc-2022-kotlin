fun main(args: Array<String>) {
    println("What is your name?")
    val lines = object {}.javaClass.getResourceAsStream("dayOne.txt")?.bufferedReader()?.readLines()
    var count = 0;
    var max = -1;
    //var maxIndex = -1;
    var index = 0;
    var counts = mutableListOf<Int>()
    lines?.forEach {
        if (it.isEmpty()) {
            counts.add(count)
            count = 0

        } else {
            val num = it.toInt()
            count += num
        }

        //println(num)
    }
    println(counts.sortedDescending().slice(0..2).sum())

}