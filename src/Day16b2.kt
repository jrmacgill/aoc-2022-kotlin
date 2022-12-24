import java.lang.Integer.max
import java.lang.Integer.min

class Day16b2 {

    val inputLineRegex = """Valve ([A-Z]*) has flow rate=(\d+); tunnel[s]? lead[s]? to valve[s]? (.*)""".toRegex()

    val data = mutableMapOf<String, Room>()
    val compactData = mutableMapOf<String, CompactRoom>()
    val maxTicks = 26
    var best = 0

    var options = mutableSetOf<List<Pair<String,Int>>>()

    class Room(val id : String, val flow : Int, val links : List<String>) {

    }

    class CompactRoom(val id : String, val flow : Int, val links : List<Pair<String,Int>>) {

    }



    fun step(room : CompactRoom  , tick : Int, open : List<String>, history:List<Pair<String, Int>>, score:Int) {
        var nextOpen =  open.toMutableList()
        var nextHistory = history.toMutableList()
        var cost = 0
        var bonus = 0
        if (tick >= maxTicks) {
            println("Done with: " + score)

            options.add(history)
            if (score > best) {
                best = score
            }
            return
        }

        if (!open.contains(room.id)) {

            nextOpen.add(room.id)
            cost+=1
            bonus = room.flow*(maxTicks-(tick+1))
            if (bonus > 0) {
                nextHistory.add(Pair(room.id, bonus))
            }
            //step(room, tick+1,next, score + (room.flow*(maxTicks-(tick+1))))
        }
        if (nextOpen.size == compactData.size) {
            println("All open with  " + (score+bonus))
            if (score+bonus > best) {
                best = score+bonus
            }
            options.add(history)
            return
        }
        room.links.forEach{
            if (!open.contains(it.first)) {
                step(compactData[it.first.trim()]!!, tick + it.second+cost, nextOpen, nextHistory,score+bonus)
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
        var lines =readInput("day16")
        lines.forEach(::parse)
        println("compacting")
        compactData["AA"] = compact(data["AA"]!!)

        data.forEach {
            if(it.value.flow > 0) {
                println("compact " + it.key)
                compactData[it.key] = compact(it.value)
            }
        }

        println("find:")

        step(compactData["AA"]!!, 0, listOf("AA"), listOf<Pair<String, Int>>(), 0)
        println(best)
        println(options.size)
        options.forEach(::println)
        var bestComboScore = 0
        var bestCombo = mapOf<String, Int>()
        options.forEach{a->
            options.forEach { b ->

                var combo = a.associateBy ( {it.first}, {it.second} ).toMutableMap()
                b.forEach{
                    if (it.second > combo[it.first]?:0) {
                        combo[it.first] = it.second
                    }
                }
                var score = combo.values.sum()
                if (score > bestComboScore) {
                    bestComboScore = score
                    bestCombo = combo
                    println(bestCombo)
                }
                bestComboScore = max(combo.values.sum(), bestComboScore)
                //println(combo)

            }
        }
        println(bestComboScore)
        println(bestCombo)


    }

}


fun main() {
    Day16b2().go()
}