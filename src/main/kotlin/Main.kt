import java.util.*
import kotlin.random.Random.Default.nextInt

fun main(args: Array<String>) {
    //1
    val first: Int = 2
    val second: Int = 3
    println("${first} + ${second} = ${sumOfTwo(first, second)}")

    //2
    val immutableList = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    for(item in immutableList){
        println(item)
    }
    print("Days that start with the letter T are: ")
    val filtered1List = immutableList.filter { it.startsWith("T") }
    println(filtered1List)
    print("Days that contain letter e are: ")
    val filtered2List = immutableList.filter { it.contains("e") }
    println(filtered2List)
    print("Days of length 6 are: ")
    val filtered3List = immutableList.filter { it -> it.length == 6 }
    println(filtered3List)

    //3
    val readNum = Scanner(System.`in`)
    print("Enter an integer number: ")
    val num1 = readNum.nextInt()
    if(checkPrimeNumber(num1)){
        println("${num1} is prime!")
    }
    else{
        println("${num1} is not prime!")
    }
    print("Enter the lowest number of the array: ")
    var low = readNum.nextInt()
    print("Enter the highest number of the array: ")
    var high = readNum.nextInt()
    print("Prime numbers: ")
    while (low < high){
        if(checkPrimeNumber(low)){
            print( low.toString() + " ")
        }
        low++;
    }
    println("\n")
    //5
    println("Even numbers: ")
    println(evenNumber())
    //6
    var numbers = setOf(1, 2, 3, 4)
    println("Original list: " + numbers)
    println("The doubled list: "+ numbers.map { it * 2 })
    println(numbers.mapIndexed { idx, value -> value * idx })


    val days = setOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    println(days.map { it.uppercase() })
    println(days.map { it.first() })
    println(days.map { it + "->" + it.length })

    val mutList: MutableList<String> = mutableListOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    mutList.remove("n")
    println(mutList)

    //8
    val start = 0
    val end = 100

    for (i in 1..10) println(rand(start, end))

    var randomNums = listOf(52,3,9,74,36)
    println("Random nums after sorting")
    println(randomNums.sorted())

}
fun rand(start: Int, end: Int): Int {
    //ha hamis illegal argumentet dob
    require(start <= end) { "Illegal Argument" }
    return (start..end).random()
}
fun sumOfTwo(first: Int, second: Int) : Int{
    return first + second;
}
fun checkPrimeNumber(num: Int): Boolean {
    var flag = true
    for (i in 2..num / 2) {

        if (num % i == 0) {
            flag = false
            break
        }
    }
    return flag
}
fun evenNumber(){
    var myList = listOf(1,4,8,14,16,17,191,20)
    var filteredList = myList.filter { x -> x%2 == 0 }
    println(filteredList)
}