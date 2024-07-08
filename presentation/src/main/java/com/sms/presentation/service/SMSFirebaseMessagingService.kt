package com.sms.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sms.presentation.R

class SMSFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        setNotificationChannel(notificationManager)
        sendNotification(message, notificationManager)
    }

    private fun sendNotification(message: RemoteMessage, notificationManager: NotificationManager) {
        message.notification?.let {
            val notificationBuilder =
                NotificationCompat.Builder(this@SMSFirebaseMessagingService, NOTIFICATION_ID)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ic_sms_white)
                    .setContentText(it.body)
                    .setContentTitle(it.title)

            notificationManager.notify(0, notificationBuilder.build())
        }
    }

    private fun setNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_ID,
            NOTIFICATION_CHANNEL,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val NOTIFICATION_ID = "SMS-notification-id"
        const val NOTIFICATION_CHANNEL = "SMS-notification-channel"
    }
}