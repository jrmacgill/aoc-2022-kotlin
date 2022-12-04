fun main(args: Array<String>) {
    val loss = 0
    val win = 6
    val draw = 3
    val values =mapOf("X" to 1, "Y" to 2, "Z" to 3)
    val scores = mapOf("A X" to draw, "A Y" to win, "A Z" to loss,
                       "B X" to loss, "B Y" to draw, "B Z" to win,
                       "C X" to win, "C Y" to loss, "C Z" to draw)
       val invert = scores.toList().groupBy { pair -> pair.second }
        .mapValues { entry ->
            entry.value.map { it.first }
        }


    val lines = object {}.javaClass.getResourceAsStream("dayTwo.txt")?.bufferedReader()?.readLines()
    var total = 0
    lines?.forEach {
        val winings = scores[it]?:0
        val value = values[it.split(" ")[1]]
        total += winings + ( value ?:0)
    }
    println(total)

    // part 2
    total = 0
    val outcome = mapOf("X" to loss, "Y" to draw, "Z" to win)
    lines?.forEach {
        val outcome = outcome[it.split(" ")[1]]
        val move = it.split(" ")[0]
        val action = invert[outcome]?.toList()?.first { s -> s[0] == move[0] }
        val winings = scores[action]
        val value = values[action!!.split(" ")[1]]
        total += winings?.plus(( value ?:0)) ?: 0

       // val winings = scores[it]?:0
       // val value = values[it.split(" ")[1]]
       // total += winings + ( value ?:0)
    }
    print(total)


}