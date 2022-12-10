import java.lang.Integer.max
import kotlin.math.abs

class Day9 constructor(length: Int){


    var head = Pair(0,0)
    var tails = MutableList<Pair<Int,Int>>(length) { Pair(0,0)}
    var width = 0
    var height = 0

    var visited : HashMap<Pair<Int,Int>, Int> = HashMap<Pair<Int,Int>, Int>()

    fun Pair<Int, Int>.add(other: Pair<Int, Int>) : Pair<Int, Int> {
        return Pair(this.first + other.first, this.second + other.second)
    }

    fun Pair<Int, Int>.touches(other: Pair<Int, Int>) : Boolean {
        return abs(this.first - other.first) < 2 && abs(this.second - other.second) < 2
    }

    fun Pair<Int, Int>.twang(head: Pair<Int, Int>) : Pair<Int, Int> {
            return when {
                head.second == this.second -> Pair((head.first+this.first)/2, head.second)
                head.first == this.first -> Pair(head.first, (head.second+this.second)/2)
                head.first > this.first && head.second > this.second -> Pair(this.first + 1, this.second +1)
                head.first > this.first && head.second < this.second -> Pair(this.first + 1, this.second -1)
                head.first < this.first && head.second < this.second -> Pair(this.first - 1, this.second -1)
                head.first < this.first && head.second > this.second -> Pair(this.first - 1, this.second +1)
                else -> error("welp")
            }
    }

    fun store(p : Pair<Int, Int>) {
        visited.putIfAbsent(p, 0)
        visited[p] = visited[p]!!+1
        width = max(width, p.first)
        height = max(height, p.first)
    }

    fun printMap(){
        println()
        for (y in height downTo 0) {
            for (x in 0 .. width) {
                print(visited[Pair(x,y)]?:".")
                print("")
            }
            println()
        }
    }

    fun moveTail(pos: Int) {
        var top = when {
            pos < tails.size-1 -> tails[pos+1]
            else -> head
        }

        if (!tails[pos].touches(top)) {
            tails[pos] = tails[pos].twang(top)
        }
        if (pos == 0) {
            store(tails[pos])
        } else {
            moveTail(pos-1)
        }
    }

    fun parseLine(line : String) {
        var steps = line.split(" ")[1].toInt()
        when (line.split(" ")[0]) {
            "U" -> moveHead(Pair(0,1), steps)
            "D" -> moveHead(Pair(0,-1), steps)
            "R" -> moveHead(Pair(1,0), steps)
            "L" -> moveHead(Pair(-1,0), steps)
            else -> error("Invalid Move")
        }

    }

    fun moveHead(change: Pair<Int, Int>, steps: Int) {
        if (steps == 0) return
        head = head.add(change)
        moveTail(tails.size-1)
        moveHead(change, steps-1)
    }


    fun go() {
        val lines = readInput("dayNine")
        lines.forEach{
            parseLine(it)
        }

        println(visited.keys.size)
    }
}

fun main() {
   Day9(1).go()
   Day9(9).go()
}