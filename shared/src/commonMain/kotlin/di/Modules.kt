package di

import com.myapplication.Database
import com.myapplication.DatabaseDriverFactory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import models.NewsApiError
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindConstant
import org.kodein.di.bindSingleton
import org.kodein.di.provider
import util.CustomException

val diContainer = DI {
    //constants
    bindConstant("apiKey") { "8be0ef4b8253433e857342ba877641f8" }

    bind {
        provider {
            HttpClient {
                expectSuccess = false

                defaultRequest {
                    url("https://newsapi.org/v2/")
                }

                install(ContentNegotiation) {
                    json()
                }

                HttpResponseValidator {
                    handleResponseExceptionWithRequest { exception, _ ->
                        val clientException = exception as? ClientRequestException
                            ?: return@handleResponseExceptionWithRequest
                        val exceptionResponse = clientException.response
                        val error = exceptionResponse.body<NewsApiError>()
                        throw CustomException(message = error.message.orEmpty())
                    }
                }
            }
        }
    }

    bindSingleton { Database(DatabaseDriverFactory) }
}