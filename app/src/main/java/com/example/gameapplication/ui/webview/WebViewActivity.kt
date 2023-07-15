package com.example.gameapplication.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.application.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState?.let {
            binding.webView.restoreState(savedInstanceState)
        }
        showWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showWebView() {
        val webSettings = binding.webView.settings

        binding.webView.apply {
            loadUrl(GOOGLE_URL)
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }

        webSettings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_DEFAULT
        }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        finish()
                    }
                }
            })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webView.saveState(outState)
    }

    companion object {
        private const val GOOGLE_URL = "https://www.google.com"
    }
}