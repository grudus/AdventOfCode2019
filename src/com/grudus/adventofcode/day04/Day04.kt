package com.grudus.adventofcode.day04

object SecureContainer {

    fun firstStar(possiblePasswords: List<String>): Int =
        possiblePasswords.count { password ->
            matches(password) { (_, group) -> group.size > 1 }
        }

    fun secondStar(possiblePasswords: List<String>): Int =
        possiblePasswords.count { password ->
            matches(password) { (_, group) -> group.size == 2 }
        }

    private fun matches(password: String, groupPredicate: (Map.Entry<Char, List<Char>>) -> Boolean): Boolean =
        isSorted(password) && password.groupBy { it }.any(groupPredicate)

    private fun isSorted(password: String) =
        password.toCharArray().contentEquals(password.toCharArray().sortedArray())
}


fun main() {
    val input = "278384-824795"
    val (from, to) = input.split("-").map { it.toInt() }
    val possiblePasswords: List<String> = (from until to).map { it.toString() }

    println(SecureContainer.firstStar(possiblePasswords))
    println(SecureContainer.secondStar(possiblePasswords))
}
