package android.com.androiodscan.data


data class Variable (
    var id: Int ?= null,
    var type: String? = null,
    var study_type: String? = null,
    var parameter_name: String? = null,
    var min_value: Int ?= null,
    var max_value: Int?= null,
    var default_value: Int?= null,
    var values: List<Float>?= null
)
