package com.example.oishii.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oishii.R

class GoToCartFragment : Fragment() {

    companion object {
        fun newInstance() = GoToCartFragment()
    }

    private lateinit var viewModel: GoToCartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.go_to_cart_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GoToCartViewModel::class.java)
        // TODO: Use the ViewModel
    }

}