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

fun partTwo(input: List<String>): Int {
    return calculateOxygenGeneratorRating(input) * calculateCO2ScrubberRating(input)
}

fun String.fromBinary(): Int =
    this.reversed().foldIndexed(0) { index, acc, i ->
        acc + (i.digitToInt() * (2.0).pow(index.toDouble()).toInt())
    }


fun calculateOxygenGeneratorRating(input: List<String>): Int {
    var currentList: List<String> = input
    for(index in input[0].indices){
        if(currentList.size == 1) break
        currentList = currentList.partition {
            it[index] == '1'
        }.run {
            if(this.first.size >= this.second.size) this.first else this.second
        }
    }

    return currentList[0].fromBinary()
}

fun calculateCO2ScrubberRating(input: List<String>): Int {
    var currentList: List<String> = input
    for(index in input[0].indices){
        if(currentList.size == 1) break
        currentList = currentList.partition {
            it[index] == '0'
        }.run {
            if(this.first.size <= this.second.size) this.first else this.second
        }
    }

    return currentList[0].fromBinary()
}