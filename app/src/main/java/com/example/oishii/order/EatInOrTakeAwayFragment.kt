package com.example.oishii.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oishii.R

class EatInOrTakeAwayFragment : Fragment() {

    companion object {
        fun newInstance() = EatInOrTakeAwayFragment()
    }

    private lateinit var viewModel: EatInOrTakeAwayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.eat_in_or_take_away_fragment, container, false)



        viewModel = ViewModelProvider(this).get(EatInOrTakeAwayViewModel::class.java)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}