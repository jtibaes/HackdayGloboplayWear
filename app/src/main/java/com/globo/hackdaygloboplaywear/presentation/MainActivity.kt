/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.globo.hackdaygloboplaywear.presentation

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound
import androidx.wear.compose.ui.tooling.preview.WearPreviewSmallRound
import androidx.wear.compose.ui.tooling.preview.WearPreviewSquare
import com.globo.hackdaygloboplaywear.R
import com.globo.hackdaygloboplaywear.presentation.components.DrawOdd
import com.globo.hackdaygloboplaywear.presentation.components.TeamOdd
import com.globo.hackdaygloboplaywear.presentation.theme.HackdayGloboplayWearTheme
import com.google.android.gms.wearable.MessageClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private lateinit var messageClient: MessageClient

    val CHANNEL_ID = "CHANNEL_ID"
    val CHANNEL_NAME = "CHANNEL_NAME"

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }

        createChannel(this)
        askNotificationPermission(this)
        notification()
    }

    private fun notification() {

        val intent = Intent(this, MainActivity::class.java)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        val notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as (NotificationManager)
        val notification = Notification.Builder(this)
            //.setLargeIcon(largeIcon)
            .setSmallIcon(R.drawable.ic_flag_placeholder)
            .setContentTitle("My Notification")
            .setContentText("Acompanhe o FLA x FLU")
            .setChannelId(CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.createNotificationChannel(
            NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )

        notificationManager.notify(100, notification)

    }


    private fun sendMessageToPhone() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Replace with your actual path and message
                val sendMessageTask = messageClient.sendMessage(
                    "connected_node_id", // Replace with the node ID of the paired phone
                    "/open_app",
                    "Your message here".toByteArray(Charsets.UTF_8)
                )
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}


@Composable
fun WearApp() {
    HackdayGloboplayWearTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(bottom = 8.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_betano),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                TeamOdd(
                    teamName = "FLA",
                    teamFlag = "https://media.api-sports.io/football/teams/127.png",
                    odd = 1.5f,
                )

                DrawOdd(odd = 0.3f)

                TeamOdd(
                    teamName = "FLU",
                    teamFlag = "https://media.api-sports.io/football/teams/124.png",
                    odd = 0.8f,
                )
            }
        }
    }
}

private fun createChannel(context: Context) {
    // Create channel to show notifications.
    val channelId = context.getString(R.string.default_notification_channel_id)
    val channelName = context.getString(R.string.default_notification_channel_id)
    val notificationManager = context.getSystemService(NotificationManager::class.java)
    notificationManager?.createNotificationChannel(
        NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH,
        ),
    )
}

private fun askNotificationPermission(activity: ComponentActivity) {
    // Declare the launcher at the top of your Activity/Fragment:
    val requestPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    // This is only necessary for API level >= 33 (TIRAMISU)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                activity.applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            // FCM SDK (and your app) can post notifications.
        } else if (shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            )
        ) {
            // TODO: display an educational UI explaining to the user the features that will be enabled
            //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
            //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
            //       If the user selects "No thanks," allow the user to continue without notifications.
        } else {
            // Directly ask for the permission
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}

@WearPreviewLargeRound
@WearPreviewSmallRound
@WearPreviewSquare
@Composable
fun DefaultPreview() {
    WearApp()
}

