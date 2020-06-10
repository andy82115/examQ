package com.andyyeh.examQ.mainActivity

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.databinding.DataBindingUtil
import com.andyyeh.examQ.R
import com.andyyeh.examQ.databinding.ActivityMainBinding
import com.andyyeh.examQ.mvvmBase.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var mViewModel: MainActivityViewModel

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.setTransition()
    }

    private fun setTransition(){
        window.enterTransition = Slide(Gravity.END).setDuration(250)
        window.returnTransition = Slide(Gravity.START).setDuration(250)
    }
}
