import java.lang.Integer.max

class DayEight {

}

fun testTree(i : Int, j : Int, data : Array<IntArray> ) : Int {
    val height = data[i][j]
    var result = false
    for (x in -1..1  ) {
        for (y in -1 .. 1) {
            if (x == 0 && y == 0) continue
            if (x != 0 && y != 0) continue
            result = result || testDir(i,j,x,y,height, data)
        }
    }
    return if(result) 1 else 0
}

fun testDir(px : Int, py : Int, dx : Int, dy : Int, height : Int, data: Array<IntArray>) : Boolean {
    var x = px+dx
    var y = py+dy
    while( x in 0 .. data.size-1 && y in 0 .. data.size-1) {

        if (data[x][y] >= height) return false
        x+=dx
        y+=dy
    }
    return true
}

fun testTreeScore(i : Int, j : Int, data : Array<IntArray> ) : Int {
    val height = data[i][j]
    var result = 1
    for (x in -1..1  ) {
        for (y in -1 .. 1) {
            if (x == 0 && y == 0) continue
            if (x != 0 && y != 0) continue
            result = result * testDirScore(i,j,x,y,height, data)
        }
    }
    return result
}


fun testDirScore(px : Int, py : Int, dx : Int, dy : Int, height : Int, data: Array<IntArray>) : Int {

    var x = px+dx
    var y = py+dy
    var distance = 0
    while( x in 0 .. data.size-1 && y in 0 .. data.size-1) {
        distance++
        val tree = data[x][y]
        if (tree >= height) return distance
        x+=dx
        y+=dy

    }
    //println("found one")
    return distance
}


fun main() {
    val day = DayEight()

    val lines = readInput("dayEight")
    val size = lines[0].length
    val data = Array(size) { IntArray(size) }

    lines.forEachIndexed { i, row -> row.forEachIndexed { j, tree -> data[i][j] = tree.digitToInt() } }
    var visible = 0

    data.forEachIndexed{ i, row -> row.forEachIndexed{ j, tree -> visible += testTree(i, j, data) }}
    println(visible)

    var best = 0
    data.forEachIndexed{ i, row -> row.forEachIndexed{ j, tree -> best = max(best, testTreeScore(i, j, data)) }}
    println(best)
}
