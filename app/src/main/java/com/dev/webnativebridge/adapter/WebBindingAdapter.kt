package com.dev.webnativebridge.adapter

import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadUrl")
fun WebView.setUrl(url: String) {
    this.loadUrl(url)
}
