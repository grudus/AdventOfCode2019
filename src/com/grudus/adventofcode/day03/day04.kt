package com.grudus.adventofcode.day03

import com.grudus.adventofcode.readDayInput
import kotlin.math.abs

object CrossedWires {
    data class Point(val x: Int = 0, val y: Int = 0) {
        fun distanceTo(other: Point) = abs(other.x - x) + abs(other.y - y)
        fun run(distance: Int, direction: Direction): Point =
            Point(x + distance * direction.dx, y + distance * direction.dy)
    }

    enum class Direction(val dx: Int, val dy: Int) {
        U(0, 1), R(1, 0), D(0, -1), L(-1, 0)
    }

    data class WirePath(val direction: Direction, val distance: Int)

    fun firstStar(input: List<String>): Int {
        val (left, right) = findWiresPath(input)
        val crossedWires: Set<Point> = left.intersect(right)

        return crossedWires
            .map { it.distanceTo(Point(0, 0)) }
            .min()!!
    }

    fun secondStar(input: List<String>): Int {
        val (left, right) = findWiresPath(input)
        val crossedWires: Set<Point> = left.intersect(right)

        return crossedWires.map { crossPoint ->
            val leftSteps = left.takeWhile { it != crossPoint }.size
            val rightSteps = right.takeWhile { it != crossPoint }.size
            leftSteps + rightSteps + 2
        }.min()!!
    }


    private fun findWiresPath(input: List<String>): List<List<Point>> =
        input.map { line ->
            line.split(",")
                .map { WirePath(Direction.valueOf(it[0].toString()), it.drop(1).toInt()) }
                .fold(Point() to listOf<Point>()) { (current, allLocations), wirePath ->
                    val newLocations: List<Point> = (1..wirePath.distance)
                        .map { current.run(it, wirePath.direction) }

                    (newLocations.last() to allLocations + newLocations)
                }.second
        }

}


fun main() {
    val input = readDayInput("03")

    println(CrossedWires.firstStar(input))
    println(CrossedWires.secondStar(input))
}
