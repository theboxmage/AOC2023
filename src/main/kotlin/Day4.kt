import java.io.File
import kotlin.math.pow

fun main(){
    val text = File("Data/D4P1.txt").readLines()
        .map{it.substring(it.indexOf(":")+2).split("|")
        .map{i -> i.split(" ").filter{str -> str.trim() != "" }.map{num -> num.toDouble()}}}
    println(text.sumOf{
        val temp = 2.0.pow(((it[0].count { i -> it[1].contains(i) }) - 1))
        if (temp == 0.5) 0 else temp.toInt()
    })

    val copyMap = mutableMapOf<Int, Int>()
    for(ind in text.indices) {
        if(!copyMap.containsKey(ind)) {
            copyMap[ind] = 1
        }
        val count = text[ind][0].count { i -> text[ind][1].contains(i) }
        for(i in 1..count) {
            copyMap[ind+i] = copyMap.getOrDefault(ind+i, 1) +
                    copyMap.getOrDefault(ind, 1)
        }
    }
    println(copyMap.values.sumOf{it})
}

