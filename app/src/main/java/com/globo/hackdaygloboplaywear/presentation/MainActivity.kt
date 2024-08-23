/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.globo.hackdaygloboplaywear.presentation

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound
import androidx.wear.compose.ui.tooling.preview.WearPreviewSmallRound
import androidx.wear.compose.ui.tooling.preview.WearPreviewSquare
import com.globo.hackdaygloboplaywear.R
import com.globo.hackdaygloboplaywear.presentation.components.DrawOdd
import com.globo.hackdaygloboplaywear.presentation.components.TeamOdd
import com.globo.hackdaygloboplaywear.presentation.theme.HackdayGloboplayWearTheme
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

//        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }

        FirebaseMessaging.getInstance().subscribeToTopic("fitness_updates");
        createChannel(this)
        askNotificationPermission(this)
    }
}

@Composable
fun WearApp() {
    HackdayGloboplayWearTheme {
        val random by remember {
            mutableStateOf(Random(0.1.toLong()))
        }
        val randomFla by remember(random) {
            mutableDoubleStateOf(random.nextDouble(0.4, 0.7))
        }
        val randomFlu by remember(random) {
            mutableDoubleStateOf(random.nextDouble(0.9, 1.2))
        }
        val randomDraw by remember(random) {
            mutableDoubleStateOf(random.nextDouble(0.8, 1.0))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.bg_flamengo),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Image(
                    painter = painterResource(id = R.drawable.bg_betano),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = Color(0xFF2d3745),
                            shape = RoundedCornerShape(size = 36.dp)
                        )
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .align(Alignment.TopCenter),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(24.dp)),
                        painter = painterResource(id = R.drawable.ic_logo_globoplay),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        painter = painterResource(id = R.drawable.ic_betano),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                TeamOdd(
                    teamName = "FLA",
                    teamFlag = "https://media.api-sports.io/football/teams/127.png",
                    odd = randomFla,
                )

                DrawOdd(odd = randomDraw)

                TeamOdd(
                    teamName = "FLU",
                    teamFlag = "https://media.api-sports.io/football/teams/124.png",
                    odd = randomFlu,
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