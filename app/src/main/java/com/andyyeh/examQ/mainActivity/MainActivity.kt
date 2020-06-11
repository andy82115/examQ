package com.andyyeh.examQ.mainActivity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.widget.Toast
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
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.jvm.internal.MagicApiIntrinsics

class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.simpleName

    @Inject lateinit var viewModel: MainActivityViewModel
    @Inject lateinit var adapter: ClimateAdapter
    @Inject lateinit var sharedPreferences: SharedPreferences

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**this place attach the data binding with R.layout.activity_main**/
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        this.setTransition()
        this.checkIsFirstTime()
        this.setRecyclerView()
        this.requestData()
    }

    /**Set the transition animation**/
    private fun setTransition(){
        window.enterTransition = Fade().setDuration(250)
        window.returnTransition = Slide(Gravity.START).setDuration(250)
    }

    /**To check out if user open this app first time
     * if not, send the welcome back Toast
     * **/
    private fun checkIsFirstTime(){
        val isFirstTime = sharedPreferences.getBoolean(Configuration.SP_IS_FIRST_TIME_KEY, true)
        if (isFirstTime){
            sharedPreferences.edit().putBoolean(Configuration.SP_IS_FIRST_TIME_KEY, false).apply()
        }
        else {
            Toast.makeText(this, "Welcome back", Toast.LENGTH_LONG).show()
        }
    }

    /**Utilize RxKotlin to observe the click event from adapter
     * @see ClimateAdapter
     * **/
    private fun setRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this)
        vClimateRV.layoutManager = linearLayoutManager
        vClimateRV.adapter = adapter
        adapter.txtClickedObserver = Consumer(fun(bean: ClimateBean) {
            if (Configuration.DEBUG) Log.e(TAG, "$bean")
            goNextPage(bean)
        })
    }

    /**
     * Utilize RxKotlin to notify adapter to update
     * Actually the correct way is comparing the data in the model is the same or not
     * If it is not the same data, then add new data into it
     * After that use adapter.notifyDataRangeInsert to do it
     * By the way, the most accurate way might be using "payload" to do it
     * @see MainActivityViewModel
     * **/
    private fun requestData(){
        viewModel.requestClimateData(Action {
            adapter.notifyDataSetChanged()
        })
    }

    /**
     * @param ClimateBean is the clicked data
     * to deliver a complicated Object could extend the "Parcelable"
     * **/
    private fun goNextPage(ClimateBean: ClimateBean){
        /**makeSceneTransitionAnimation() are the function for native transitionAnimation**/
        val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle()
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(Configuration.PARAMETER_KEY, ClimateBean.Parameter)
        intent.putExtra(Configuration.START_TIME_KEY, ClimateBean.StartTime)
        intent.putExtra(Configuration.END_TIME_KEY, ClimateBean.EndTime)
        startActivity(intent, bundle)
    }
}
