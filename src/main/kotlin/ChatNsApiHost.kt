import com.aallam.openai.api.http.Timeout
import com.aallam.openai.client.OpenAIConfig
import com.aallam.openai.client.OpenAIHost
import kotlin.time.Duration.Companion.seconds

object ChatNsApiHost {
    val HEADERS = mapOf<String, String>("Ocp-Apim-Subscription-Key" to "apiKey")
    const val BASE_URL = "chatns api url"

    val chatNsConfig = OpenAIConfig(
        token = "apiKey, is this needed?",
        timeout = Timeout(socket = 60.seconds),
        headers = HEADERS,
        host = OpenAIHost(baseUrl = BASE_URL, emptyMap())
    )
}