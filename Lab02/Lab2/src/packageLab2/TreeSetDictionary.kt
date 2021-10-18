package packageLab2

import java.io.File
import java.util.*

object TreeSetDictionary : IDictionary {
    val wordsTree = TreeSet<String>()
    init {
        File(IDictionary.fileName).readLines().forEach{ HashSetDictionary.add(it) }
    }

    override fun add(word: String): Boolean {
        if(!find(word)){
            TreeSetDictionary.wordsTree.add(word)
            return true
        }
        return false

    }
    override fun find(word: String): Boolean {
        return TreeSetDictionary.wordsTree.contains(word)
    }

    override fun size() : Int{
        return TreeSetDictionary.wordsTree.size
    }

}


