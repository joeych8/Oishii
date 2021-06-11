package com.example.oishii.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.oishii.R

class TakeAwayFragment : Fragment() {


    private lateinit var takeAwayIcon: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.take_away_fragment, container, false)

        takeAwayIcon = view.findViewById(R.id.takeAway_ImageView)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<ImageView>(R.id.back_arrow)?.visibility = View.VISIBLE

        navigation()


    }


    private fun navigation() {

        takeAwayIcon.setOnClickListener {
            findNavController().navigate(R.id.action_takeAwayFragment_to_menuFragment)
        }


    }

}