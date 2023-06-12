package com.hadirahimi.matrix

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.system.Os
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.hadirahimi.matrix.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity()
{
    private val matrixChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()"
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.apply {
           container.post {
               startMatrixRain(container)
           }
       }

    }

    private fun startMatrixRain(container: FrameLayout) {
        val screenWidth = container.width
        val screenHeight = container.height

        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                addMatrixCharacter(container, screenWidth, screenHeight)
                handler.postDelayed(this, 20)
            }
        }

        handler.postDelayed(runnable, 20)
    }




    private fun addMatrixCharacter(container: FrameLayout, screenWidth: Int, screenHeight: Int) {
        val textView = TextView(this)
        textView.text = matrixChars.random().toString()
        textView.setTextColor(Color.GREEN)

        textView.textSize = 24f
        textView.typeface = Typeface.MONOSPACE
        textView.gravity = Gravity.CENTER

        val layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParams.leftMargin = (0 until screenWidth).random()
        layoutParams.topMargin = -50

        container.addView(textView, layoutParams)

        textView.animate()
            .translationY(screenHeight.toFloat())
            .setDuration((2000..4000).random().toLong())
            .withEndAction {
                container.removeView(textView)
            }
            .start()
    }

}