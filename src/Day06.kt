fun main() {

    fun parse(input: List<String>): Map<Long, Long> {
        val table = input.map {
            val (_, timeOrDistance) = it.split(":")
            timeOrDistance.trim().split(" ").mapNotNull { part ->
                part.toLongOrNull()
            }
        }

        val map = mutableMapOf<Long, Long>()

        table[0].mapIndexed { index, v ->
            map += v to table[1][index]
        }

        return map
    }

    fun distancePossible(durationPress: Long, maxDuration: Long): Long {
        val restTime = maxDuration - durationPress
        return durationPress * restTime
    }

    fun part1(input: List<String>): Long {
        val game = parse(input)

        val result = game.map { (time, distance) ->
            (1..<time).count {
                distancePossible(it, time) > distance
            }
        }

        return result.reduce { a, b ->
            a * b
        }.toLong()
    }

    fun part2(input: List<String>): Long {
        val game = parse(input)

        val time = game.keys.map { it.toString() }.reduce { acc, s -> acc + s }.toLong()
        val distance = game.values.map { it.toString() }.reduce { acc, s -> acc + s }.toLong()

        return (1..time).count {
            distancePossible(it, time) > distance
        }.toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test1")
    check(part1(testInput) == 288L)
    check(part2(testInput) == 71503L)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
