package com.example.health_app


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.health_app.ui.theme.Health_APPTheme

// --- Imports from the ZOLL SDK. Adjust package names if needed. ---

import com.zoll.zoxseries.ZOXSeries
import com.zoll.zoxseries.DeviceBrowser
import com.zoll.zoxseries.DeviceApi
import com.zoll.zoxseries.callback.DevicesBrowserCallback
import com.zoll.zoxseries.callback.CaseCallback
import com.example.health_app.ui.theme.Health_APPTheme
import com.zoll.zoxseries.model.XSeriesDevice
import com.zoll.zoxseries.model.ZOXSeriesException


class Zoll_OS_Logic_One: ComponentActivity() {

    var browser = ZOXSeries.getDeviceBrowser()
    var deviceApi = ZOXSeries.getDeviceApi()
    var caseParser = ZOXSeries.getCaseParser()
    var zoxSeries = ZOXSeries.getReportGenerator()
//    var callback = DevicesBrowserCallback.
//    var vitalSigns = callback.getVitalSigns().getVitalSigns()
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val allGranted = permissions.entries.all { it.value }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        ZOXSeries.initialize(this)
        enableEdgeToEdge()

    }

    override fun onStart() {
        super.onStart()
        // onStart: Activity is about to become visible.
        val myDevicesBrowserCallback = object : DevicesBrowserCallback {
             fun onDeviceFound(device: DeviceApi) {
                // Handle device found
                Log.d("DeviceBrowser", "Device found: ${device.toString()}")
            }

             fun onDeviceLost(device: DeviceApi) {
                // Handle device lost
                Log.d("DeviceBrowser", "Device lost: ${device.toString()}")
            }

             fun onBrowserStarted() {
                // Handle browser started
                Log.d("DeviceBrowser", "Browser started")
            }

             fun onBrowserStopped() {
                // Handle browser stopped
                Log.d("DeviceBrowser", "Browser stopped")
            }

             fun onBrowserError(error: String) {
                // Handle browser error
                Log.e("DeviceBrowser", "Browser error: $error")
            }

            override fun onXSeriesDeviceFound(p0: XSeriesDevice) {
                // Handle XSeries device found
                Log.d("DeviceBrowser", "XSeries device found: ${p0.toString()}")
                // You can access other properties of the XSeriesDevice here, e.g.,
                // device.serialNumber, device.model, etc.
            }

            override fun onXSeriesDeviceLost(p0: XSeriesDevice) {
                // Handle XSeries device lost
                Log.d("DeviceBrowser", "XSeries device lost: ${p0.toString()}")
            }

            override fun onXSeriesBrowseError(p0: ZOXSeriesException) {
                Log.e("DeviceBrowser", "ZoXSeries browse error: ${p0.toString()}")
            }
        }
        browser.start(myDevicesBrowserCallback)
        browser.stop()
    }
    override fun onStop(){
        browser.stop()
        super.onStop()

    }



}
