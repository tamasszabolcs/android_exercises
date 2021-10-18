package packageLab2

import java.io.File

object ListDictionary : IDictionary {

    val wordsList = mutableListOf<String>()

    init {
        File(IDictionary.fileName).readLines().forEach{ add(it)}
    }

    override fun add(word: String): Boolean {
        if(!find(word)){
            wordsList.add(word)
            return true
        }
        return false
    }

    override fun find(word: String): Boolean {
        return wordsList.contains(word)
    }


    override fun size(): Int {
        return wordsList.size
    }


}