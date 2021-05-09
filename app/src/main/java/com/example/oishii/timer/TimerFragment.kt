package com.example.oishii.timer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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


class TimerFragment : Fragment() {


    private lateinit var timerTv: TextView
    private lateinit var backToStartTv: TextView
    private lateinit var secondsTv: TextView
    private lateinit var notificationsManager: NotificationManager
    private var channelId = "com.example.oishii.id.notificationFoodFinish"
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
        notificationsManager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        createNotificationChannel()
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

                createAndSendNotification()
                timerTv.visibility = View.GONE
                secondsTv.visibility = View.GONE

            }
        }.start()
    }


    private fun createNotificationChannel() {

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, "Order Complete", importance)

        channel.description = "Makes a sound and appears as a heads-up notification"
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 500, 400)
        channel.setShowBadge(true)
        notificationsManager.createNotificationChannel(channel)
    }

    private fun createAndSendNotification() {

        val notificationId = 65465
        val notification = Notification.Builder(requireContext(), channelId)
            .setContentTitle("Order Complete!")
            .setContentText("Your order is completed and ready for pickup")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        notificationsManager.notify(notificationId, notification)

    }


}