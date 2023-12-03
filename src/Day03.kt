
fun main() {
    val directions = listOf(-1, 0, 1)

    fun resized(board: List<String>): List<String> {
        val boardLength = board.first().length + 1
        val emptyLine = CharArray(boardLength) { '.' }.joinToString("")
        return buildList {
            add(emptyLine)
            addAll(board.map { "$it." })
            add(emptyLine)
        }
    }

    fun checkAdjacents(board: List<String>, column: Int, row: Int, filter: (Char, Int, Int) -> Unit) {
        val length = board.first().length
        for (dx in directions) {
            for (dy in directions) {
                if (dx == 0 && dy == 0) {
                    continue
                }

                val x = dx + row
                val y = dy + column
                if (y in 0 until length) {
                    filter(board[x][y], x, y)
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val board = resized(board = input)

        return board.mapIndexed { row, line ->

            var lineSum = 0
            var currentNumber = ""
            var hasSymbolNear = false

            line.forEachIndexed { column, cell ->
                if (cell.isDigit()) {
                    currentNumber += cell
                    checkAdjacents(board, column, row) { adj, _, _ ->
                        if (!adj.isDigit() && adj != '.') {
                            hasSymbolNear = true
                        }
                    }

                } else if (currentNumber.isNotEmpty()) {
                    if (hasSymbolNear) {
                        lineSum += currentNumber.toInt()
                    }
                    currentNumber = ""
                    hasSymbolNear = false
                }
            }

            lineSum

        }.sum()
    }


    fun part2(input: List<String>): Int {
        val board = resized(board = input)

        val partsByGear = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()

        board.forEachIndexed { row, line ->

            var currentNumber = ""
            val parts = mutableSetOf<Pair<Int, Int>>()

            line.forEachIndexed { column, cell ->
                if (cell.isDigit()) {
                    currentNumber += cell

                    checkAdjacents(board, column, row) { adj, x, y ->
                        if (adj == '*') {
                            parts.add(x to y)
                        }
                    }

                } else if (currentNumber.isNotEmpty()) {
                    if (parts.isNotEmpty()) {
                        val partNumber = currentNumber.toInt()
                        parts.forEach { pos ->
                            partsByGear.getOrPut(pos) { mutableListOf() }.add(partNumber)
                        }
                    }
                    currentNumber = ""
                    parts.clear()
                }
            }
        }

        return partsByGear.values.filter { it.size == 2 }.sumOf {
            it.reduce { acc, l -> acc * l }
        }
    }

    val testInput = readInput("Day03_test1")
    val input = readInput("Day03")

    check(part1(testInput) == 4361)
    println(part1(input))

    check(part2(testInput) == 467835)
    println(part2(input))
}