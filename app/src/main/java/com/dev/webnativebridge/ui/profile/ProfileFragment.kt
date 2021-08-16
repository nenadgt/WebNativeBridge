package com.dev.webnativebridge.ui.profile

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dev.webnativebridge.R
import com.dev.webnativebridge.bridge.WebBridge
import com.dev.webnativebridge.adapter.setUrl
import com.dev.webnativebridge.databinding.ProfileFragmentBinding
import com.dev.webnativebridge.model.Profile

class ProfileFragment : Fragment(), WebBridge.WebBridgeInterface {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: ProfileFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.lifecycleOwner = this
        binding.profileViewModel = viewModel

        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.setUrl(WebBridge.webViewUrl)

        val webSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.progressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        }

        val webInterface = WebBridge(this)

        binding.webView.addJavascriptInterface(webInterface, "WebBridge")

        binding.sendData.setOnClickListener {

            val profile = Profile(
                binding.nameInput.text.toString(),
                binding.locationInput.text.toString(),
                binding.ageInput.text.toString()
            )

            webInterface.sendDataToWebView(profile.encodeData(), binding.webView)
        }

        return binding.root
    }

    override fun recievedDataFromWeb(jsonData: String) {
        viewModel.setProfileDataFromWeb(jsonData)
    }
}