package ru.wildberries.di

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.wb.data.di.dataModule
import ru.wb.domain.di.domainModule
import ru.wildberries.util.VerifyPinCodeNotificationService

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    domainModule,
                    dataModule
                )
            )
        }
        createNotificationChannel()
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                VerifyPinCodeNotificationService.VERIFY_PIN_CODE_NOTIFICATION_CHANNEL_ID,
                VerifyPinCodeNotificationService.VERIFY_PIN_CODE_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description =
                VerifyPinCodeNotificationService.VERIFY_PIN_CODE_NOTIFICATION_CHANNEL_DESCRIPTION
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}