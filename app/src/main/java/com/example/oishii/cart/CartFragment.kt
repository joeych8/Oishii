package com.example.oishii.cart

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.oishii.R

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: CartViewModel
    private lateinit var payTv: TextView
    private lateinit var notificationsManager: NotificationManager
    private var channelId = "com.example.oishii.id.notification"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cart_fragment, container, false)

        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        payTv = view.findViewById(R.id.pay_tv)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationsManager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
        setOnclickListeners()


    }


    private fun setOnclickListeners() {

        payTv.setOnClickListener {
            createAndSendNotification()

        }

    }

    private fun createAndSendNotification() {

        val notificationId = 321123
        val notification = Notification.Builder(requireContext(), channelId)
            .setContentTitle("Order confirmation")
            .setContentText("Payment successful! Your order is being processed!")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        notificationsManager.notify(notificationId,notification)
    }


    private fun createNotificationChannel() {

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, "Order confirmations", importance)

        channel.description = "Makes a sound and appears as a heads-up notification"
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 500, 400)
        channel.setShowBadge(true)
        notificationsManager.createNotificationChannel(channel)

    }


}