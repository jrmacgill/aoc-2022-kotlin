import utils.*
import java.util.*

// INPUT FILE MODIFIED - E and X added for elf and target
class Day24 {

    private val left = 2
    private val right = 4
    private val up = 8
    private val down = 16
    private val wall = 1
    private val empty = 0
    private val player = 32
    private val target = 64
    var exit = Point(0, 0)
    var enter = Point(0,0)

    fun move(initial: Grid<Int>) {
        var grid = initial.toMutableGrid()
        var count = 0
        val targets = mutableListOf<Point>(enter, exit, enter, exit)
        var target = targets.removeFirst()
        var places = listOf(target).asSequence()
        while(true) {
            var next = step(grid)
            var options = places.map { it -> it.directNeighbors()+it }.flatten().toSet()
            if (options.any { p -> p == target }) {
                println("BOUNCE")
                places = listOf(target).asSequence()
                if (targets.isEmpty()) {
                    throw Exception("there and back and there again" + (count+1))
                }
                target = targets.removeFirst()
            } else {
                places = options.filter { next.getOrElse(it, { wall }) == empty }.asSequence()
            }
            count++
            grid = next.toMutableGrid()
        }
    }

    fun step(grid: Grid<Int>): Grid<Int> {
        val next = MutableGrid(grid.width, grid.height) { 0 }

        grid.forAreaIndexed { p, v ->
            if (v and wall > 0) {
                next[p] = wall
            }
            if (v and target > 0) {
                next[p] = target
            }
            if (v and down > 0) {
                if (grid.get(p.down()) == wall) {
                    next[Point(p.x, 1)] = down or next[Point(p.x, 1)]
                } else {
                    next[p.down()] = down or next[p.down()]
                }
            }
            if (v and right > 0) {
                if (grid.get(p.right()) == wall) {
                    next[Point(1, p.y)] = right or next[Point(1, p.y)]
                } else {
                    next[p.right()] = right or next[p.right()]
                }
            }
            if (v and left > 0) {
                if (grid.get(p.left()) == wall) {
                    next[Point(grid.width - 2, p.y)] = left or next[Point(grid.width - 2, p.y)]
                } else {
                    next[p.left()] = left or next[p.left()]
                }
            }
            if (v and up > 0) {
                if (grid.get(p.up()) == wall) {
                    next[Point(p.x, grid.height - 2)] = up or next[Point(p.x, grid.height - 2)]
                } else {
                    next[p.up()] = up or next[p.up()]
                }
            }

        }
        return next.asGrid()

    }

    fun go() {
        var input = "Day24"

        val initial: Grid<Int> by lazy {
            readInput(input).map {
                it.map { it ->
                    when (it) {
                        '#' -> wall
                        '>' -> right
                        '<' -> left
                        '^' -> up
                        'v' -> down
                        'E' -> player
                        'X' -> target
                        else -> 0
                    }
                }
            }
        }
        exit = initial.searchIndices(target).first()
        enter = initial.searchIndices(player).first()
        var grid = initial.toMutableGrid()
        grid.set(exit, empty)
        grid.set(enter, empty)
        showGrid(grid)
        //for (i in 0..10) {
        //  println(i)
       // try {
            move(grid)
        println("done?")
       // } catch (e : Exception) {
       //     println(e.message)
        //}
        //grid = step(grid).toMutableGrid()
        //  }
    }

    private fun showGrid(grid: Grid<Int>) {
        println(grid.formatted { _, it ->
            when (it) {
                wall -> "#"
                left -> "<"
                right -> ">"
                up -> "^"
                down -> "v"
                empty -> "."
                player -> "E"
                target -> "X"
                else -> "@"
            }
        })
    }
}


fun main() {
    Day24().go()
}