package com.example.androidtrainingcg

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        btnGetPermission.setOnClickListener {
            Log.d("PERMISSION", "Get Permission From User clicked")
            if (isPermissionGranted(android.Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Permission already given by user", Toast.LENGTH_SHORT).show()
                Log.d("PERMISSION", "Permission granted already")
            } else {
                requestForPermission(android.Manifest.permission.CAMERA)
                Log.d("PERMISSION", "Getting Permission")
            }
        }

        btnOpenCamera.setOnClickListener {
            val camIntent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(camIntent)
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return PermissionChecker.checkCallingOrSelfPermission(this, permission) == PermissionChecker.PERMISSION_GRANTED
    }

    private fun requestForPermission(permission: String) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), 7749)

        ActivityCompat.OnRequestPermissionsResultCallback { requestCode, permissions, grantResults ->
            Log.d("PERMISSION", "Inside the permission callback")
            if (requestCode == 7749) {
                if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}