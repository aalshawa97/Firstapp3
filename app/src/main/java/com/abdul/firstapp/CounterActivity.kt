package com.abdul.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.provider.AlarmClock
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class CounterActivity : AppCompatActivity() {
    //private val LOG_TAG: String = "CounterActivity"
    var TAG = CounterActivity::class.java.simpleName
    lateinit var etContact: EditText
    lateinit var tvCounter: TextView
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val LOG_TAG = "onCreate"
        Log.d(LOG_TAG, "------")
        Log.d(LOG_TAG, "onCreate")
        setContentView(R.layout.activity_counter)
        tvCounter = findViewById(R.id.tvCounter)
        etContact = findViewById(R.id.contactID)
        etContact.setText("0")
        //var name = intent.getStringExtra("nkey")
        //tvCounter.setText(name)
    }

    override fun onStart() {
      super.onStart()
      Log.e(TAG, "onStart")

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token: String = task.getResult().toString()

                // Log and toast
                //val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, token)
                Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
            })
    }

  override fun onPause() {
      super.onPause()
      Log.e(TAG, "onPause")
  }

  override fun onStop() {
      super.onStop()
      Log.e(TAG, "onStop")
  }

    fun updateCounter(view: android.view.View) {
        var seconds = Integer.parseInt(etContact.text.toString())
        tvCounter.setText("" + seconds)
        startTimer("sample",seconds)
        Log.d("CounterActivity", "updateCounter: current value" + counter)
    }

    fun startTimer(message: String, seconds: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_LENGTH, seconds)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun showToast(view: android.view.View) {
        var contact = etContact.text.toString()
        var intent = Intent()
        intent.putExtra("ckey", contact)
        setResult(RESULT_OK)
        updateCounter(view)
        tvCounter.setText("" + counter)
        Toast.makeText(this, "" + counter, Toast.LENGTH_SHORT).show()
        Log.d("CounterActivity", "updateCounter: current value")
    }

    fun returnReply(){
        Log.d(TAG, "End counter activity")
    }
}