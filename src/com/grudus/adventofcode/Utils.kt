package com.grudus.adventofcode

import java.io.File
import java.util.regex.Matcher

fun readDayInput(day: String): List<String> =
    File("src/com/grudus/adventofcode/day$day/input.txt").readLines()


fun Matcher.findGroups(): List<String> =
    find().let { (1..groupCount()).map { group(it) } }

fun <T> Array<Array<T>>.copy2Dim() = Array(size) { get(it).clone() }

typealias Matrix<T> = List<List<T>>
