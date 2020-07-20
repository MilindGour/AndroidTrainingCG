package com.example.androidtrainingcg

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class SampleBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Toast.makeText(context, "Boot has been completed", Toast.LENGTH_LONG).show()
        }

        if (Intent.ACTION_AIRPLANE_MODE_CHANGED == intent.action) {
            Toast.makeText(context, "Airplane mode changed", Toast.LENGTH_LONG).show()
        }
    }
}
