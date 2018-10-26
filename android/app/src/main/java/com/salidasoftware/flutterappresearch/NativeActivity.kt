package com.salidasoftware.flutterappresearch

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_native.*
import android.content.Intent



class NativeActivity : AppCompatActivity() {

    companion object {
        val KEY_DEFAULT_VALUE = "DEFAULT_VALUE"
        val KEY_RESULT_VALUE = "RESULT_VALUE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native)

        this.setTitle(getString(R.string.title_activity_native))

        var defaultValue = this.intent.getIntExtra(KEY_DEFAULT_VALUE, 0)
        Log.d("NativeActivity", "~~ start value = " + defaultValue)
        seekBarNativeValue.progress = defaultValue

        buttonExitNativeActivity.setOnClickListener {
            Log.d("NativeActivity", "~~ end value = " + seekBarNativeValue.progress)
            val data = Intent()
            data.putExtra(KEY_RESULT_VALUE, seekBarNativeValue.progress)
            setResult(Activity.RESULT_OK, data)
            this.finish()
        }
    }
}
