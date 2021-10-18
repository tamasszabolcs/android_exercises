package problem3

class CompareDate : Comparator<Date> {
    override fun compare(o1: Date?, o2: Date?): Int {
        if(o1 == null || o2 == null){
            return 0
        }
        val cmpY = o1.year - o2.year
        if(cmpY == 0){
            val cmpM = o1.month - o2.month
            if(cmpM == 0){
                return o1.day - o2.day
            }
            return cmpM
        }
        return cmpY
    }
}