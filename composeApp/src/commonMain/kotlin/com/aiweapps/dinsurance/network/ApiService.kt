package com.aiweapps.dinsurance.network

import com.aiweapps.dinsurance.data.ContextHolder
import com.aiweapps.dinsurance.data.dto.TokenResponseDTO
import com.aiweapps.dinsurance.utils.json
import com.aiweapps.dinsurance.utils.launchBrowser
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType

class ApiService(
    private val client: HttpClient,
) {

    private companion object {
        const val CLIENT_ID = "0x4C619fa01e84Aa0F9165FB16FdAAFCA309c06711" //TODO should be hidden
        const val REDIRECT_URI = "dinsurance://login"
        const val GRAND_TYPE_FOR_GETTING_TOKEN = "authorization_code"
    }

    fun launchOAuth(contextHolder: ContextHolder) {
        launchBrowser(
            contextHolder = contextHolder,
            url = "https://auth.dimo.zone/auth?client_id=" +
                    CLIENT_ID +
                    "&redirect_uri=$REDIRECT_URI" +
                    "&scope=openid email" +
                    "&response_type=code"
        )
    }

    suspend fun fetchTokens(
        code: String,
    ): TokenResponseDTO {
        val response = client.safeRequest<String, HttpExceptions> {
            url(urlString = "https://auth.dimo.zone/token")
            method = HttpMethod.Post
            contentType(type = ContentType.Application.FormUrlEncoded)
            setBody(
                body = FormDataContent(
                    formData = Parameters.build {
                        append(name = "client_id", value = CLIENT_ID)
                        append(name = "grant_type", value = GRAND_TYPE_FOR_GETTING_TOKEN)
                        append(name = "code", value = code)
                        append(name = "redirect_uri", value = REDIRECT_URI)
                    }
                )
            )
        }
        if (response is ApiResponse.Success) {
            return json.decodeFromString(string = response.body)
        }
        throw parseException(response = response)
    }

    private fun parseException(response: ApiResponse<*, HttpExceptions>) =
        when (response) {
            is ApiResponse.Error.HttpError -> Throwable(message = "${response.errorMessage}")
            is ApiResponse.Error.GenericError -> Throwable(message = "${response.errorMessage}")
            is ApiResponse.Error.SerializationError -> Throwable(message = "${response.errorMessage}")
            else -> Throwable(message = "Unknown error")
        }
}