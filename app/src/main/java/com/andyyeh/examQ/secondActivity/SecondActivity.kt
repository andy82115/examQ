package com.andyyeh.examQ.secondActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import com.andyyeh.examQ.Configuration
import com.andyyeh.examQ.R
import kotlinx.android.synthetic.main.activity_second.*

/**
* Did not utilize the Dagger or MVVM structure, cause this page just for display ConstraintLayout
* */
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setTransition()
        getIntentDataAndSetToView()
    }

    private fun setTransition(){
        window.enterTransition = Slide(Gravity.END).setDuration(250)
        window.returnTransition = Slide(Gravity.START).setDuration(250)
    }

    private fun getIntentDataAndSetToView(){
        val endTimeStr = intent.getStringExtra(Configuration.END_TIME_KEY)

        vSecondStartTimeTV.text = intent.getStringExtra(Configuration.START_TIME_KEY)
        vSecondEndTimeTV.text = intent.getStringExtra(Configuration.END_TIME_KEY)
        vSecondParameterTV.text = intent.getStringExtra(Configuration.PARAMETER_KEY)
    }
}
