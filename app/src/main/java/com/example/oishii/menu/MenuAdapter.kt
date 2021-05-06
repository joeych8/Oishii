package com.example.oishii.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.R

class MenuAdapter(var dataSet: List<MenuCardObject>, val context: Context) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val verticalTitleCard: TextView
        val menuContentLinearLayout: LinearLayout



        init {
            // Define click listener for the ViewHolder's View.
            verticalTitleCard = view.findViewById(R.id.vertical_card_title)
            menuContentLinearLayout = view.findViewById(R.id.menu_content_linear_layout)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.menu_card, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {


        viewHolder.menuContentLinearLayout.removeAllViews()
        viewHolder.verticalTitleCard.text = dataSet[position].title


        for (menu in dataSet[position].menuContent) {
            val newMenuView = CustomMenuView(context)
            newMenuView.setMenuContentText(menu)

            viewHolder.menuContentLinearLayout.addView(newMenuView)
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }


}