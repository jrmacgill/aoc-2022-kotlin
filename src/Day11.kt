import java.io.File
import java.lang.Math.floor
import java.util.SortedSet

class Monkey(val id : Int, val items: MutableList<Int>, val op: (old: Int) -> Int, val test : Int, val pass : Int, val fail : Int) {
    var count = 0L

}


class Day11 {

    val monkies : HashMap<Int, Monkey> = HashMap<Int, Monkey>()

    val regex = Regex("Monkey ([0-9]):")

    fun print() {
        for ((id, monkey) in monkies) {
            println("Monkey " + id + " Cont " + monkey.count)
           // println(monkey.items.joinToString(" "))

        }
    }

    fun round(div : Float, prod : Int) {
        monkies.map { m ->
            turn(m.value, div, prod) //monkies)
         }
       //rint()
    }

    fun turn(monkey : Monkey, div : Float, prod : Int) {


        monkey.items.forEach(){
            monkey.count++
            //print("Pass item "+it)

            var worry = monkey.op(it)
            worry = kotlin.math.floor(worry / div).toInt()

            worry = worry % prod

            if (worry % monkey.test == 0) {
                monkies[monkey.pass]!!.items.add(worry)
                // println(" to monkey " + pass + " pass")
            } else {
                monkies[monkey.fail]!!.items.add(worry)
                //   println(" to monkey " + fail + " fail")
            }
        }
        monkey.items.clear()
    }

    fun go() {
        //monkies[0] = Monkey(0, mutableListOf(79,98), { old -> old * 19}, 23, 2,3)
        //monkies[1] = Monkey(1, mutableListOf(54,65,75,74), { old -> old + 6}, 19, 2,0)
        //monkies[2] = Monkey(2, mutableListOf(79, 60, 97), {old -> old * old}, 13, 1,3)
        //monkies[3] = Monkey(3, mutableListOf(74), {old -> old+3}, 17, 0,1)

        monkies[0] = Monkey(0, mutableListOf(65, 58, 93, 57, 66), { old -> old * 7}, 19, 6,4)
        monkies[1] = Monkey(1, mutableListOf(76, 97, 58, 72, 57, 92, 82), { old -> old + 4}, 3, 7,5)
        monkies[2] = Monkey(2, mutableListOf(90, 89, 96), { old -> old * 5}, 13, 5,1)
        monkies[3] = Monkey(3, mutableListOf(72, 63, 72, 99), { old -> old * old}, 17, 0,4)
        monkies[4] = Monkey(4, mutableListOf(65), { old -> old  +1}, 2, 6,2)
        monkies[5] = Monkey(5, mutableListOf(97, 71), { old -> old + 8}, 11, 7,3)
        monkies[6] = Monkey(6, mutableListOf(83, 68, 88, 55, 87, 67), { old -> old + 2}, 5, 2,1)
        monkies[7] = Monkey(7, mutableListOf(64, 81, 50, 96, 82, 53, 62, 92), { old -> old + 5}, 7, 3,0)

        var prod =1
        monkies.mapValues { m -> prod *= m.value.test; prod }

        for (i in 1..10000) {
            round(1F, prod)
        }
        val result = monkies.values.map { v -> v.count }.sortedDescending().take(2).reduce{acc,i -> acc*i}
        println(result)
        for (i in 1..10000) {
            round(1F, prod)
        }


    }
}

fun main() {
    Day11().go()
}