import utils.*
import java.lang.Math.abs
import java.lang.Math.min

class Day12 {

    var dir = listOf<Point>(Point(-1,0), Point(1,0), Point(0,1),Point(0,-1))

    fun go() {
        //private val rawInput: List<String>
        val grid: List<List<Char>> by lazy { readInput("dayTwelve").map { it.toList() }}

        var start = grid.searchIndices('S').first()



        var dist = path(grid, start)
        println(dist)
        var best = dist
        grid.searchIndices('a').forEach { p -> best = min(path(grid, p), best) }

    }

    fun path(grid: List<List<Char>>, start : Point) : Int {


        var end = grid.searchIndices('E').first()
        var dist = grid.mapValues { c -> 9999 }.toMutableGrid()
        var heights = grid.mapValues { c -> when(c) {
            'S' -> 'a'.code - 'a'.code
            'E' -> 'z'.code - 'a'.code
            else -> c.code - 'a'.code
        } }
        //println(heights)
        var prior = grid.mapValues { c -> Point(-1,-1) }.toMutableGrid()

        var q = mutableListOf<Point>()

        grid.mapValuesIndexed() { p,_ -> q.add(p) }
        var u = start
        dist[start] = 0

        while (q.isNotEmpty()) {

            u = q.minBy { x -> dist[x] }
            q.remove(u)

            var current = heights[u]
            var neighbors =
                dir.map { p -> p + u }.filter { p -> (kotlin.math.abs(heights.getOrNull(p) ?: 1000) - current) < 2 }
            for (v in neighbors) {
                var alt = dist[u] + 1
                if (alt < dist[v]) {
                    dist[v] = alt
                    prior[v] = u
                }
            }

        }

        println(dist[end])
        return dist[end]
    }
}

fun main() {
    Day12().go()
}