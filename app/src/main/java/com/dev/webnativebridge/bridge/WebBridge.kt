package com.dev.webnativebridge.bridge

import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView

class WebBridge(private val webInterface: WebBridgeInterface) {

    companion object {

        const val webViewUrl = "file:///android_asset/sampleweb.html"
    }

    @JavascriptInterface
    fun receiveDataFromWeb(jsonString: String) {

        Log.e("TEST", jsonString)

        webInterface.recievedDataFromWeb(jsonString)
    }

    /**
     * Send data to webview through function sendDataToWebView.
     */
    fun sendDataToWebView(jsonString: String, webView: WebView) {

        Log.e("TEST", jsonString)

        webView.evaluateJavascript(
            "javascript: updateFromNative($jsonString)",
            null
        )
    }

    interface WebBridgeInterface {
        fun recievedDataFromWeb(jsonData: String)
    }
}