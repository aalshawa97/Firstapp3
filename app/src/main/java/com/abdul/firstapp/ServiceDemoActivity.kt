package com.abdul.firstapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat

class ServiceDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_demo)
    }

    fun showNotification(view: View) {
        createNotificationChannel()
        val intent = Intent(this, CounterActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        var builder = NotificationCompat.Builder(this, "simple notifs")
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setContentTitle("some title")
            .setContentText("some content text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        var notification: Notification = builder.build()
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(123,notification)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("simple notifs", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun serviceHandler(view: View) {
        when(view.id){
            R.id.btnStartServ -> {
                var serviceIntent:Intent = Intent(this,MyService::class.java)
                serviceIntent.putExtra("musicfile","alfatiha.mp3")
                startService(serviceIntent)
                bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE)
            }
            R.id.btnStop -> {
                var serviceIntent1:Intent = Intent(this,MyService::class.java)
                stopService(serviceIntent1)
                //super.finish()
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