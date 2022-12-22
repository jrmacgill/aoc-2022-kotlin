import utils.splitBy

class Day13_2 {




    // giberish
    fun parse(line : String, nest : MutableList<Any>) : Int {
        var i  = 0
        var digit = ""
        while (i < line.length) {
            i++
            if (line[i].isDigit()) {
                digit += line[i]; continue}
            when (line[i]) {
                '[' -> {
                    var n = mutableListOf<Any>()
                    nest.add(n)
                    i += parse(line.substring(i), n)
                }
                ']' -> {
                    if (digit.isNotEmpty()) {
                        nest.add(digit.toInt())
                    }
                    return i
                }
                ',' -> if (digit.isNotEmpty()) {nest.add(digit.toInt()); digit=""}
                else -> {}

            }


        }
        return -1
    }

    fun comp(a: MutableList<*>, b: MutableList<*>, depth : Int) : Boolean {
        println(" ".repeat(depth) + "Compare " + a  + " vs " + b)

        if (a.zip(b).any {
                println(" ".repeat(depth) + "Compare " +  it.first  + " vs " +  it.second)
                when {
                    it.first is Int && it.second is Int -> {
                        if ((it.first as Int) < (it.second as Int)) throw Exception("pass")
                        if ((it.first as Int) > (it.second as Int)) throw Exception("fail")
                        false
                    }
                    it.first is MutableList<*> && it.second is MutableList<*> -> comp(it.first as MutableList<*>,
                        it.second as MutableList<*>, depth +1
                    )
                    it.first is Int -> comp(mutableListOf(it.first), it.second as MutableList<*>, depth+1)
                    it.second is Int ->  comp(it.first as MutableList<*>, mutableListOf(it.second), depth + 1)
                    else -> false
                }

                //else -> false


            }) return true
        if (a.size == b.size) return false
        if (b.size < a.size) {

            throw Exception("fail")
        }
        throw Exception("pass")

    }

    fun go() {
        var lines =readInput("dayThirteen")
        var pairs = lines.splitBy { a -> a.isEmpty() }
        println(pairs.zipWithNext().size)

        var sum = 0
        pairs.forEachIndexed() { i, e ->
            println(" Pair " + (i + 1))
            val a = mutableListOf<Any>()
            val b = mutableListOf<Any>()
            parse(e[0], a)
            parse(e[1], b)
            try {
                if (comp(a, b, 0)) {
                    println("found")
                    // sum += (i + 1)
                }
            } catch (e: Exception) {
                println(e)
                if (e.message == "pass") {
                    sum += (i + 1)
                } else {

                }
            }

        }


        println(sum)

    }
}

fun main() {
    Day13().go()
}