package problem3

import kotlin.random.Random.Default.nextInt

fun main(){
    val date_list  = mutableListOf<Date>()
    val begin = 0
    val beginY = 1900
    val endY = 2022
    val endM = 13
    val endD = 32
    val numberOfDates = 12

//    val date = Date(year = 2020,month = 11,day = 23)
//    if(date.valid_date()){ println(date.toString())}
//    if(date.leap_year_checker()){
//        println(date.toString())}


    while(true){
        if(date_list.size == numberOfDates){
            break
        }
        val year = nextInt(beginY,endY)
        val month = nextInt(begin,endM)
        val day = nextInt(begin,endD)
        val date = Date(year,month,day)
        if(date.valid_date()){
            date_list.add(date)
        }
        else
            println("This date: $date is not valid")


    }
    date_list.forEach{ println(it)}
    println("Sorting with comparable interface")
    val sorted_list : List<Date> = date_list.sorted()
    sorted_list.forEach{println(it)}

    println("reverse the list")
    val reverse : List<Date> = sorted_list.reversed()
    reverse.forEach{println(it)}

    println("Sorting with comparator")
    val my_sort = CompareDate()
    date_list.sortWith(my_sort)
    date_list.forEach{println(it)}
}