package com.abdul.firstapp

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast

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
                bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE)
            }
            R.id.btnStop -> {
                var serviceIntent1:Intent = Intent(this,MyService::class.java)
                stopService(serviceIntent1)
            }
        }
    }

    private lateinit var mService: MyService

    //serviceConnection like a contract
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, addBinder: IBinder?) {
            val binder = addBinder as MyService.LocalBinder
            mService = binder.getService()  //i am not creating a new object instead i am binding to an existing object and consuming its services
            var result = mService.add(10,25)
            Toast.makeText(applicationContext,"result = "+result,Toast.LENGTH_SHORT).show()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
        }
    }
}