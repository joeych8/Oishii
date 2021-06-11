package com.example.oishii.burgerMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.oishii.R

class BurgerMenuFragment : Fragment() {


    private lateinit var aboutTv: TextView
    private lateinit var contactTv: TextView
    private lateinit var reservationTv: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.burger_menu_fragment, container, false)

        aboutTv = view.findViewById(R.id.about_tv)
        contactTv = view.findViewById(R.id.contact_tv)
        reservationTv = view.findViewById(R.id.reservation_tv)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonClickListeners()


    }


    private fun buttonClickListeners() {

        aboutTv.setOnClickListener {
            findNavController().navigate(R.id.action_burgerMenuFragment_to_aboutFragment)
        }

        contactTv.setOnClickListener {
            findNavController().navigate(R.id.action_burgerMenuFragment_to_contactFragment)
        }

        reservationTv.setOnClickListener {
            findNavController().navigate(R.id.action_burgerMenuFragment_to_calendarFragment)
        }


    }


}