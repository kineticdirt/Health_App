//package com.example.health_app
//
//import android.Manifest
//import android.bluetooth.BluetoothAdapter
//import android.content.Context
//import android.content.pm.PackageManager
//import android.net.wifi.WifiManager
//import android.os.Build
//import android.os.Bundle
//import android.os.SystemClock
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import com.example.health_app.databinding.FragmentFirstBinding
//
///**
// * A simple [Fragment] subclass as the default destination in the navigation.
// */
//class FirstFragment : Fragment() {
//
//    private var _binding: FragmentFirstBinding? = null
//    // This property is only valid between onCreateView and onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: android.view.LayoutInflater, container: android.view.ViewGroup?,
//        savedInstanceState: Bundle?
//    ): android.view.View {
//        _binding = FragmentFirstBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
//
//        // Update the status text view with various system stats.
//        updateStatusDisplay()
//    }
//
//    /**
//     * Gather and display status information such as Bluetooth status, Wi-Fi status,
//     * permission statuses, device name, and uptime.
//     */
//    private fun updateStatusDisplay() {
//        val statusBuilder = StringBuilder()
//
//        // --- Bluetooth Data ---
//        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
//        val bluetoothStatus = if (bluetoothAdapter?.isEnabled == true) "Enabled" else "Disabled"
//        val deviceName = bluetoothAdapter?.name ?: "Unknown"
//        statusBuilder.append("Bluetooth Status: $bluetoothStatus\n")
//        statusBuilder.append("Device Name: $deviceName\n\n")
//
//        // --- Wi-Fi Data ---
//        val wifiManager = requireContext().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        val wifiStatus = if (wifiManager.isWifiEnabled) "Enabled" else "Disabled"
//        statusBuilder.append("Wi-Fi Status: $wifiStatus\n\n")
//
//        // --- Permission Status ---
//        val permissions = mutableListOf<String>().apply {
//            // Bluetooth permissions depending on SDK version
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                add(Manifest.permission.BLUETOOTH_SCAN)
//                add(Manifest.permission.BLUETOOTH_CONNECT)
//            } else {
//                add(Manifest.permission.BLUETOOTH)
//                add(Manifest.permission.BLUETOOTH_ADMIN)
//            }
//            // Wi-Fi permission depending on SDK version
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                add(Manifest.permission.NEARBY_WIFI_DEVICES)
//            } else {
//                add(Manifest.permission.ACCESS_FINE_LOCATION)
//            }
//        }
//        statusBuilder.append("Permissions:\n")
//        permissions.forEach { permission ->
//            val granted = if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED)
//                "Granted" else "Denied"
//            statusBuilder.append("  $permission: $granted\n")
//        }
//        statusBuilder.append("\n")
//
//        // --- Uptime ---
//        val uptimeMillis = SystemClock.uptimeMillis()
//        val seconds = uptimeMillis / 1000 % 60
//        val minutes = uptimeMillis / (1000 * 60) % 60
//        val hours = uptimeMillis / (1000 * 60 * 60)
//        statusBuilder.append("Uptime: %d:%02d:%02d (hh:mm:ss)".format(hours, minutes, seconds))
//
//        // Update the TextView with the compiled status information.
//        binding.textviewFirst.text = statusBuilder.toString()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
package com.example.health_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.health_app.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
