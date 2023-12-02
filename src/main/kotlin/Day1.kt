import java.io.File
val r = Regex("one|two|three|four|five|six|seven|eight|nine|\\d")
val r1 = Regex(".*(?<f>one|two|three|four|five|six|seven|eight|nine|\\d).*?$")
val map = mapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9"
)

fun main() {
    val text = File("Data/D1P1.txt").useLines{it.toList()}
    val output1 = text.sumOf {
        (it.first { c: Char -> c.isDigit() }.toString() +
                it.last{c: Char -> c.isDigit()}.toString()).toInt()
    }
    println(output1)

    val output2 = text.sumOf {
        (
            r.find(it)?.value?.let { it1 -> mapStrings(it1) }
            + r1.find(it)?.groups?.get("f")?.value?.let { it1 -> mapStrings(it1) }
        ).toInt()
    }
    println(output2)
}

fun mapStrings(str: String): String {
    return map.getOrDefault(str, str)
}