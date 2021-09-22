package com.abdul.firstapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import java.util.*
import android.app.NotificationManager as NotificationManager1

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText   //declartion
    lateinit var tvRes: TextView
    lateinit var IVPreviewImage: ImageView
    lateinit var alarmService: AlarmService
    private var imageUri: Uri? = null

    // One Button
    var BSelectImage: Button? = null
    val pickImage = 100
    // constant to compare
    // the activity result code
    var SELECT_PICTURE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var mNotificationManager = (NotificationManager1) getSystemService (NOTIFICATION_SERVICE)
        setContentView(R.layout.activity_main)
        //alarmService = AlarmService()
        /*
        BSelectImage?.setOnClickListener{
            setAlarm()
        }
        */
        /*
        val setExact
        setExact.setOnClickListener{
            setAlarm()
        }
        */
        //Register the UI widgets with their appropriate IDs
        val tag = "Weather updates"
        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.msg_subscribe_failed)
                }
                Log.d(tag, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }

        BSelectImage = findViewById(R.id.BSelectImage)
        IVPreviewImage = findViewById(R.id.imageView)

        // handle the Choose Image button to trigger
        // the image chooser function

        // handle the Choose Image button to trigger
        // the image chooser function
        //BSelectImage.setOnClickListener(View.OnClickListener { imageChooser() })
        etName = findViewById(R.id.etName) //initialization
        tvRes = findViewById(R.id.tvResult)
        title = "KotlinApp"
        BSelectImage?.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
        setAlarm()
        Log.d("MainActivity", "Hello World")
    }

    private fun onCheckedChanged(compoundButton: CompoundButton, isChecked:Boolean) {
        //Declaring two variables.
        //You can also declare it as global.
        //but global variable must be initialized before creating toast otherwise you will get NPE and lead to you application crash
        //Declaring two variables.
        //You can also declare it as global.
        //but global variable must be initialized before creating toast otherwise you will get NPE and lead to you application crash
        val cText = "Setting alarm clock"
        Toast.makeText(getApplicationContext(), cText, Toast.LENGTH_LONG).show();
        if(isChecked)
        {

        }
    }


    private fun setAlarm(){
        Calendar.getInstance().apply {
            DatePickerDialog(
                this@MainActivity,
                0,
                DatePickerDialog.OnDateSetListener{ _, year, month, day ->
                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, day)

                    TimePickerDialog(
                        this@MainActivity,
                        0,
                        TimePickerDialog.OnTimeSetListener{_, hour, min ->
                            this.set(Calendar.HOUR_OF_DAY, hour)
                            this.set(Calendar.MINUTE, min)
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        false
                    ).show()
                },
                this.get(Calendar.YEAR),
                this.get(Calendar.MONTH),
                this.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    // this function is triggered when
    // the Select Image Button is clicked
    fun imageChooser(view: android.view.View) {

        // create an instance of the
        // intent of the type image
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE)
    }


    @Override
    public override fun onStart(){
        super.onStart()
        val LOG_TAG = "MainActivity"
        Log.d(LOG_TAG, "onStart")
    }

    fun clickHandler(view: View) {
        //startCounterActivity()
        //Implicit intent
        var dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:988097932"))
        startActivity(dialIntent)
    }

    private fun startCounterActivity() {
        //Explicit intent -- name of the class to be invoked
        var intent = Intent(this, CounterActivity::class.java)

        var name = etName.text.toString()
        /*
        Toast.makeText(this@MainActivity, name, Toast.LENGTH_LONG).show()
        tvRes.setText("Welcome " + name)
        setContentView(R.layout.logged_in)
        */
        intent.putExtra("nkey", name)
        startActivity(intent)
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            IVPreviewImage.setImageURI(imageUri)
        }
    }

}