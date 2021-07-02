package com.example.oishii.timer

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.oishii.R
import com.example.oishii.notification.OishiiNotificationManager
import com.example.oishii.utils.NOTIFICATION_1_ID


class TimerFragment : Fragment() {

    private lateinit var timerTv: TextView
    private lateinit var backToStartTv: TextView
    private lateinit var secondsTv: TextView
    var counter = 5


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

        findNavController().navigate(R.id.action_timerFragment_to_takeAwayFragment)
    }


    private fun startTimeCounter() {
        val countTime = timerTv
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = counter.toString()
                counter--

            }

            override fun onFinish() {
                OishiiNotificationManager.notificationChannel1(
                    getString(R.string.notification_order_title_text),
                    getString(R.string.notification_order_complete_text), NOTIFICATION_1_ID
                )
                timerTv.visibility = View.GONE
                secondsTv.visibility = View.GONE

            }
        }.start()
    }

}