package com.salidasoftware.flutterappresearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_native.*

class NativeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native)

        this.setTitle(getString(R.string.title_activity_native))

        buttonExitNativeActivity.setOnClickListener {
            this.finish()
        }
    }
}
