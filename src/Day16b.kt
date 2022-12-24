import java.lang.Integer.min

class Day16b {

    val inputLineRegex = """Valve ([A-Z]*) has flow rate=(\d+); tunnel[s]? lead[s]? to valve[s]? (.*)""".toRegex()

    val data = mutableMapOf<String, Room>()
    val compactData = mutableMapOf<String, CompactRoom>()
    val maxTicks = 30
    var best = 0

    class Room(val id : String, val flow : Int, val links : List<String>) {

    }

    class CompactRoom(val id : String, val flow : Int, val links : List<Pair<String,Int>>) {

    }

    fun maxRemain(open : List<String>, tick : Int) : Int {
        var remain = 0
        compactData.forEach { v ->
            if (!open.contains(v.key)) {
                remain += v.value.flow * (maxTicks-tick)
            }
        }
        return remain
    }


    fun step(room : CompactRoom  , tick : Int, open : List<String>, score:Int) {
        if (tick == maxTicks) {
            println("Done with: " + score)
            if (score > best) {
                best = score
                best = score
            }
            return
        }
        if (open.size == compactData.size) {
            println("All open with  " + score)
            return
        }
        if (maxRemain(open, tick)+score <= best) {
            //println("Crap, giveup")
            return
        }
        if (!open.contains(room.id)) {
            var next =  open.toMutableList()
            next.add(room.id)
            step(room, tick+1,next, score + (room.flow*(maxTicks-(tick+1))))
        }
        room.links.forEach{
            if (!open.contains(it.first)) {
                // println("step to " + it.trim())
                step(compactData[it.first.trim()]!!, tick + it.second, open, score)
            }
        }

    }

    fun cost(room : String, target : String, visited : List<String>, cost: Int) : Int {
        if (room.trim() == target.trim()) return cost;
        var best = Int.MAX_VALUE
        data[room.trim()]!!.links.filter { r -> !visited.contains(r) }.forEach{
            var c = cost(it, target, visited+room, cost+1)
            best = min(c, best)
        }
        return best
    }

    fun compact(room: Room) : CompactRoom{
        //var visited = listOf<String>()
        var costLinks = mutableListOf<Pair<String, Int>>()
        data.forEach{
            if (it.key != room.id) {
                if (data[it.key]!!.flow > 0) {
                    var cost = cost(room.id, it.key, listOf(), 0)
                    costLinks.add(Pair(it.key, cost))
                }
            }
        }
        return CompactRoom(room.id, room.flow, costLinks)
    }

    fun parse(line : String) {
        val match = inputLineRegex
            .find(line)!!
        println(match)
        val (code, flow, links) = match.destructured
        data[code] = Room(code, flow.toInt(), links.split(","))


    }

    fun go() {
        var lines =readInput("day16_test")
        lines.forEach(::parse)
        println("compacting")
        compactData["AA"] = compact(data["AA"]!!)

        data.forEach {
            if(it.value.flow > 0) {
                println("compact " + it.key)
                compactData[it.key] = compact(it.value)
            }
        }
       println(compactData)

        println("find:")
        step(compactData["AA"]!!, 0, listOf<String>(), 0)
        println(best)
    }

}


fun main() {
    Day16b().go()
}