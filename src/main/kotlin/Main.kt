import kotlinx.coroutines.runBlocking

fun main() {

    val client = OpenAiClient()

    runBlocking {
        val food = "orange"
        val answer = client.doToolcall("I want a $food")
        println("$food is a $answer")
    }
}
