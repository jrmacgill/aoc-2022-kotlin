import java.util.*

class Day10 {
    var rx = 1;
    var tick = 0;
    var total = 0

    fun add(line: String) {
        var amt = line.split(" ")[1].toInt()
        tick()
        tick()
        rx+=amt
    }

    fun tick() {
        if ((tick-20) % 40 == 0) {
            total += (rx*tick)
        }
        if (tick % 40 in rx-1 .. rx+1) {
            print("#")
        } else {
            print(".")
        }
        if ((tick+1) % 40 == 0) {
            println()
        }
        tick++
    }

    fun go() {
        var lines = readInput("dayTen")
        lines.forEach {
            when {
                it == "noop" -> { tick()}
                it.startsWith("addx") -> add(it);
                else -> error("unsuported")
            }
        }
        print(total)
    }

}

fun main() {
    Day10().go()
}