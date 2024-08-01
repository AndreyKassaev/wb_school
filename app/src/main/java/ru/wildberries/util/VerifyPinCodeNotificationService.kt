package ru.wildberries.util

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import ru.wildberries.R
import kotlin.random.Random

class VerifyPinCodeNotificationService(
    private val context: Context
) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @SuppressLint("LaunchActivityFromNotification", "ObsoleteSdkInt")
    fun showNotification(pinCode: String) {

        val notification =
            NotificationCompat.Builder(context, VERIFY_PIN_CODE_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.splash)
                .setContentTitle("Pin code")
                .setContentText(pinCode)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun cancelAllNotifications() {
        notificationManager.cancelAll()
    }

    companion object {
        const val VERIFY_PIN_CODE_NOTIFICATION_CHANNEL_ID =
            "VERIFY_PIN_CODE_NOTIFICATION_CHANNEL_ID"
        const val VERIFY_PIN_CODE_NOTIFICATION_CHANNEL_NAME = "Verify pin code channel"
        const val VERIFY_PIN_CODE_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Used for displaying verifying Pin Code"
    }
}