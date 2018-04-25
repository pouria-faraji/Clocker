package com.blacksite.clocker.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.blacksite.clocker.R
import com.blacksite.clocker.application.Global
import com.blacksite.clocker.model.Item
import kotlinx.android.synthetic.main.item.view.*
import android.widget.RelativeLayout
import com.blacksite.clocker.application.Constants
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.blacksite.clocker.R.layout.item
import android.support.v7.widget.RecyclerView.ViewHolder




/**
 * Created by p.faraji on 4/17/2018.
 */
class ItemAdapter:BaseAdapter {
    var context: Context? = null
    var itemsList = ArrayList<Item>()
    var hashMapSelected: HashMap<Int, Boolean>? = null

    constructor(context: Context?, itemsList: ArrayList<Item>) : super() {
        this.context = context
        this.itemsList = itemsList

        hashMapSelected = HashMap()
        for (i in 0 until itemsList.size) {
            hashMapSelected!!.put(i, false)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var varConvertView = convertView
        var viewHolder: ViewHolder? = null
        val item = this.itemsList[position]
        if (varConvertView == null) {
            viewHolder = ViewHolder()
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            varConvertView = inflator.inflate(R.layout.item, null)
            varConvertView!!.tag = viewHolder
        }else {
            viewHolder = varConvertView!!.tag as ViewHolder
//            viewHolder = (varConvertView!!.tag) as ViewHolder
        }

        viewHolder!!.imageView!!.setImageDrawable(Global.reduceImage(item.image!!))
        var size = ((0.7) * Global.getAppWidth()/Constants.NUMBER_ITEMS_EACH_ROW).toInt()
        viewHolder!!.imageView!!.layoutParams.width = size
        viewHolder!!.imageView!!.layoutParams.height = size
        val params1 = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, Global.getAppWidth()/Constants.NUMBER_ITEMS_EACH_ROW)
        viewHolder!!.layout!!.layoutParams = params1
        if(hashMapSelected!![position] == true){
            viewHolder!!.layout!!.background = ContextCompat.getDrawable(context, R.drawable.item_background_selected)
        }else{
            viewHolder!!.layout!!.background = ContextCompat.getDrawable(context, R.drawable.item_background)
        }


//        val item = this.itemsList[position]
//
//        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        var itemView = inflator.inflate(R.layout.item, null)
//        itemView.imgItem.setImageDrawable(Global.reduceImage(item.image!!))
//        itemView.imgItem.layoutParams.width = ((0.7) * Global.getAppWidth()/Constants.NUMBER_ITEMS_EACH_ROW).toInt()
//        itemView.imgItem.layoutParams.height = ((0.7) * Global.getAppWidth()/Constants.NUMBER_ITEMS_EACH_ROW).toInt()
//
//        val params1 = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, Global.getAppWidth()/Constants.NUMBER_ITEMS_EACH_ROW)
//        itemView.layoutItem.layoutParams = params1
//
//        if(hashMapSelected!![position] == true){
//            itemView.layoutItem.background = ContextCompat.getDrawable(context, R.drawable.item_background_selected)
//        }else{
//            itemView.layoutItem.background = ContextCompat.getDrawable(context, R.drawable.item_background)
//        }

        return varConvertView!!
    }

//    private object MyViewHolder{
//        var layout:RelativeLayout? = null
//        var imageView:ImageView? = null
//    }

    private class ViewHolder {
        var layout:RelativeLayout? = null
        var imageView:ImageView? = null
    }
    fun makeAllUnselect(position: Int) {
        hashMapSelected!!.put(position, true)
        for (i in 0 until hashMapSelected!!.size) {
            if (i != position)
                hashMapSelected!!.put(i, false)
        }
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