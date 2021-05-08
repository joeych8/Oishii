package com.example.oishii.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.oishii.R

class LoginFragment : Fragment() {



    private lateinit var fortsettTextView: TextView


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


        navigation()


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

        fortsettTextView.setOnClickListener {
            findNavController().navigate(R.id.eatInOrTakeAwayFragment, null, options)
        }
    }


}