package com.grudus.adventofcode.day02

import com.grudus.adventofcode.readDayInput

private fun <E> List<E>.updated(index: Int, elem: E): List<E> =
    mapIndexed { i, existing -> if (i == index) elem else existing }

object _1202ProgramAlarm {
    private const val ADD_CODE = 1
    private const val MULTIPLY_CODE = 2
    private const val END_CODE = 99

    fun firstStar(initialMemory: List<Int>): Int {
        val memory = initialMemory.updated(1, 12).updated(2, 2)
        return processMemory(0, memory)
    }

    fun secondStar(initialMemory: List<Int>): Int {
        for (noun in 0 until 99) {
            for (verb in 0 until 99) {
                val memory = initialMemory.updated(1, noun).updated(2, verb)
                val output = processMemory(0, memory)
                if (output == 19690720) {
                    return 100 * noun + verb
                }
            }
        }
        return -1
    }

    private tailrec fun processMemory(instructionPointer: Int, memory: List<Int>): Int {
        val opcode = memory[instructionPointer]
        if (opcode == END_CODE)
            return memory[0]
        val address1 = memory[instructionPointer + 1]
        val address2 = memory[instructionPointer + 2]
        val value1 = memory[address1]
        val value2 = memory[address2]

        val result = when (opcode) {
            ADD_CODE -> value1 + value2
            MULTIPLY_CODE -> value1 * value2
            else -> throw RuntimeException("Jebło [$instructionPointer] - [$address1,$address2]")
        }
        val addressToUpdate = memory[instructionPointer + 3]
        return processMemory(instructionPointer + 4, memory.updated(addressToUpdate, result))
    }

}


fun main() {
    val input = readDayInput("02")
    val intcode: List<Int> = input[0].split(",").map { it.toInt() }

    println(_1202ProgramAlarm.firstStar(intcode))
    println(_1202ProgramAlarm.secondStar(intcode))
}
