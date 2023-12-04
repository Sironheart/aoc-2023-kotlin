import kotlin.math.pow

fun main() {
    
    fun parse(line: String): Int {
        val (_, gameValues) = line.split(":").map { it.trim() }
        val (winning, chosen) = gameValues.split("|").mapNotNull {
            it.trim().split(" ").mapNotNull {
                value -> value.toIntOrNull()
            }
        }
        
        return winning.count { it in chosen }
    }
    fun part1(input: List<String>) = input.sumOf { row ->
        2.0.pow(parse(row) - 1).toInt() 
    }

    fun part2(input: List<String>): Int {
        val cardCopies = IntArray(input.size) {1}
        for ((index, line) in input.withIndex()) {
            val matches = parse(line)
            if (matches > 0) {
                for (i in 1..matches) {
                    if (index + i >= cardCopies.size) break
                    cardCopies[index + i] += cardCopies[index]
                }
            }
        }
        return cardCopies.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test1")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
