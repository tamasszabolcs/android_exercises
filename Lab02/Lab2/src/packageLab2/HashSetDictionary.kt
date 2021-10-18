package packageLab2

import java.io.File

object HashSetDictionary : IDictionary {
    val wordsHash = hashSetOf<String>()

    init {
        File(IDictionary.fileName).readLines().forEach{add(it)}
    }

    override fun add(word: String): Boolean {
        if(!find(word)){
            HashSetDictionary.wordsHash.add(word)
            return true
        }
        return false
    }

    override fun find(word: String): Boolean {
        return HashSetDictionary.wordsHash.contains(word)
    }

    override fun size(): Int {
        return wordsHash.size
    }
}