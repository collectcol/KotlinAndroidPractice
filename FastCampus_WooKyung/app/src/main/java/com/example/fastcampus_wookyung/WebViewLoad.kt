package com.example.fastcampus_wookyung

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebViewRenderProcessClient
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged

class WebViewLoad : AppCompatActivity() {
    private lateinit var url: (EditText)
    private lateinit var btnOpen: (Button)
    private lateinit var webViewLoad: (WebView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_load)



        url = (findViewById<EditText>(R.id.editText_uri))
        val urlPrefix = "http://"
        var finalUrl = ""

        // addTextChangedListener 사용법
        url.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
//                if (!s.toString().contains("http://")) {
//
//                }
                finalUrl = urlPrefix + s.toString()
            }

        })

        // addTextChangedListener 사용법 -> 람다형식
        // 필요한것만 장착가능 <-> 굳이 3개를 위처럼 implement 할 필요없다
        url.doBeforeTextChanged { text, start, count, after -> }
        url.doOnTextChanged { text, start, before, count -> }
        url.doAfterTextChanged {
//            if (!it.toString().contains("http://")) {
//                finalUrl = urlPrefix + it.toString()
//            }
        }

        btnOpen = (findViewById<Button>(R.id.button_open))
        webViewLoad = (findViewById<WebView>(R.id.webView)).apply {
            this.settings.javaScriptEnabled = true
            this.webChromeClient = WebChromeClient()
            this.webViewClient = WebViewClient()
        }

        webViewLoad.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                // true -> 주도권을 가져오지 않는다(우리앱이)
                // false -> 주도권을 가져오겠다 (우리앱)
                // true 이면 우리가 주도권을 가지지 않으니 또 물어보는 창이 뜬다 (intent filter 손드는 창)
                return false
            }
        }

        try{
            Log.d("testt", intent.data!!.toString())
            webViewLoad.loadUrl(
                intent.data!!.toString()

            )
        }catch (e : Exception){

        }

        btnOpen.setOnClickListener {

            // 직접 url 주소 넣어서 하는 방법
//            webViewLoad.loadUrl(finalUrl)

            // 인텐트를 활용 하는 방법
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl))
            startActivity(intent)
        }
    }
}