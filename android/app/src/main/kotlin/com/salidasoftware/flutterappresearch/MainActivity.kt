package com.salidasoftware.flutterappresearch

import android.app.Activity
import android.os.Bundle

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.common.MethodChannel
import android.content.Intent
import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.StringCodec
import io.flutter.plugin.common.BasicMessageChannel.Reply
import kotlinx.android.synthetic.main.activity_native.*
import java.lang.annotation.Native


class MainActivity(): FlutterActivity() {
  private val METHOD_CHANNEL = "flutter_app_research.salidasoftware.com/method"

  private val MESSAGE_CHANNEL = "flutter_app_research.salidasoftware.com/message"

  private lateinit var messages : BasicMessageChannel<String>

  val PICK_NATIVE_VALUE = 121
  var pendingResult: MethodChannel.Result? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)

    MethodChannel(flutterView, METHOD_CHANNEL).setMethodCallHandler { call, result ->
      if (call.method == "openNative") {
        var defaultValue = 0
        if (call.arguments != null) {
          val arguments = call.arguments as java.util.HashMap<String, Int>
          arguments.get("counter")?.let {
            defaultValue = it
          }
        }
        // Resolve any existing results that may be hung up???
        pendingResult?.let {
          it.error("EXPIRED", "New result has been created.", null)
        }
        pendingResult = result
        openNative(defaultValue)
      }
    }

    messages = BasicMessageChannel(flutterView, MESSAGE_CHANNEL, StringCodec.INSTANCE)
    messages.setMessageHandler(object : BasicMessageChannel.MessageHandler<String> {
      override fun onMessage(s: String, reply: Reply<String>) {
        messages.send("ECHO : " + s)
        reply.reply("")
      }
    })

  }

  private fun openNative(defaultValue: Int) {
    Log.d("MainActivity", "~~ openNative defaultValue = " + defaultValue)
    val intent = Intent(this, NativeActivity::class.java)
    intent.putExtra(NativeActivity.KEY_DEFAULT_VALUE, defaultValue)
    // this.startActivity(intent)
    startActivityForResult(intent, PICK_NATIVE_VALUE)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    if (requestCode == PICK_NATIVE_VALUE) {
      if (resultCode == Activity.RESULT_OK) {
        val result = data.getIntExtra(NativeActivity.KEY_RESULT_VALUE, 0)
        Log.d("NativeActivity", "~~ result value = " + result)
        pendingResult?.let {
          it.success(result)
        }
        pendingResult = null
      }
    }
  }
}
