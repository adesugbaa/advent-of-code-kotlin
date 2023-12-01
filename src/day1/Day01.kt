val words : Map<String, Int> = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { line: String ->
            line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt()
        }
    }

    fun part2(input: List<String>): Int {
        val updatedInput = input.map { row ->
            row.mapIndexedNotNull { index, c ->
                if (c.isDigit()) c
                else
                    row.possibleWordsAt(index).firstNotNullOfOrNull { candidate ->
                        words[candidate]
                    }
            }.joinToString()
        }
        
        return updatedInput.sumOf { line: String ->
            line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day1/test_day01_pt1")
    check(part1(testInput) == 142)
    
    val testInput2 = readInput("day1/test_day01_pt2")
    check(part2(testInput2) == 281)

    val input = readInput("day1/day01")
    part1(input).println()
    part2(input).println()
}

fun String.possibleWordsAt(start: Int): List<String> =
    (3..5).map { len ->
        substring(start, (start + len).coerceAtMost(length))
    }
