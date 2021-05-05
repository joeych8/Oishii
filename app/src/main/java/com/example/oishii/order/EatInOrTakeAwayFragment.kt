package com.example.oishii.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.oishii.R

class EatInOrTakeAwayFragment : Fragment() {

    companion object {
        fun newInstance() = EatInOrTakeAwayFragment()
    }

    private lateinit var viewModel: EatInOrTakeAwayViewModel
    private lateinit var takeAwayIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.eat_in_or_take_away_fragment, container, false)
        takeAwayIcon = view.findViewById(R.id.takeAway_ImageView)


        viewModel = ViewModelProvider(this).get(EatInOrTakeAwayViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navigation()


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

        takeAwayIcon.setOnClickListener {
            findNavController().navigate(R.id.menuFragment, null, options)
        }


    }

}