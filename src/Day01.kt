fun main() {
    fun part1(input: List<String>) =
            input.sumOf { row ->
                val firstInt = row.first { it.isDigit() }.digitToInt()
                val lastInt = row.last { it.isDigit() }.digitToInt()

                "$firstInt$lastInt".toInt()
            }

    val numericString = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    fun toInt(second: String): Int =
            when (second) {
                "one" -> 1
                "two" -> 2
                "three" -> 3
                "four" -> 4
                "five" -> 5
                "six" -> 6
                "seven" -> 7
                "eight" -> 8
                "nine" -> 9
                else -> 0
            }

    fun part2(input: List<String>): Int = input.sumOf { row ->
                val firstInt = if (row.indexOfFirst { it.isDigit() } != -1 && row.indexOfFirst { it.isDigit() } < row.indexOfAny(numericString) || row.indexOfAny(numericString) == -1) {
                    row.first { it.isDigit() }.digitToInt()
                } else {
                    toInt(row.findAnyOf(numericString)!!.second)
                }
                val lastInt = if (row.indexOfLast { it.isDigit() } != -1 && row.indexOfLast { it.isDigit() } > row.lastIndexOfAny(numericString) || row.lastIndexOfAny(numericString) == -1) {
                    row.last { it.isDigit() }.digitToInt()
                } else {
                    toInt(row.findLastAnyOf(numericString)!!.second)
                }
                "$firstInt$lastInt".toInt()
            }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day01_test1")
    val testInput2 = readInput("Day01_test2")
    check(part1(testInput1) == 142)
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
