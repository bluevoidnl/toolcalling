import com.aallam.openai.api.chat.ToolBuilder
import com.aallam.openai.api.core.Parameters
import kotlinx.serialization.json.add
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject

const val foodTypeFunctionName = "getFoodType"
const val foodTypeProperty = "foodType"

private val params = Parameters.buildJsonObject {
    put("type", "object")
    putJsonObject("properties") {
        putJsonObject(foodTypeProperty) {
            put("type", "string")
            put(
                "description",
                "The category!! of the food the user mentions. Choose one of these options: Fruit, Vegetable," +
                        "Nut, Dairy, Beverage,Meat,Carbohydrates,Other-edible,Non-edible" +
                        "\nUse Other-edible if non of the categories fit." +
                        "\nUse non-edible is poisonous or if it does not contain nutrients." +
                        "Do not mention the food itself!" // this last line should not be needed, see what happens if you leave it out
            )
        }
        putJsonObject("unit") {
            put("type", "string")
            putJsonArray("enum") {
                add("Fruit")
                add("Vegetable")
                add("Nut")
                add("Dairy")
                add("Beverage")
                add("Meat")
                add("Carbohydrates")
                add("Other-edible")
                add("Non-edible")
            }
        }
    }
    putJsonArray("required") {
        add(foodTypeProperty)
    }
}

fun ToolBuilder.foodTypeTool() {
    function(
        name = foodTypeFunctionName,
        description = "the type of the first food that the user mentions",
        parameters = params,
    )
}