import utils.*
import java.lang.Integer.max
import java.lang.Math.min

class Day14 {

    val grid = MutableGrid(100000,1000) { _ -> '.' }

    fun Point.down() : Point {
        return Point(this.x, this.y+1)
    }
    fun Point.left() : Point {
        return Point(this.x-1, this.y+1)
    }
    fun Point.right() : Point {
        return Point(this.x+1, this.y+1)
    }
    val start = Point(500,0)
    var active = start
    var count = 1

    var maxY = 0;
    fun parse(line : String) {
        line.split("->").windowed(2).forEach {
            println(it)
            var a = it[0].split(",").map { a -> a.trim().toInt() }
            var b = it[1].split(",").map { a -> a.trim().toInt() }
            for (x in min(a[0], b[0])..max(a[0], b[0])) {
                for (y in min(a[1], b[1])..max(a[1], b[1])) {
                    println("x " + x + " y " + y)
                    maxY = max(y, maxY)
                    grid.set(Point(x, y), '#')
                }
            }

        }
    }

    fun step() {
        when {
            (grid.get(active.down()) == '.') -> { active = active.down(); println("air")}
            (grid.get(active.left()) == '.') -> { active = active.left(); println("air to left")}
            (grid.get(active.right()) == '.') -> { active = active.right(); println("air to right")}
            (active == start) -> throw Exception("full")
            else -> {grid.set(active, 'o'); count++; active=start}
        }
    }

    fun go() {
        grid.set(start, '+')
        var lines =readInput("dayForteen")
        lines.forEach{
            parse(it)
        }
        maxY += 2
        parse("0,$maxY -> 10000,$maxY")
        try {
            while(true) {
                step()
            }
        } finally {
            println(grid.formatted())
            println(count-1)
        }

    }
}




fun main() {
    Day14().go()
}