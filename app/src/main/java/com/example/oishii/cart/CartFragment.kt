package com.example.oishii.cart

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
import androidx.core.view.isEmpty
import androidx.navigation.fragment.findNavController
import com.example.oishii.R
import com.example.oishii.notification.OishiiNotificationManager
import com.example.oishii.utils.NOTIFICATION_1_ID
import java.util.concurrent.Executor

class CartFragment : Fragment() {

    private lateinit var viewModel: CartViewModel
    private lateinit var payTv: TextView
    private lateinit var executor: Executor
    private lateinit var cartLinearLayout: LinearLayout
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var totalPriceTv: TextView
    private var sumPrice = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cart_fragment, container, false)

        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        payTv = view.findViewById(R.id.pay_tv)
        cartLinearLayout = view.findViewById(R.id.cart_content_linear_layout)
        totalPriceTv = view.findViewById(R.id.total_sum_tv)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        executor = ContextCompat.getMainExecutor(requireContext())

        setOnclickListeners()
        createViewsToCart()

    }


    //setter data fra db inn i CustomCartView
    private fun createViewsToCart() {
        cartLinearLayout.removeAllViews()

        //fetching menuObject from DB
        viewModel.fetchAllItems {

            val menu = it

            //For hver dish i "item" som er en liste av MenuObject(List<MenuObject>) så skal det først lages et nytt view, som bruker funksjonen inne i newCartView(setCartContentText) til å sette teksten inn i dish.
            // tilslutt ber vi cartLinearLayout om å legge til det nye viewet(newCartView)
            activity?.runOnUiThread {
                for (dish in menu) {
                    val newCartView = CustomCartView(requireContext())
                    newCartView.setCartContentText(dish)
                    cartLinearLayout.addView(newCartView)
                }

                //Legger sammen prisen på hver enkel dish og viser total summen i totalPriceSum textview.
                for (dish in menu) {
                    sumPrice += dish.priceTag
                }
                val totalSum = "$sumPrice${getString(R.string.cart_total_sum_currency_text)}"
                totalPriceTv.text = totalSum
                //Kjøres her for å kunne få oppdatert total sum
                biometricAuthentication(sumPrice)

            }
        }
    }


    private fun setOnclickListeners() {
        //kun når det er noe i handle vognen/cartLinearLayout så kan bruker gå til betaling
        payTv.setOnClickListener {
            if (cartLinearLayout.isEmpty()) {
                Toast.makeText(context, getString(R.string.empty_cart_text), Toast.LENGTH_SHORT)
                    .show()
            } else {
                biometricPrompt.authenticate(promptInfo)

            }
        }

    }


    //Tar in price: Int for å få tak i oppdatert sumPrice
    private fun biometricAuthentication(price: Int) {

        biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)

                    //auth error, stop tasks that require auth
                    Toast.makeText(
                        context,
                        getString(R.string.authentication_cancel_text),
                        Toast.LENGTH_SHORT
                    ).show()

                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)

                    //auth succeed, do task
                    OishiiNotificationManager.notificationChannel1(
                        getString(R.string.order_confirmation_title_text),
                        getString(R.string.payment_success_text), NOTIFICATION_1_ID
                    )
                    viewModel.deleteAllItems()
                    cartLinearLayout.removeAllViews()
                    Toast.makeText(
                        context,
                        getString(R.string.authentication_success_text),
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToTimerFragment()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()

                    //auth failed, stop tasks
                    Toast.makeText(
                        context,
                        getString(R.string.authentication_failed_text),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        //set prompt like title and description in auth dialog box
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.bioid_title_text))
            .setSubtitle("${getString(R.string.bioid_subtitle_total_text)}${price}${getString(R.string.bioid_subtitle_currency_text)}")
            .setNegativeButtonText(getString(R.string.bioid_cancel_button_text))
            .build()
    }

    private fun navigateToTimerFragment() {

        findNavController().navigate(R.id.action_goToCartFragment_to_timerFragment)
    }

}

