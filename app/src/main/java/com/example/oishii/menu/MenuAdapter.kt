package com.example.oishii.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.R
import com.example.oishii.database.MenuObject

class MenuAdapter(
    private var dataSet: List<MenuCardObject>,
    val context: Context,
    val callBack: (MenuObject) -> Unit
) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val verticalTitleCard: TextView = view.findViewById(R.id.vertical_card_title)
        val menuContentLinearLayout: LinearLayout =
            view.findViewById(R.id.menu_content_linear_layout)

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
        val item = dataSet[position]

        viewHolder.menuContentLinearLayout.removeAllViews()
        viewHolder.verticalTitleCard.text = item.title

        for (menu in item.menuContent) {
            val newMenuView = CustomMenuView(context)

            newMenuView.setMenuContentText(menu)
            newMenuView.addToCart.setOnClickListener {
                callBack(menu)
            }

            viewHolder.menuContentLinearLayout.addView(newMenuView) //CustomMenuView blir lagt til i menuContentLinearLayout
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }


}