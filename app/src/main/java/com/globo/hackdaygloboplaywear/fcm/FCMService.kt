package com.globo.hackdaygloboplaywear.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.globo.hackdaygloboplaywear.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.w("FCMService", "=======================")
        Log.w("FCMService", "=======================")
        Log.w("FCMService", "=======================")
        Log.w("FCMService", "Refreshed token: $token")
        Log.w("FCMService", "=======================")
        Log.w("FCMService", "=======================")
        Log.w("FCMService", "=======================")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // Extract message data
        val title = message.data["title"]
        val body = message.data["body"]
        val imageUrl = message.data["imageUrl"]
        val clickAction = message.data["click_action"]

        // Create intent for notification click action
        val intent = Intent(clickAction).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        // Build notification
        val notificationBuilder = NotificationCompat.Builder(this, baseContext.getString(R.string.default_notification_channel_id))
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        // Load image if available
//        imageUrl?.let {
//            val bitmap = BitmapFactory.decodeStream(URL(imageUrl).openConnection().getInputStream())
//            notificationBuilder.setLargeIcon(bitmap)
//            notificationBuilder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
//        }

        // Show notification
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())
    }
}