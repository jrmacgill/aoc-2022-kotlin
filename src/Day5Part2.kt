import java.io.File

fun main(args: Array<String>) {
    val input = File("src/dayFive.txt").readText()
    val parts = input.split("\r\n\r\n")
    val crates = parts[0]
    val rows = crates.split("\r\n")

    val cols = rows.last().last().digitToInt()
    val moves = parts[1]

    var stacks = Array<ArrayDeque<String>>(cols){ ArrayDeque() }

    val boxes = rows.subList(0,rows.size-1).reversed()

    boxes.forEach() {
        val chunks = it.chunked(4).mapIndexed {idx, a->  if (a.strip().isNotEmpty()) {stacks[idx].add(a)}} //a[1]}

    }


    moves.lines().forEach() {
        val fragments = it.split(" ")
        val num = fragments[1].toInt()
        val from = fragments[3].toInt()
        val to = fragments[5].toInt()
        println("move " + from + " " + to + " " + num +" ")
        val temp = Array<String>(num){it -> ""}
        for (i in 0..num-1) {
            temp[i] = stacks[from - 1].removeLast()

        }
            stacks[to-1].addAll(temp.reversed())


    }
    val tops = stacks.map { q -> q.last() }


    print(tops)





    // print(stacks)
    // print(moves)

//    print(stacks.last())

    var count = 0
    var max = -1
    var index = 0

    //lines?.forEach {

    //}


}