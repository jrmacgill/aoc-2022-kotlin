import utils.*




class Day15_2 {
    val ranges = mutableMapOf<Point, Int>()
    fun contained(min : Point, max : Point) : Boolean {
        ranges.forEach{
            val dist = it.value
            if (it.key.manhattanDistanceTo(min) <= dist && it.key.manhattanDistanceTo(max) <=dist && it.key.manhattanDistanceTo(Point(min.x, max.y)) <= dist && it.key.manhattanDistanceTo(Point(max.x, min.y)) <= dist) {
                return true
            }
        }
        return false;
    }


    fun doChunk(min : Point, max : Point) {
        if (contained(min, max)) {
        } else {

            if (min.x == max.x || min.y == max.y) {

            } else {
                if (min.manhattanDistanceTo(max) < 3) {
                    for (x in min.x .. max.x) {
                        for (y in min.y .. max.y) {
                            if (!contained(Point(x,y),Point(x,y))) {
                                println("FOUND!!" + Point(x,y))
                            }
                        }
                    }
                } else {
                    var mid = Point((min.x + max.x) / 2, (min.y + max.y) / 2)
                        doChunk(Point(min.x, min.y), Point(mid.x, mid.y))
                        doChunk(Point(mid.x, min.y), Point(max.x, mid.y))
                        doChunk(Point(min.x, mid.y), Point(mid.x, max.y))
                        doChunk(Point(mid.x, mid.y), Point(max.x, max.y))
                }
            }



        }

    }

    fun go() {
     //   var size = 20
     //   var lines =readInput("day15_test")
        var lines =readInput("day15")
        val size = 4000000

        val data = mutableListOf<List<Int>>()

        val sensors = mutableMapOf<Point, Point>()
        println("sizing")
        lines.forEach{
            var values = it.extractAllIntegers()
            data.add(values)
            var s = Point(values[0], values[1])
            var b = Point(values[2], values[3])
            sensors[s] = b
            ranges[s] = s.manhattanDistanceTo(b)
        }

        doChunk(Point(0,0), Point(size,size))


    }





}

fun main() {
    Day15_2().go()
}