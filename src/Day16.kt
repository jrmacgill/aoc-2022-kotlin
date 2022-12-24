
class Day16 {

    val inputLineRegex = """Valve ([A-Z]*) has flow rate=(\d+); tunnel[s]? lead[s]? to valve[s]? (.*)""".toRegex()

    val data = mutableMapOf<String, Room>()
    val maxTicks = 30
    var best = 0

    class Room(val id : String, val flow : Int, val links : List<String>) {

    }

    fun maxRemain(open : List<String>, tick : Int) : Int {
        var remain = 0
        data.forEach { v ->
            if (!open.contains(v.key)) {
                remain += v.value.flow * (maxTicks-tick)
            }
        }
        return remain
    }


    fun step(room : Room  , tick : Int, open : List<String>, score:Int) {
        if (tick == maxTicks) {
            println("Done with: " + score)
            if (score > best) {
                best = score
            }
            return
        }
        if (open.size == data.size) {
            println("All open with  " + score)
            return
        }
        if (maxRemain(open, tick)+score < best) {
            //println("Crap, giveup")
            return
        }
        if (!open.contains(room.id)) {
            var next =  open.toMutableList()
            next.add(room.id)
            step(room, tick+1,next, score + (room.flow*(maxTicks-(tick+1))))
        }
        room.links.forEach{
           // println("step to " + it.trim())
            step(data[it.trim()]!!, tick+1, open, score)
        }

    }

    fun parse(line : String) {
        val match = inputLineRegex
            .find(line)!!
        println(match)
        val (code, flow, links) = match.destructured
        data[code] = Room(code, flow.toInt(), links.split(","))



       /* val (code : String , flow : Int, links : String) =
            inputLineRegex
            .matchEntire(line)
            ?.destructured
            ?: throw IllegalArgumentException("Incorrect input line $line")*/
    }

    fun go() {
        var lines =readInput("day16")
        lines.forEach(::parse)
        step(data["AA"]!!, 0, listOf<String>(), 0)
        println(best)
    }

}


fun main() {
    Day16().go()
}