import java.io.File

fun main(){
    val text = File("Data/D3P1.txt").readLines()
    val indexSymbols = mutableMapOf<Pair<Int, Int>, Int>()
    val indexNumbers =  mutableMapOf<Pair<Int, Int>, Int>()
    val  maybeGears = mutableMapOf<Pair<Int, Int>, Int>()
    for(i in text.indices) {
        text[i].indexesOf("[^.0-9]").forEach{indexSymbols[Pair(it.first, i)] = 1}
        text[i].indexesOf("\\d+").forEach{indexNumbers[Pair(it.first, i)] = it.second}
        text[i].indexesOf("\\*").forEach{maybeGears[Pair(it.first, i)] = 0}
    }
    println(indexNumbers.filter{
        number ->
        indexSymbols.any{symbol -> adjacent(number.key, symbol.key, number.value)}}
        .map{text[it.key.second].substring(it.key.first,it.key.first+it.value)}.sumOf { it.toInt() }
    )

    val part2 = maybeGears.keys.sumOf {gear ->
        val count = indexNumbers.filter{adjacent(it.key, gear, it.value)}
            .map{text[it.key.second].substring(it.key.first,it.key.first+it.value)}
            .map{it.toInt()}

        if(count.size != 2) 0 else count.reduce{a, b -> a*b}
    }
    println(part2)
}

fun adjacent(number: Pair<Int, Int>, symbol: Pair<Int, Int>, length: Int): Boolean {
    return symbol.first in number.first-1..number.first+length
            && symbol.second in number.second-1..number.second+1
}

fun String?.indexesOf(pattern: String, ignoreCase: Boolean = true): List<Pair<Int, Int>> {
    return this?.let {
        val regex = if (ignoreCase) Regex(pattern, RegexOption.IGNORE_CASE) else Regex(pattern)
        regex.findAll(this).map { Pair(it.range.first, it.value.length) }.toList()
    } ?: emptyList()
}