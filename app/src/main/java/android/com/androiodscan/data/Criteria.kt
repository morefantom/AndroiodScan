package android.com.androiodscan.data


data class Criteria (
    var id: Int? = null,
    var response: Int? = null,
    var variable: Map<String,Variable>? = null,
    var text: String? = null,
    var type: String? = null
)
