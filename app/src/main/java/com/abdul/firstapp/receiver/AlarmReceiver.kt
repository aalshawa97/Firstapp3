package com.abdul.firstapp.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.abdul.firstapp.AlarmService
import com.abdul.firstapp.util.Constants
import java.lang.String.format
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class AlarmReceiver: BroadcastReceiver() {
    /**
     * This method is called when the BroadcastReceiver is receiving an Intent
     * broadcast.  During this time you can use the other methods on
     * BroadcastReceiver to view/modify the current result values.  This method
     * is always called within the main thread of its process, unless you
     * explicitly asked for it to be scheduled on a different thread using
     * [android.content.Context.registerReceiver]. When it runs on the main
     * thread you should
     * never perform long-running operations in it (there is a timeout of
     * 10 seconds that the system allows before considering the receiver to
     * be blocked and a candidate to be killed). You cannot launch a popup dialog
     * in your implementation of onReceive().
     *
     *
     * **If this BroadcastReceiver was launched through a &lt;receiver&gt; tag,
     * then the object is no longer alive after returning from this
     * function.** This means you should not perform any operations that
     * return a result to you asynchronously. If you need to perform any follow up
     * background work, schedule a [android.app.job.JobService] with
     * [android.app.job.JobScheduler].
     *
     * If you wish to interact with a service that is already running and previously
     * bound using [bindService()][android.content.Context.bindService],
     * you can use [.peekService].
     *
     *
     * The Intent filters used in [android.content.Context.registerReceiver]
     * and in application manifests are *not* guaranteed to be exclusive. They
     * are hints to the operating system about how to find suitable recipients. It is
     * possible for senders to force delivery to specific recipients, bypassing filter
     * resolution.  For this reason, [onReceive()][.onReceive]
     * implementations should respond only to known actions, ignoring any unexpected
     * Intents that they may receive.
     *
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context, intent: Intent) {
        //var notificationManager = (NotificationManager)
        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME, 0L)
        when(intent.action){
            Constants.ACTION_SET_EXACT_ALARM->{
                //buildNotification(context, "Set Exact Time", convertDate(timeInMillis))
            }
            Constants.ACTION_SET_REPETITVE_ALARM->{
                val cal = Calendar.getInstance().apply {
                    this.timeInMillis = timeInMillis + TimeUnit.DAYS.toMillis(7)
                }
                //AlarmService(context).setReptitiveAlarm(cal.timeInMillis)
                //buildNotification(context, "Set Repetitive Time", convertDate(cal.timeInMillis))
            }
        }
    }

    private fun buildNotification(context:Context, title:String, message:String){

        /*
        Notify
            .with(context)
            .content{
                this.title = title
                this.text = "I got triggered at - $message"
            }
            .show()
        */

    }

    //private fun convertDate(timeInMillis: Long): String =
       //DateFormat.format("dd//MM/yyyy hh:mm:ss", timeInMillis).toString()
}