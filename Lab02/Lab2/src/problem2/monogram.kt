package problem2

fun main(){
//1
    val name = "John Smith"
    println(name.monogram())
//2
    val fruits = mutableListOf<String>("apple", "pear", "melon", "strawberry")
    println(fruits.connectString())
//3
    println(fruits.biggestString())
}

fun String.monogram() = this.split(" ").map { it.first() }.joinToString(separator = "")
fun List<String>.connectString() = this.joinToString(separator = "#")
fun List<String>.biggestString() = this.maxByOrNull { it.length }