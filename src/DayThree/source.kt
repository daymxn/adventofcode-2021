package DayThree

import java.io.File
import kotlin.math.pow

fun main(){
    val input = File("./src/DayThree/input.txt").readLines()
    println(partOne(input))
    println(partTwo(input))
}

class BitCounter(val bits: IntArray){
    fun processLine(line: String){
        for(index in line.indices){
            bits[index] += if(line[index] == '1') 1 else -1
        }
    }

    fun convertToBinary(): BitCounter =
        this.apply {
            bits.onEachIndexed { index, it ->
                bits[index] = if (it > 0) 1 else 0
            }
        }

    fun flip(): BitCounter =
        this.apply {
            bits.onEachIndexed { index, it ->
                bits[index] = if(it == 1) 0 else 1
            }
        }


    fun toNumber(): Int =
        bits.reversedArray().foldIndexed(0) { index, acc, i ->
            acc + (i * (2.0).pow(index.toDouble()).toInt())
        }

}

fun partOne(input: List<String>): Int{
    val counter = BitCounter(IntArray(12){ 0 })
    for(line in input){
        counter.processLine(line)
    }
    counter.convertToBinary()

    return counter.toNumber() * counter.flip().toNumber()
}

fun partTwo(input: List<String>): Int{
    return -1
}