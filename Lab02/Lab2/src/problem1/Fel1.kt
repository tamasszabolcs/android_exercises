package problem1


fun main(){
//    val dict: IDictionary = ListDictionary
//    val dict: IDictionary = DictionaryProvider.creatDictionary(DictionaryType.TREE_SET)
    val dict: IDictionary = DictionaryProvider.creatDictionary(DictionaryType.HASH_SET)

    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

}