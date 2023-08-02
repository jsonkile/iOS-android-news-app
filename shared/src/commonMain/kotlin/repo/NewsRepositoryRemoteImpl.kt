package repo

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import models.Headline
import models.ResponseWrapper

class NewsRepositoryRemoteImpl(private val client: HttpClient, private val apiKey: String) :
    NewsRepository {
    override suspend fun fetchHeadlines(countryCode: String, pageSize: Int): List<Headline> {
        return client.get("top-headlines?country=$countryCode&pageSize=$pageSize&apiKey=$apiKey")
            .body<ResponseWrapper<Headline>>().articles.orEmpty()
    }
}