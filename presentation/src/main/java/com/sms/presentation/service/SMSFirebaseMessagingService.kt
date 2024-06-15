package com.sms.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class SMSFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // todo: 추후 서버 이용하게 되면 신규 토큰 발급시 서버로 전달필요
        Log.d("onNewToken", token)
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
                    .setContentText(it.body)
                    .setContentTitle(it.title)
                    .setSmallIcon(com.sms.design_system.R.drawable.ic_sms)

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