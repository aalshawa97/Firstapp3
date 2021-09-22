package com.abdul.firstapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import com.abdul.firstapp.util.Constants
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class AlarmService : Service() {
    private val alarmManager: AlarmManager? = getSystemService(Context.ALARM_SERVICE) as AlarmManager?
    val cal_alarm: Calendar = Calendar.getInstance()
    val cal_now: Calendar = Calendar.getInstance()
    val dat = Date()

    fun setExactAlarm(timeInMillis: Long){
        getPendingIntent(
            getIntent().apply {
                action = Constants.ACTION_SET_EXACT_ALARM
                putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)

            }
        )?.let {
            setAlarm(
                timeInMillis,
                it
            )
        }
    }

    fun setCalendarAlarm(cal_alarm: Calendar){
        cal_now.setTime(dat)
        cal_alarm.setTime(dat)
        cal_alarm.set(Calendar.HOUR_OF_DAY, 10)
        cal_alarm.set(Calendar.MINUTE, 43)
        cal_alarm.set(Calendar.SECOND, 0)

        if (cal_alarm.before(cal_now)) {
            cal_alarm.add(Calendar.DATE, 1)
        }
    }


    //Every week
    fun setReptitiveAlarm(timeInMillis: Long, pendingIntent: PendingIntent){
        setCalendarAlarm(cal_alarm)

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), cal_alarm.getTimeInMillis() , pendingIntent)
        }; // Millisec * Second * Minute
    }

    private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent){
        alarmManager?.let {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }
            else{
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    private fun getIntent() = Intent(this, AlarmReceiver::class.java)
    private val seed = AtomicInteger()

    private fun getPendingIntent(apply: Intent): PendingIntent? {
        val broadcast = PendingIntent.getBroadcast(
            this,
            seed.getAndIncrement(),
            getIntent(),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        return broadcast
    }


    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * [android.os.IBinder] is usually for a complex interface
     * that has been [described using
 * aidl]({@docRoot}guide/components/aidl.html).
     *
     *
     * *Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process*.  More information about the main thread can be found in
     * [Processes and
 * Threads]({@docRoot}guide/topics/fundamentals/processes-and-threads.html).
     *
     * @param intent The Intent that was used to bind to this service,
     * as given to [ Context.bindService][android.content.Context.bindService].  Note that any extras that were included with
     * the Intent at that point will *not* be seen here.
     *
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
    /*
    val manager = getSystemService(ALARM_SERVICE) as AlarmManager
    //manager[AlarmManager.RTC_WAKEUP, myCalendar.getTimeInMillis()] = pi

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    */
}