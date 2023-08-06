package com.myapplication

import models.Headline

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllHeadlines()
        }
    }

    internal fun getAllHeadlines(): List<Headline> {
        return dbQuery.selectAllHeadlinesInfo { author, content, description, urlToImage, url, title, sourceName, publishedAt ->
            Headline(
                author = author,
                content = content,
                description = description,
                urlToImage = urlToImage,
                url = url,
                title = title,
                source = Headline.Source(name = sourceName),
                publishedAt = publishedAt
            )
        }.executeAsList()
    }

    internal fun insertHeadlines(headlines: List<Headline>) {
        dbQuery.transaction {
            headlines.forEach { headline ->
                dbQuery.insertHeadline(
                    author = headline.author,
                    content = headline.content.orEmpty(),
                    description = headline.description,
                    urlToImage = headline.urlToImage,
                    url = headline.url,
                    title = headline.title.orEmpty(),
                    sourceName = headline.source?.name.orEmpty(),
                    publishedAt = headline.publishedAt
                )
            }
        }
    }
}