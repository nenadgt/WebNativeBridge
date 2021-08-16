package com.dev.webnativebridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.webnativebridge.ui.profile.ProfileFragment

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment.newInstance())
                    .commitNow()
        }
    }
}