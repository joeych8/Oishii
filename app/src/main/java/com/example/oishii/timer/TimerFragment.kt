package com.example.oishii.timer

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.oishii.R
import com.example.oishii.notification.OishiiNotificationManager


class TimerFragment : Fragment() {

    private lateinit var timerTv: TextView
    private lateinit var backToStartTv: TextView
    private lateinit var secondsTv: TextView
    var counter = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.timer_fragment, container, false)
        timerTv = view.findViewById(R.id.timer_textView)
        backToStartTv = view.findViewById(R.id.timer_textView_backToStart)
        secondsTv = view.findViewById(R.id.timer_textView_seconds)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimeCounter()
        setOnclickListeners()

    }

    private fun setOnclickListeners() {
        backToStartTv.setOnClickListener {
            navigation()
        }
    }

    private fun navigation() {
        val options = navOptions {
            anim {
                enter = R.anim.fragment_fade_enter
                exit = R.anim.fragment_fade_exit
                popEnter = R.anim.fragment_fade_enter
                popExit = R.anim.fragment_fade_exit
            }
        }
        findNavController().navigate(R.id.takeAwayFragment, null, options)


    }


    fun startTimeCounter() {
        val countTime = timerTv
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = counter.toString()
                counter--
            }

            override fun onFinish() {

                OishiiNotificationManager.createAndSendNotification(
                    "Order Complete!",
                    "Your order is completed and ready for pickup"
                )
                timerTv.visibility = View.GONE
                secondsTv.visibility = View.GONE

            }
        }.start()
    }

}