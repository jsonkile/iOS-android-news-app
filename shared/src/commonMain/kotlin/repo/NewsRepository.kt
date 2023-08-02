package repo

import models.Headline

interface NewsRepository {
    suspend fun fetchHeadlines(countryCode: String, pageSize: Int): List<Headline>
}