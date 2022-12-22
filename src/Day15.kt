import utils.*
import java.lang.Math.max
import java.lang.Math.min


class Day15 {

    fun go() {
        var lines =readInput("day15_test")
        //var lines =readInput("day15")
        val row = 2000000
        //val row = 10

        val data = mutableListOf<List<Int>>()
        val sensors = mutableMapOf<Point, Point>()
        println("sizing")
        lines.forEach{
            var values = it.extractAllIntegers()
            data.add(values)
            sensors[Point(values[0], values[1])] = Point(values[2], values[3])
        }


        var minx = data.minOf { i -> min(i[0], i[2])}
        var miny = data.minOf { i -> min(i[1], i[3])}
        var maxx = data.maxOf { i -> max(i[0], i[2])}
        var maxy = data.maxOf { i -> max(i[1], i[3])}

        //val row = 2000000
        var count = 0
        for (x in minx-100000000 .. maxx+10000000) {
            var test = Point(x, row)
            var found = false
            sensors.forEach{p ->
                if (p.value != test && p.key != test) {
                    var dist = test.manhattanDistanceTo(p.key)
                    var range =  p.key.manhattanDistanceTo(p.value)
                    if (dist <= range) {
                        found = true
                        //return@forEach
                    }
                } else {
                   // found = false
                    //
                }
            }
            if (found) {count++}
        }
        println("answer is")
        println(count)
       /* println(data)
        println(minx)
        println(miny)
        println("make grid")
        var grid = MutableGrid(maxx-minx+1,maxy-miny+1) { _ -> '.' }

        var sensor = Point(0,0)
        var beacon = Point(0,0)

        println("process")
        data.forEach{it ->
            run {
                sensor = Point(it[0] - minx, it[1] - miny)
                beacon = Point(it[2] - minx, it[3] - miny)

                grid.forAreaIndexed { p, v ->
                    if (sensor.manhattanDistanceTo(p) <= sensor.manhattanDistanceTo(beacon)) {
                        grid.set(p, '#')
                    }
                }
                //grid= grid.mapValuesIndexed { pair, c ->  if (sensor.manhattanDistanceTo(pair) <= dist) '#' else c }.toMutableGrid()

               // println()
               // println(temp.formatted())

                grid.set(Point(it[0] - minx, it[1] - miny), 'S')
                grid.set(Point(it[2] - minx, it[3] - miny), 'B')
            }
        }

       // println(grid.formatted())
        println("done")
        println(grid[2000000-miny].count{ it -> it != '.' && it != 'B'})
*/
    }
}


fun main() {
    Day15().go()
}