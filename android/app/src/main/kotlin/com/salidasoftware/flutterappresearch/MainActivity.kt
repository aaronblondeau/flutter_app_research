package com.salidasoftware.flutterappresearch

import android.os.Bundle

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.common.MethodChannel
import android.content.Intent

class MainActivity(): FlutterActivity() {
  private val CHANNEL = "flutter_app_research.salidasoftware.com/map"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)

    MethodChannel(flutterView, CHANNEL).setMethodCallHandler { call, result ->
      if (call.method == "openMap") {
        val status = openMap()
        result.success(status)
      }
    }
  }

  private fun openMap(): Int {
    val intent = Intent(this, NativeActivity::class.java)
    this.startActivity(intent)
    return 99
  }
}
