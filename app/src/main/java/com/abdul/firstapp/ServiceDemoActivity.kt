package com.abdul.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ServiceDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_demo)
    }

    fun serviceHandler(view: View) {
        when(view.id){
            R.id.btnStartServ -> {
                var serviceIntent:Intent = Intent(this,MyService::class.java)
                serviceIntent.putExtra("musicfile","alfatiha.mp3.mp3")
                startService(serviceIntent)
            }
            R.id.btnStop -> {
                var serviceIntent1:Intent = Intent(this,MyService::class.java)
                stopService(serviceIntent1)
            }
        }
    }
}