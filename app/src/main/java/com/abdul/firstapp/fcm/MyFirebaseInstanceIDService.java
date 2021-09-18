package com.abdul.firstapp.fcm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Arrays;
import java.util.List;

public class MyFirebaseInstanceIDService extends Service {
    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     *
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // These registration tokens come from the client FCM SDKs.
        List<String> registrationTokens = Arrays.asList(
                "YOUR_REGISTRATION_TOKEN_1",
                // ...
                "YOUR_REGISTRATION_TOKEN_n"
        );

// Subscribe the devices corresponding to the registration tokens to the
// topic.
        //TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(registrationTokens, topic);
// See the TopicManagementResponse reference documentation
// for the contents of response.
        //System.out.println(response.getSuccessCount() + " tokens were subscribed successfully");
        return null;
    }
}
