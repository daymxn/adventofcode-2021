package DayOne

import java.io.File

fun main(){
    val input = File("./src/DayOne/input.txt").readLines()
    println(partOne(input))
    println(partTwo(input))
}

fun partOne(input: List<String>): Int{
    var previous: Int? = null
    var largerCount = 0
    for(line in input){
        val current = line.toInt()
        previous?.let{
            if(current > it){
                largerCount++
            }
        }

        previous = current
    }

    return largerCount
}


fun partTwo(input: List<String>): Int{
    var largerCount = 0

    var previousWindow: Int? = null

    for(index in input.indices) {
        var current = 0
        for(i in 0 until 3) {
            input.getOrNull(index + i)?.let {
                current += it.toInt()
            } ?: return largerCount
        }

        previousWindow?.let {
            if(current > it) {
                largerCount++
            }
        }

        previousWindow = current
    }

    return largerCount
}