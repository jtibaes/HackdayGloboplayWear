/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.globo.hackdaygloboplaywear.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound
import androidx.wear.compose.ui.tooling.preview.WearPreviewSmallRound
import androidx.wear.compose.ui.tooling.preview.WearPreviewSquare
import com.globo.hackdaygloboplaywear.R
import com.globo.hackdaygloboplaywear.presentation.components.DrawOdd
import com.globo.hackdaygloboplaywear.presentation.components.TeamOdd
import com.globo.hackdaygloboplaywear.presentation.theme.HackdayGloboplayWearTheme
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {



    private lateinit var messageClient: MessageClient

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        messageClient = Wearable.getMessageClient(this)

        setTheme(android.R.style.Theme_DeviceDefault)
        createNotificationChannel(this)
        showNotification(this)

        builder.build()
        setContent {
            WearApp("Rebeca Andrade") {
                // sendMessageToPhone()
               // builder.build()
                Toast.makeText(this, "TESTe", Toast.LENGTH_LONG).show()
                showNotification(this)

            }
        }
    }

    var builder = NotificationCompat.Builder(this, "chanelId")
        // .setSmallIcon(R.drawable.notification_icon)
        .setContentTitle("TESTE")
        .setContentText("cdfd")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

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

    private fun createNotificationChannel(context: Context) {
        val channelId = "your_channel_id"
        val channelName = "Your Channel Name"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    private fun showNotification(context: Context) {
        val notificationId = 1

        val notificationBuilder = NotificationCompat.Builder(context, "your_channel_id")
            .setSmallIcon(R.drawable.splash_icon) // Replace with your icon
            .setContentTitle("Notification Title")
            .setContentText("Notification content goes here.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // Show the notification
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
    }


@Composable
fun WearApp(greetingName: String, onClickGP: () -> kotlin.Unit) {
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
            TimeText()
            Greeting(greetingName = greetingName, onClickGP)
        }
    }
}

@Composable
fun Greeting(greetingName: String, onClickGP: () -> kotlin.Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 20.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFFDB5103),
            text = stringResource(R.string.hello_world, greetingName)
        )
        Button(
            modifier = Modifier
                .padding(top = 5.dp)
                .size(ButtonDefaults.LargeIconSize),
            onClick = {
                onClickGP
            },

            ) {
            Text(text = "GP")
        }
        Button(
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
            onClick = { onClickGP },
        ) {
            Text(text = "Bets")
        }

    }

}

@WearPreviewLargeRound
@WearPreviewSmallRound
@WearPreviewSquare
@Composable
fun DefaultPreview() {
    WearApp("Preview Android", {})
}