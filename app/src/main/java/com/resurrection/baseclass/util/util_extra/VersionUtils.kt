package com.yenen.ahmet.basecorelibrary.base.utility

import android.app.Application
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.media.AudioManager
import android.media.ToneGenerator
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import androidx.documentfile.provider.DocumentFile
import java.io.File
import java.io.IOException


object VersionUtils {
 // https://gitlab.com/commonsguy/cw-android-q/-/blob/vFINAL/AppInstaller/src/main/java/com/commonsware/q/appinstaller/InstallReceiver.kt
    val NAME = "mostly-unused"
    val PI_INSTALL = 3439


    @Throws(IOException::class)
    fun installApk(file: File, app: Application, authority: String,broadcastReceiverClass: Class<Any>){
        val apkUri = FileProvider.getUriForFile(
            app,
            authority,
            file
        )
        installApk(app,apkUri,broadcastReceiverClass)
    }

    @Throws(IOException::class)
    fun installApk(application: Application, apkUri: Uri, broadcastReceiverClass: Class<Any>) {
        val resolver = application.contentResolver
        val installer = application.packageManager.packageInstaller

        resolver.openInputStream(apkUri)?.use { apkStream ->
            val length =
                DocumentFile.fromSingleUri(application, apkUri)?.length() ?: -1
            val params =
                PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL)
            val sessionId = installer.createSession(params)
            val session = installer.openSession(sessionId)

            session.openWrite(NAME, 0, length).use { sessionStream ->
                apkStream.copyTo(sessionStream)
                session.fsync(sessionStream)
                val intent = Intent(application, broadcastReceiverClass)
                val pi = PendingIntent.getBroadcast(
                    application,
                    PI_INSTALL,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                session.commit(pi.intentSender)
            }
        }
    }


    private const val TAG = "AppInstaller"

    class InstallReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            when (val status = intent.getIntExtra(PackageInstaller.EXTRA_STATUS, -1)) {
                PackageInstaller.STATUS_PENDING_USER_ACTION -> {
                    val activityIntent =
                        intent.getParcelableExtra<Intent>(Intent.EXTRA_INTENT)

                    context.startActivity(activityIntent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                }
                PackageInstaller.STATUS_SUCCESS ->
                    ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)
                        .startTone(ToneGenerator.TONE_PROP_ACK)
                else -> {
                    val msg = intent.getStringExtra(PackageInstaller.EXTRA_STATUS_MESSAGE)

                    Log.e(TAG, "received $status and $msg")
                }
            }
        }
    }


}