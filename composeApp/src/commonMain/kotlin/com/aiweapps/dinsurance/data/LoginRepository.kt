package com.aiweapps.dinsurance.data

import com.aiweapps.dinsurance.data.dto.TokenResponseDTO
import com.aiweapps.dinsurance.utils.json
import com.aiweapps.dinsurance.utils.launchBrowser
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitForm
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType

class LoginRepository(
    private val client: HttpClient,
) {

    companion object {
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

    suspend fun exchangeCodeForToken(
        code: String,
    ): TokenResponseDTO {
        val response = client.submitForm(
            url = "https://auth.dimo.zone/token",
            formParameters = Parameters.build {
                append("client_id", CLIENT_ID)
                append("grant_type", "authorization_code")
                append("code", code)
                append("redirect_uri", REDIRECT_URI)
            }
        ) {
            contentType(ContentType.Application.FormUrlEncoded)
        }
        return json.decodeFromString<TokenResponseDTO>(response.bodyAsText())
    }

}