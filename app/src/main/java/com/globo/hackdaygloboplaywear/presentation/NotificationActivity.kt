package com.globo.hackdaygloboplaywear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.globo.hackdaygloboplaywear.presentation.components.NotificationMessage
import com.globo.hackdaygloboplaywear.presentation.theme.HackdayGloboplayWearTheme
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable

class NotificationActivity : ComponentActivity() {

    private lateinit var messageClient: MessageClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messageClient = Wearable.getMessageClient(this)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            HackdayGloboplayWearTheme {
                NotificationMessage("FLA x FLU",
                    {},
                    {}
                )
            }
        }
    }

}