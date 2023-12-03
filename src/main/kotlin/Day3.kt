import java.io.File

fun main(){
    val text = File("Data/D3P1.txt").readLines()
    val indexSymbols = mutableMapOf<Pair<Int, Int>, Int>()
    val indexNumbers =  mutableMapOf<Pair<Int, Int>, Int>()
    val  maybeGears = mutableMapOf<Pair<Int, Int>, Int>()
    for(i in 0 until text.size) {
        text[i].indexesOf("[^.0-9]").forEach{indexSymbols[Pair(it.first, i)] = 1}
        text[i].indexesOf("\\d+").forEach{indexNumbers[Pair(it.first, i)] = it.second}
        text[i].indexesOf("\\*").forEach{maybeGears[Pair(it.first, i)] = 0}
    }
//    println(indexNumbers.filter{
//        number ->
//        indexSymbols.any{symbol -> adjacent(number.key, symbol.key, number.value)}}
//        .map{text[it.key.second].substring(it.key.first,it.key.first+it.value)}.sumOf { it.toInt() }
//    )
    var part2 = 0L
    maybeGears.forEach {gear ->
        val count = indexNumbers.filter{adjacent(it.key, gear.key, it.value)}
            .map{text[it.key.second].substring(it.key.first,it.key.first+it.value)}
            .map{it.toInt()}

        part2 += if(count.size != 2) 0 else count.reduce{a, b -> a*b}
    }
    println(part2)
}

//var1: Number var2: Symbol
fun adjacent(var1: Pair<Int, Int>, var2: Pair<Int, Int>, length: Int): Boolean {
    return var2.first in var1.first-1..var1.first+length
            && var2.second in var1.second-1..var1.second+1
}

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Pair<Int, Int>> {
    return this?.let {
        val regex = if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr)
        regex.findAll(this).map { Pair(it.range.start, it.value.length) }.toList()
    } ?: emptyList()
}