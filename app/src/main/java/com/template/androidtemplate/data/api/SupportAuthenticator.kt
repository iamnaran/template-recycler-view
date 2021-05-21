package com.template.androidtemplate.data.api

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.internal.ignoreIoExceptions
import javax.inject.Inject

class SupportAuthenticator @Inject constructor() :
    Authenticator {
    var mCounter: Int = 0

    override fun authenticate(route: Route?, response: Response): Request? {

        if (mCounter++ > 0) {

            return null
        }

        var requestAvailable: Request? = null

        try {
            requestAvailable = response.request.newBuilder()
                .addHeader(
                    "Authorization", "your_token/api_keys"
                )
                .build()
            return requestAvailable
        } catch (ex: Exception) {
            ignoreIoExceptions { }
        }
        return requestAvailable

    }
}