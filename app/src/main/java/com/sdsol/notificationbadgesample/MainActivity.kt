package com.sdsol.notificationbadgesample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.sdsol.notificationbadgesample.ui.theme.NotificationBadgeSampleTheme


class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                val id = "my_channel_01"
                val name = getString(R.string.channel_name)
                val descriptionText = getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_HIGH
                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val mChannel = NotificationChannel(id, name, importance).apply {
                        description = descriptionText
                        setShowBadge(true)
                    }
                    notificationManager.createNotificationChannel(mChannel)
                }

                val notification = NotificationCompat.Builder(this@MainActivity, id)
                    .setContentTitle("New Messages")
                    .setContentText("You've received 3 new messages.")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setNumber(3)

                notificationManager.notify(1, notification.build())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.POST_NOTIFICATIONS
                    ) -> {
                        val id = "my_channel_01"
                        val name = getString(R.string.channel_name)
                        val descriptionText = getString(R.string.channel_description)
                        val importance = NotificationManager.IMPORTANCE_HIGH
                        val notificationManager =
                            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            val mChannel = NotificationChannel(id, name, importance).apply {
                                description = descriptionText
                                setShowBadge(true)
                            }
                            notificationManager.createNotificationChannel(mChannel)
                        }

                        val notification = NotificationCompat.Builder(this@MainActivity, id)
                            .setContentTitle("New Messages")
                            .setContentText("You've received 3 new messages.")
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setNumber(3)

                        notificationManager.notify(1, notification.build())
                    }

                    else -> {
                        requestPermissionLauncher.launch(
                            android.Manifest.permission.POST_NOTIFICATIONS
                        )
                    }
                }
            } else {
                val id = "my_channel_01"
                val name = getString(R.string.channel_name)
                val descriptionText = getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_HIGH
                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val mChannel = NotificationChannel(id, name, importance).apply {
                        description = descriptionText
                        setShowBadge(true)
                    }
                    notificationManager.createNotificationChannel(mChannel)
                }

                val notification = NotificationCompat.Builder(this@MainActivity, id)
                    .setContentTitle("New Messages")
                    .setContentText("You've received 3 new messages.")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setNumber(3)

                notificationManager.notify(1, notification.build())
            }
        }


        setContent {
            NotificationBadgeSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotificationBadgeSampleTheme {
        Greeting("Android")
    }
}