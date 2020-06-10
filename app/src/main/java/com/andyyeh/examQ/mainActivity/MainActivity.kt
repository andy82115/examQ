package com.andyyeh.examQ.mainActivity

import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.andyyeh.examQ.Configuration
import com.andyyeh.examQ.R
import com.andyyeh.examQ.databinding.ActivityMainBinding
import com.andyyeh.examQ.mainActivity.adapters.ClimateAdapter
import com.andyyeh.examQ.mainActivity.data.ClimateBean
import com.andyyeh.examQ.mvvmBase.BaseActivity
import com.andyyeh.examQ.secondActivity.SecondActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.jvm.internal.MagicApiIntrinsics

class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.simpleName

    @Inject lateinit var mViewModel: MainActivityViewModel
    @Inject lateinit var mAdapter: ClimateAdapter

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.setTransition()
        this.setRecyclerView()
    }

    private fun setTransition(){
        window.enterTransition = Slide(Gravity.END).setDuration(250)
        window.returnTransition = Slide(Gravity.START).setDuration(250)
    }

    private fun setRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this)
        vClimateRV.layoutManager = linearLayoutManager
        vClimateRV.adapter = mAdapter
        mAdapter.txtClickedObserver = Consumer(fun(bean: ClimateBean) {
            if (Configuration.DEBUG) Log.e(TAG, "$bean")
            goNextPage(bean)
        })
    }

    private fun goNextPage(ClimateBean: ClimateBean){
        val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle()
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(Configuration.PARAMETER_KEY, ClimateBean.Parameter)
        intent.putExtra(Configuration.START_TIME_KEY, ClimateBean.StartTime)
        intent.putExtra(Configuration.END_TIME_KEY, ClimateBean.EndTime)

        startActivity(intent, bundle)
    }
}
