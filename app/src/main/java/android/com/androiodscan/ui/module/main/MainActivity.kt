package android.com.androiodscan.ui.module.main

import android.com.androiodscan.R
import android.com.androiodscan.data.ApiResponse
import android.com.androiodscan.data.MessageDetail
import android.com.androiodscan.data.MessageLanding
import android.com.androiodscan.ui.module.detail.DetailFragment
import android.com.androiodscan.ui.module.parameter.ParameterFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_main, LandingFragment())
            .commit()
    }

    @Subscribe
    fun onEventLanding(message: MessageLanding){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, DetailFragment.newInstance(message.apiResponse))
            .addToBackStack(null)
            .commit()
    }

    @Subscribe
    fun onEventDetail(messageDetail: MessageDetail){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, ParameterFragment.newInstance(messageDetail.variable))
            .addToBackStack(null)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
