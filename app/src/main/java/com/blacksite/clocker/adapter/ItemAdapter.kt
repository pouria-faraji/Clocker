package com.blacksite.clocker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.blacksite.clocker.R
import com.blacksite.clocker.application.Global
import com.blacksite.clocker.model.Item
import kotlinx.android.synthetic.main.item.view.*
import android.widget.RelativeLayout
import com.blacksite.clocker.application.Constants


/**
 * Created by p.faraji on 4/17/2018.
 */
class ItemAdapter:BaseAdapter {
    var context: Context? = null
    var itemsList = ArrayList<Item>()

    constructor(context: Context?, itemsList: ArrayList<Item>) : super() {
        this.context = context
        this.itemsList = itemsList
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = this.itemsList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var itemView = inflator.inflate(R.layout.item, null)
        itemView.imgItem.setImageResource(item.image!!)
        itemView.imgItem.layoutParams.width = ((0.7) * Global.getAppWidth()/Constants.NUMBER_ITEMS_EACH_ROW).toInt()
        itemView.imgItem.layoutParams.height = ((0.7) * Global.getAppWidth()/Constants.NUMBER_ITEMS_EACH_ROW).toInt()

        val params1 = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, Global.getAppWidth()/Constants.NUMBER_ITEMS_EACH_ROW)
        itemView.layoutItem.layoutParams = params1

        return itemView
    }

    override fun getItem(position: Int): Any {
        return itemsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return itemsList.size
    }
}