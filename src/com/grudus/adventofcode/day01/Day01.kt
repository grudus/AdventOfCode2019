package com.grudus.adventofcode.day01

import com.grudus.adventofcode.day01.TheTyrannyOfTheRocketEquation.firstStar
import com.grudus.adventofcode.day01.TheTyrannyOfTheRocketEquation.secondStar
import com.grudus.adventofcode.readDayInput

object TheTyrannyOfTheRocketEquation {

    fun firstStar(input: List<String>) = input
        .map { calculateFuel(it.toLong()) }
        .sum()

    fun secondStar(input: List<String>) = input
        .map { totalFuel(it.toLong()) }
        .sum()

    private fun calculateFuel(it: Long) = (it.toDouble() / 3).toLong() - 2

    private fun totalFuel(mass: Long): Long {
        tailrec fun totalFuel(currentMass: Long, fuelSum: Long): Long {
            val fuel = calculateFuel(currentMass)
            return if (fuel <= 0)
                fuelSum
            else totalFuel(fuel, fuelSum + fuel)
        }
        return totalFuel(mass, 0)
    }
}

fun main() {
    val input = readDayInput("01")

    println(firstStar(input))
    println(secondStar(input))
}
