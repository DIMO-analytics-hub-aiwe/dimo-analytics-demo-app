package com.aiweapps.dinsurance

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aiweapps.dinsurance.presentation.androidDinsuranceApp
import com.aiweapps.dinsurance.utils.oauth.OAuthCallbackHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidDinsuranceApp()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { uri ->
            CoroutineScope(Dispatchers.Main).launch {
                OAuthCallbackHandler.handleAuthCode(
                    code = uri.getQueryParameter("code")
                )
            }
        }
    }
}