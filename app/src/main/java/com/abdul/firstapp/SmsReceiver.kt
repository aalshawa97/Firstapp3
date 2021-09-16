package com.abdul.firstapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context,"Abdullah, you have an sms from Sergio!",Toast.LENGTH_SHORT).show()
        val data = intent.extras
        val pdusObj = data!!.get("pdus") as Array<*>
        for (i in pdusObj.indices) {
            val currentMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                Telephony.Sms.Intents.getMessagesFromIntent(intent)[0]
            else SmsMessage.createFromPdu(pdusObj[0] as ByteArray)


            val phoneNumber = currentMessage.displayOriginatingAddress
            Log.e("receiver", phoneNumber)

            val senderNum = phoneNumber
            Log.e("receiver", senderNum)

            val smsContent = currentMessage.getDisplayMessageBody()
            Log.d("receiver","Message: "+smsContent) // Always max 67 characters!

        }
    }
} 