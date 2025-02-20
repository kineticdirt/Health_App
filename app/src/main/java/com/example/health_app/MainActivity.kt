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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

// --- Imports from the ZOLL SDK. Adjust package names if needed. ---



class MainActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Check and request permissions when the activity is created.
        checkAndRequestPermissions()
    }

    override fun onStart() {
        super.onStart()
        // onStart: Activity is about to become visible.
    }

    override fun onResume() {
        super.onResume()
        // onResume: Activity has become visible (it is now "resumed").
    }

    override fun onPause() {
        super.onPause()
        // onPause: Another activity is taking focus (this activity is about to be "paused").
    }

    override fun onStop() {
        super.onStop()
        // onStop: The activity is no longer visible.
    }

    override fun onDestroy() {
        super.onDestroy()
        // onDestroy: Cleanup any resources before the activity is destroyed.
    }

    /**
     * Checks if the necessary Bluetooth and Wi-Fi permissions are granted.
     * For Bluetooth:
     *  - Use BLUETOOTH_SCAN and BLUETOOTH_CONNECT on Android 12+ (SDK 31+).
     * For Wi-Fi:
     *  - Use NEARBY_WIFI_DEVICES on Android 13+ (SDK 33+); otherwise fall back to ACCESS_FINE_LOCATION.
     */
    private fun checkAndRequestPermissions() {
        val permissions = mutableListOf<String>()

        // Add Bluetooth permissions.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissions.add(Manifest.permission.BLUETOOTH_SCAN)
            permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
        } else {
            permissions.add(Manifest.permission.BLUETOOTH)
            permissions.add(Manifest.permission.BLUETOOTH_ADMIN)
        }

        // Add Wi-Fi permission.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 (SDK 33) and above.
            permissions.add(Manifest.permission.NEARBY_WIFI_DEVICES)
        } else {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        // Filter out already granted permissions.
        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        // Request the missing permissions.
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    /**
     * Callback for the result from requesting permissions.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val allGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (!allGranted) {
                // Optionally handle the case where permissions are not granted.
                // e.g., show a message or disable features that depend on these permissions.
            }
        }
    }
}

    /**
     * A simple composable greeting for your UI.
     */


//    @Preview(showBackground = true)
//    @Composable
//    fun GreetingPreview() {
//        Health_APPTheme {
//            Greeting("Android")
//        }
//    }

