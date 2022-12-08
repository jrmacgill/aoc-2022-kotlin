data class Folder(val name: String, val parent: String, var size : Int, var children: MutableList<String>)
data class File(val name: String, val size: Int, val dir: String)

fun main() {
    DaySeven().run()
}

class DaySeven( val dirMap: HashMap<String, Folder> = HashMap<String, Folder>()) {

    var current: Folder? = Folder("/", "/", 0, mutableListOf())


    fun up() {
        println("Up Directory")
        current = dirMap[current!!.parent] ?: error("unknown parent " + current)
    }

    fun down(child: String) {
        println("Down Directory to " + child)
        if (dirMap.contains(child)) error("duplicate!")
        val mkdir = Folder(current!!.name+":"+child, current!!.name, 0, mutableListOf())
        current!!.children.add(mkdir.name)
        current = mkdir
        dirMap[mkdir.name] = mkdir
    }

    fun root() {
        println("To Root")
        current = dirMap["/"]
    }


    fun dir() {
        println("dir exists")
    }

    fun ls() {
        println("start listing")
        current!!.size = 0
    }

    fun record(it: String) {
        println("make a note of " + it)
        val file = File(it.split(" ")[1], it.split(" ")[0].toInt(), current!!.name)

        current!!.size += file.size

    }

    fun totalSize(name : String) : Int {
        val f = dirMap[name] ?: return 0

        return f.size + f.children.sumOf { totalSize(it) }

    }

    fun run() {
        val file = "daySeven"

        dirMap["/"] = current!!
        val maxSize = 70000000
        val spaceNeeded = 30000000


        val input = readInput(file)
        input.forEach() {
            when {
                it.startsWith("$ cd ..") -> up()
                it.startsWith("$ cd /") -> root()
                it.startsWith("$ cd ") -> down(it.split(" ").last())
                it.startsWith("$ dir") -> dir()
                it.startsWith("$ ls") -> ls()
                it.startsWith("dir") -> {}
                else -> record(it)
            }
        }
        val sizes = dirMap.mapValues { f -> totalSize(f.value.name) }

        val totalUsed = maxSize - totalSize("/")


        println(sizes.filter { n -> n.value < 100000 }.values.sum())

        println(totalUsed)
        val minToFree = spaceNeeded - totalUsed
        println(" min to free " + minToFree)

        val candidates =  sizes.filter{ n -> n.value > minToFree}.values.sortedDescending()

        println(candidates)


    }


}