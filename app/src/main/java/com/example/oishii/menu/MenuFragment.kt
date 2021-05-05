package com.example.oishii.menu

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.MainActivity
import com.example.oishii.R

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.menu_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        recyclerView = view.findViewById(R.id.menu_recyclerView)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecyclerView()


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
                            "70kr"
                        ),
                        MenuObject(
                            "spicy vegan ramen",
                            "Fresh ramen noodles, tofu,\nscallions, shitake mushrooms,\nleeks, bamboo shoots, pak choi\nin spicy dobanjiang soup.",
                            "allergens: hvete, soya, sesam, nøtter",
                            "70kr"
                        )
                    )
                ), MenuCardObject(
                    "WOK", listOf(
                        MenuObject(
                            "vegan pad thai prik",
                            "Rice noodle sticks with tofu,\nbean sprouts and chinese chieves\nin chilli sauce. Topped with\ncashew and lime.",
                            "allergens: nøtter, soya",
                            "70kr"
                        ),
                        MenuObject(
                            "vegan teriyaki noodles",
                            "Wheat noodles woked with tofu,\npak choi, bell peppers, red\nonions and broccoli in teriyaki",
                            "allergens: hvete, soya",
                            "70kr"
                        )
                    )
                ), MenuCardObject(
                    "DRINKS", listOf(
                        MenuObject(
                            "sakura ramune",
                            null,
                            null,
                            "30kr"
                        ),MenuObject(
                            "coffee",
                            null,
                            null,
                            "20kr",
                        ),MenuObject(
                            "soda",
                            "Coca-cola\nCoca-cola zero\nsparkling water",
                            null,
                            "35kr",
                        )
                    )
                )
            ),(requireContext())
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = menuAdapter
    }


}