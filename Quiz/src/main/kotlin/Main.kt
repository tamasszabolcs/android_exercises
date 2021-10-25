import java.io.File

fun main() {
    val quiz  = QuizController()
    quiz.doQuiz(1)
}

data class Question(
     val text: String,val answers: List<String>
    )

class QuizController {
    val questions = arrayListOf<Question>()
    init {
        val fileName = "questions.txt"
        val lines = File(fileName).readLines()
        for (i in 0..lines.size - 1 step 1) {
            val QuestionItem = Question(
                lines[i],
                listOf(lines[i + 1], lines[i + 2], lines[i + 3], lines[i + 4])
            )
            questions.add(QuestionItem)

        }
    }

    fun randomizeQuestions() = questions.shuffle()

    fun doQuiz(numOfQuestions: Int){
        if (numOfQuestions < 0){
            println("Incorrect number!")
        }
        randomizeQuestions()

    }

}

