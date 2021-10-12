package com.example.cs4518_finalproject

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

private const val TAG = "SendActivity"
private var USER_MESSAGE = "Love"
private var OTHER_USERNAME = "Pedro"

class SendActivity : AppCompatActivity(){

    // Set the button text and color to the correct values depending on the received message
    private lateinit var messageButton: Button
    private lateinit var backButton: Button
    private lateinit var textOtherUser: TextView
    private lateinit var viewKonfetti: KonfettiView

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.send_screen)

        /*
        WIRING UP WIDGETS
        */
        messageButton = findViewById(R.id.button_message)
        backButton = findViewById(R.id.button_sendBack)
        textOtherUser = findViewById(R.id.textView_otherU)
        viewKonfetti = findViewById(R.id.viewKonfetti)

        /*
        SET CORRECT VALUES
         */
        textOtherUser.text = OTHER_USERNAME
        messageButton.text = USER_MESSAGE
        messageButton.setBackgroundColor(findColor(USER_MESSAGE))

        /*
        ON-CLICK LISTENERS
         */
        backButton.setOnClickListener{
            // Return to FirstFragment
            val intent = MainActivity.newIntent(this@SendActivity)
            startActivity(intent)
        }

        /*
        CONFETTI! :D
         */
        viewKonfetti.build()
            .addColors(findColor(USER_MESSAGE))
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(1000L)
            .addShapes(Shape.Square, Shape.Circle)
            .addSizes(Size(12))
            .setPosition(0f, viewKonfetti.width + 1000f, 0f, viewKonfetti.height - 5f)
            .streamFor(300, 1000L)
    }

    private fun findColor(user_message : String): Int {
        var result = 0

        when (user_message) {
            "Love" -> {
                //Pink
                result = Color.parseColor("#E91E63")
            }
            "Peace" -> {
                //Green
                result = Color.parseColor("#8BC34A")
            }
            "Support" -> {
                //Blue
                result = Color.parseColor("#03A9F4")
            }
            else -> {
                //Hope
                result = Color.parseColor("#FF9800")
            }
        }

        return result
    }

    companion object {
        fun newIntent(packageContext: Context?, message: String, otherUsername: String): Intent {
            Log.d(TAG, "Got Extra: $message")
            Log.d(TAG, "Got Extra: $otherUsername")
            USER_MESSAGE = message
            OTHER_USERNAME = otherUsername
            return Intent(packageContext, SendActivity::class.java).apply {
                putExtra(USER_MESSAGE, message)
                putExtra(OTHER_USERNAME, otherUsername)
            }
        }
    }
}