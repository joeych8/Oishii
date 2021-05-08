package com.example.oishii.cart

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.oishii.R

import java.util.concurrent.Executor

class CartFragment : Fragment() {


    private lateinit var viewModel: CartViewModel
    private lateinit var payTv: TextView
    private lateinit var notificationsManager: NotificationManager
    private var channelId = "com.example.oishii.id.notification"
    private lateinit var cartLinearLayout: LinearLayout
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cart_fragment, container, false)

        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        payTv = view.findViewById(R.id.pay_tv)
        cartLinearLayout = view.findViewById(R.id.cart_content_linear_layout)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationsManager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        executor = ContextCompat.getMainExecutor(requireContext())

        biometricAuthentication()
        createNotificationChannel()
        setOnclickListeners()
        createViewsToCart()


    }


    //setter data fra db inn i CustomCartView //TODO stopper når man åpner handlevogn? Error:Only the original thread that created a view hierarchy can touch its views **Solution: runOnUiThread**
    private fun createViewsToCart() {

        cartLinearLayout.removeAllViews()
        //fetching menuObject from DB
        viewModel.fetchAllItems {

            val item = it
            /**For Loop explained to myself like im brain dead*/
            //for hver dish i item(som er MenuObject) så skal det først lages et nytt view, som bruker funksjonen inne i newCartView(setCartContentText) til å sette teksten inn i dish.
            // tilslutt ber vi cartLinearLayout om å legge til det nye viewet(newCartView)

            activity?.runOnUiThread {
                for (dish in item) {
                    val newCartView = CustomCartView(requireContext())
                    newCartView.setCarthContentText(dish)
                    cartLinearLayout.addView(newCartView)
                }
            }
        }
    }


    private fun setOnclickListeners() {

        payTv.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun createAndSendNotification() {

        val notificationId = 321123
        val notification = Notification.Builder(requireContext(), channelId)
            .setContentTitle("Order confirmation")
            .setContentText("Payment successful! Your order is being processed!")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        notificationsManager.notify(notificationId, notification)

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

    private fun biometricAuthentication() {

        biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    //auth error, stop tasks that require auth
                    Toast.makeText(
                        context,
                        "Authentication error. Please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    //auth succeed, do task
                    createAndSendNotification()
                    viewModel.deleteAllItems()
                    cartLinearLayout.removeAllViews()
                    Toast.makeText(context, "Authentication successful", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    //auth failed, stop tasks
                    Toast.makeText(
                        context,
                        "Authentication failed, please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        //set prompt like title and description in auth dialog box
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("BioMetric Authentication")
            .setSubtitle("pay for food")
            .setNegativeButtonText("Use account password")
            .build()

    }

}