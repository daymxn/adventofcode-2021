package DayTwo
import java.io.File

fun main(){
    val input = File("./src/DayTwo/input.txt").readLines()
    println(partOne(input))
    println(partTwo(input))
}

data class Submarine(var horizontal: Int = 0, var depth: Int = 0, var aim: Int = 0){
    fun move(line: String){
        val movements = line.split(" ")
        val amount = movements[1].toInt()
        when(movements[0][0]){
            'f' -> horizontal += amount
            'd' -> depth += amount
            'u' -> depth -= amount
        }
    }

    fun moveWithAim(line: String){
        val movements = line.split(" ")
        val amount = movements[1].toInt()
        when(movements[0][0]){
            'f' -> {
                horizontal += amount
                depth += amount * aim
            }
            'd' -> aim += amount
            'u' -> aim -= amount
        }
    }
}

fun partOne(input: List<String>): Int{
    val sub = Submarine()
    for(line in input) {
        sub.move(line)
    }
    return sub.horizontal * sub.depth
}

fun partTwo(input: List<String>): Int{
    val sub = Submarine()
    for(line in input) {
        sub.moveWithAim(line)
    }
    return sub.horizontal * sub.depth
}