package com.example.nativeapp.test_native_app

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.MethodChannel.MethodCallHandler


class MainActivity: FlutterActivity() {

    private val channel="test_native_channel_name"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channel)
            .setMethodCallHandler { call: MethodCall, result: Result ->
                if (call.method == "getMessageMethodNative") {
                    var mess=getMessageMethodNative();
                    if(mess.isNotEmpty()){
                        result.success(mess)
                    }
                    else{
                        result.error("UNAVAILABLE","message Form kotline is Empty",null)
                    }
                }
                else if (call.method == "getMap") {
                    var map=getMap();
                        result.success(map)
                }

                else {
                    result.notImplemented()
                }
            }
    }
    private fun getMessageMethodNative():String{
        return "message get from kotlin";
    }
    private fun getMap(): Map<String, String> {
        val myMap = hashMapOf(
            "name" to "Bard",
            "age" to "1.0",  // Dynamic allows for different data types
            "skills" to "summarization"
        )
        return myMap
    }
}
