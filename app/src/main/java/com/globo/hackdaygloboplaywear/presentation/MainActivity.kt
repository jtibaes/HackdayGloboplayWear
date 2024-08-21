/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.globo.hackdaygloboplaywear.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.Settings.Global.getString
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import androidx.wear.compose.material.TimeText
import com.globo.hackdaygloboplaywear.R
import com.globo.hackdaygloboplaywear.presentation.theme.HackdayGloboplayWearTheme

class MainActivity : ComponentActivity() {

    val NOTIFICATION_ID: Int = 1
    val NOTIFICATION_ID_SECOND: Int = 2
    val NOTIFICATION_ID_THIRD: Int = 3
    val NOTIFICATION_ID_FOURTH: Int = 4
    val NOTIFICATION_ID_GROUP: Int = 777
    val EXTRA_VOICE_REPLY: String = "extra_voice_reply"
    val EXTRA_VOICE_REPLY_CHOICE: String = "extra_voice_reply_choice"
    val GROUP_KEY_EMIALS: String = "group_key_emails"


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        builder.build()
        setContent {
            WearApp("Rebeca Andrade")
        }
    }

    var builder = NotificationCompat.Builder(this, "chanelId")
        // .setSmallIcon(R.drawable.notification_icon)
        .setContentTitle("")
        .setContentText("cdfd")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)


}

@Composable
fun WearApp(greetingName: String) {
    HackdayGloboplayWearTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            TimeText()
            Greeting(greetingName = greetingName)
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
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
            modifier = Modifier.padding(top = 5.dp).size(ButtonDefaults.LargeIconSize),
            onClick = { },
            
        ) {
            Text(text = "GP")
        }
        Button(
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
            onClick = { },
            ) {
            Text(text = "Bets")
        }

    }

}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}