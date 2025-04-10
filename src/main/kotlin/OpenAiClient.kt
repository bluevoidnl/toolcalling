import com.aallam.openai.api.chat.*
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlin.time.Duration.Companion.seconds

class OpenAiClient() {

    // see https://github.com/aallam/openai-kotlin/blob/main/guides/ChatToolCalls.md

    private val apiKey = "private"

    private val config = OpenAIConfig(
        token = apiKey,
        timeout = Timeout(socket = 60.seconds),
    )

    private val chatNsConfig = ChatNsApiHost.chatNsConfig

    private val openAI = OpenAI(config)

    suspend fun doToolcall(prompt: String): String {
        val start = System.currentTimeMillis()
        val chatCompletionRequest = chatCompletionRequest {
            //model = ModelId("gpt-4o-mini") // gpt-4o-mini should be more than enough for our simple questions, cheaper and faster
            model = ModelId("gpt-4o")
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "You are a helpful assistant that selects one of the food categories based on the prompt",
                ),
                ChatMessage(role = ChatRole.User, content = prompt),
            )
            tools {
                foodTypeTool()
            }

            toolChoice = ToolChoice.Auto// or function(DispatchTool.TOOL_NAME)
        }

        val completion = openAI.chatCompletion(chatCompletionRequest)

        // read the answer
        val function = completion.choices[0].message.toolCalls!!.first() as ToolCall.Function
        val destinationArgument: JsonElement? = function.function.argumentsAsJson()[foodTypeProperty]
        val foodType: String? = destinationArgument?.jsonPrimitive?.contentOrNull
        val timeMS = System.currentTimeMillis() - start
        println("RRR foodTypeTool took $timeMS ms")
        return foodType ?: "no answer"
    }
}