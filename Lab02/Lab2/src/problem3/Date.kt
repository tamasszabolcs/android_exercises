package problem3

import java.time.DateTimeException
import java.time.Month
import java.time.MonthDay
import java.time.Year
import java.util.*


data class Date(val year : Int = Calendar.YEAR, val month: Int = Calendar.MONTH,val day : Int = Calendar.DAY_OF_MONTH) : Comparable<Date> {
    override fun toString(): String{
        val newYear = year.toString()
        val newMonth = month.toString()
        val newDay = day.toString()

        return "$newYear/$newMonth/$newDay"
    }

    override fun compareTo(other: Date): Int {
        val cmpY = this.year - other.year
        if(cmpY == 0){
            val cmpM = this.month - other.month
            if(cmpM == 0){
                return this.day - other.day
            }
            return cmpM
        }
        return cmpY
    }


}

fun Date.leap_year_checker() : Boolean{
    return Year.isLeap(year.toLong())
}

fun Date.valid_date() : Boolean{
    try {
        Year.of(year)
        MonthDay.of(month,day)
        return true

    }catch (e : DateTimeException){
        return false
    }
}