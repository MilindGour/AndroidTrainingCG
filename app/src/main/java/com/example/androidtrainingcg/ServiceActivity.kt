package com.example.androidtrainingcg

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.*
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {

    lateinit var serviceIntent: Intent
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        btnStartService.setOnClickListener {
            // start the service
            serviceIntent = Intent(this, MyService::class.java)
            startService(serviceIntent)
        }

        btnStopService.setOnClickListener {
            if (!this::serviceIntent.isInitialized) {
                serviceIntent = Intent(this, MyService::class.java)
            }
            stopService(serviceIntent)
        }

        btnStartSound.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        }
        btnStopSound.setOnClickListener {
            if (this::mediaPlayer.isInitialized) {
                mediaPlayer.stop()
            }
        }

        registerNetworkCallback(applicationContext)
    }

    private fun registerNetworkCallback(context: Context) {
        val connManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()
        val networkCallback: ConnectivityManager.NetworkCallback = object: ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d("WIFI_CONN", "Wifi Connected")
                Toast.makeText(context, "WiFi connected", Toast.LENGTH_SHORT).show()

                val wifiManager: WifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager
                val SSID: String = wifiManager.connectionInfo.ssid
                Log.d("WIFI_CONN", "SSID: $SSID")

            }

            override fun onLost(network: Network) {
                super.onLost(network)
                Log.d("WIFI_CONN", "Wifi Disconnected")
                Toast.makeText(context, "WiFi disconnected", Toast.LENGTH_SHORT).show()
            }
        }
        connManager.registerNetworkCallback(networkRequest, networkCallback)
    }

}
