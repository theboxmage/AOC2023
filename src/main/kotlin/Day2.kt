import java.io.File
import java.lang.Integer.max

fun main() {
    val text = File("Data/D2P1.txt").useLines{it.map{i->i.replace("[,;]".toRegex(), "")}.map{i -> i.split(" ")}.toList()}
    var part1 = 0L
    var part2 = 0L
    text.forEach {
        val id = it[1].substring(0, it[1].length-1).toInt()
        val map = mutableMapOf<String, Int>()
        for(i in 2 until it.size step 2) {
            map.put(it[i+1], max(it[i].toInt(), map.getOrDefault(it[i+1], 0)))
        }
        if(map.getOrDefault("red", 0) <= 12
            && map.getOrDefault("green", 0) <= 13
            && map.getOrDefault("blue", 0) <= 14) {
            part1 += id
        }
        part2 += map.values.reduce{a , b -> a * b }
    }
    println(part1)
    println(part2)
}
