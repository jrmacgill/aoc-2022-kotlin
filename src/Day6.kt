fun main(args: Array<String>) {
    val lines = readInput("daySix")
    lines.forEach() {
        println(findMarker(it, 4))
        println(findMarker(it, 14))
    }

}

private fun findMarker(line: String, markerSize: Int) : Int {
    val letter = line.windowed(size = markerSize).toSet().first { s -> s.toSet().size == markerSize }
    return line.indexOf(letter) + markerSize
}