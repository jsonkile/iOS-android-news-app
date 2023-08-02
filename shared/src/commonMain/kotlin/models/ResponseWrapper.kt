package models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWrapper<T>(
    @SerialName("articles")
    val articles: List<T>? = emptyList(),
    @SerialName("status")
    val status: String? = "",
    @SerialName("totalResults")
    val totalResults: Int? = 0
)