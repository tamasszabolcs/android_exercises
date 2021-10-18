package packageLab2

interface IDictionary {



    companion object{
        val fileName = "dictionary.txt"
    }


    fun add(word:String):Boolean
    fun find(word:String):Boolean
    fun size():Int
}