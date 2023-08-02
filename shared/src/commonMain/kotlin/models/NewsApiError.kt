package models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsApiError(
    @SerialName("code")
    val code: String? = "",
    @SerialName("message")
    val message: String? = "",
    @SerialName("status")
    val status: String? = ""
)