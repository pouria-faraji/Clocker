package com.blacksite.clocker.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.blacksite.clocker.R
import com.blacksite.clocker.activity.MainActivity
import com.blacksite.clocker.application.Constants
import com.blacksite.clocker.application.Global
import com.blacksite.clocker.model.Item

/**
 * Created by p.faraji on 4/25/2018.
 */
class ItemRecyclerAdapter: Adapter<ItemRecyclerAdapter.ViewHolder> {

    var context: Context? = null
    var itemsList:MutableList<Item>? = null
    var hashMapSelected: HashMap<Int, Boolean>? = null

    var onItemClick: (Int) -> Unit = {}
    constructor(context: Context?, itemsList: MutableList<Item>?) : super() {
        this.context = context
        this.itemsList = itemsList
        hashMapSelected = HashMap()
        for (i in 0 until itemsList!!.size) {
            hashMapSelected!!.put(i, false)
        }
    }

    inner class ViewHolder(var layout: View) : RecyclerView.ViewHolder(layout){
        var imageView: ImageView = layout.findViewById(R.id.imgItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item, parent, false)
        var viewHolder = ViewHolder(v)
        v.setOnClickListener {
            onItemClick(viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemRecyclerAdapter.ViewHolder, position: Int) {
        val item = this.itemsList!![position]
//        holder.imageView.setImageDrawable(Global.reduceImage(item.image!!))
//        holder.imageView.setImageResource(item.image!!)
        holder.imageView.setImageBitmap(Global.reducedBitmaps.get(item.image!!))
        var size = ((0.7) * Global.getAppWidth()/Constants.NUMBER_ITEMS_EACH_ROW).toInt()
        holder.imageView.layoutParams.width = size
        holder.imageView.layoutParams.height = size
        val params1 = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, Global.getAppWidth()/ Constants.NUMBER_ITEMS_EACH_ROW)
        var margin = Global.dp_to_px(2)
        params1.setMargins(margin,margin,margin,margin)
        holder.layout.layoutParams = params1
        if(hashMapSelected!![position] == true){
            holder.layout.background = ContextCompat.getDrawable(context, R.drawable.item_background_selected)
        }else{
            holder.layout.background = ContextCompat.getDrawable(context, R.drawable.item_background)
        }
//        holder.layout.setOnClickListener(View.OnClickListener {
//
//        })

    }

    override fun getItemCount(): Int {
        return itemsList!!.size
    }

    fun clear(){
//        for(item in itemsList!!){
//            itemsList!!.remove(item)
//        }
        itemsList!!.clear()
        notifyDataSetChanged()
    }
    fun add(position: Int, item: Item) {
        itemsList!!.add(position, item)
        notifyItemInserted(position)
        notifyDataSetChanged()
    }
    fun makeAllUnselect(position: Int) {
        hashMapSelected!!.put(position, true)
        for (i in 0 until hashMapSelected!!.size) {
            if (i != position)
                hashMapSelected!!.put(i, false)
        }
    }
}