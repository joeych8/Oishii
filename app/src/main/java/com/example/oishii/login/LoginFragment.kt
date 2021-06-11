package com.example.oishii.login


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.oishii.R
import com.example.oishii.utils.SHARED_PREFS_FILE_NAME
import com.example.oishii.utils.SHARED_PREF_LOGIN_BOOLEAN

class LoginFragment : Fragment() {


    private lateinit var fortsettTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        fortsettTextView = view.findViewById(R.id.fortsett_textView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<ImageView>(R.id.back_arrow)?.visibility = View.GONE

        sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE)

        skipLoginPageAfterFirstLogin()
        navigation()

    }


    private fun navigation() {

        fortsettTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_takeAwayFragment)

            val editor = sharedPreferences.edit()
            editor.putBoolean(SHARED_PREF_LOGIN_BOOLEAN, true).apply()

        }
    }

    private fun skipLoginPageAfterFirstLogin() {

        if (sharedPreferences.contains(SHARED_PREF_LOGIN_BOOLEAN)) {
            findNavController().navigate(R.id.takeAwayFragment)

        }
    }


}