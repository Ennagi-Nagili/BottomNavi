package com.annaginagili.bottomnavi

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class TestActivity : AppCompatActivity() {
    lateinit var send: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        send = findViewById(R.id.send)

        send.setOnClickListener {
            permissionLauncherSingle.launch(android.Manifest.permission.SEND_SMS)
        }
    }

    private val permissionLauncherSingle = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val intent = Intent(this, MainActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE)

            val sms = SmsManager.getDefault()
            sms.sendTextMessage("+994999700101", null, "Hello", pi, null)
            Log.e("hello", "yes")
        }
    }
}