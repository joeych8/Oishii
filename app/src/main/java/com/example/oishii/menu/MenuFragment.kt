package com.example.oishii.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.R
import com.example.oishii.database.AppDatabase
import com.example.oishii.database.MenuObject

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var goToCheckOut: ImageView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.menu_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        recyclerView = view.findViewById(R.id.menu_recyclerView)
        goToCheckOut = view.findViewById(R.id.goTOCheckOut)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
        initRecyclerView()

    }


    private fun setClickListeners() {

        goToCheckOut.setOnClickListener {
            val options = navOptions {
                anim {
                    enter = R.anim.fragment_fade_enter
                    exit = R.anim.fragment_fade_exit
                    popEnter = R.anim.fragment_fade_enter
                    popExit = R.anim.fragment_fade_exit
                }
            }
            findNavController().navigate(R.id.goToCartFragment, null, options)
        }


    }

    fun setItemsTocart(){  //TODO note: what do you do if you cant get to the addToCart textview.
        addToCart.setOnClickListener {
            val item = MenuObject("Ramen",null,null,12,"AddToCart")
            viewModel.addItem(item)
        }

    }


    private fun initRecyclerView() {

        menuAdapter = MenuAdapter(
            listOf(
                MenuCardObject(
                    "RAMEN", listOf(
                        MenuObject(
                            "vegan ramen",
                            "Fresh ramen noodles, tofu,\nscallions, shitake mushrooms,\nleeks, bamboo shoots, pak choi\nin spicy dobanjiang soup.",
                            "allergens: hvete, soya, sesam, nøtter",
                            "70kr",
                            "add to cart"
                        ),
                        MenuObject(
                            "spicy vegan ramen",
                            "Fresh ramen noodles, tofu,\nscallions, shitake mushrooms,\nleeks, bamboo shoots, pak choi\nin spicy dobanjiang soup.",
                            "allergens: hvete, soya, sesam, nøtter",
                            "70kr",
                            "add to cart"
                        )
                    )
                ), MenuCardObject(
                    "WOK", listOf(
                        MenuObject(
                            "vegan pad thai prik",
                            "Rice noodle sticks with tofu,\nbean sprouts and chinese chieves\nin chilli sauce. Topped with\ncashew and lime.",
                            "allergens: nøtter, soya",
                            "70kr",
                            "add to cart"
                        ),
                        MenuObject(
                            "vegan teriyaki noodles",
                            "Wheat noodles woked with tofu,\npak choi, bell peppers, red\nonions and broccoli in teriyaki",
                            "allergens: hvete, soya",
                            "70kr",
                            "add to cart"
                        )
                    )
                ), MenuCardObject(
                    "DRINKS", listOf(
                        MenuObject(
                            "sakura ramune",
                            null,
                            null,
                            "30kr",
                            "add to cart"
                        ), MenuObject(
                            "coffee",
                            null,
                            null,
                            "20kr",
                            "add to cart"
                        ), MenuObject(
                            "soda",
                            "Coca-cola\nCoca-cola zero\nsparkling water",
                            null,
                            "35kr",
                            "add to cart"
                        )
                    )
                )
            ), (requireContext())
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = menuAdapter
    }





}