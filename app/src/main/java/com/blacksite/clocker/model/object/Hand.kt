package com.blacksite.clocker.model.`object`

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.AnalogClock
import android.widget.RemoteViews
import com.blacksite.clocker.R
import com.blacksite.clocker.application.App
import com.blacksite.clocker.application.Global
import com.blacksite.clocker.model.Item
import com.blacksite.clocker.model.db.HandDB
import com.blacksite.clocker.model.db.HandDBDao

/**
 * Created by p.faraji on 4/24/2018.
 */
class Hand {
    private var id: Long? = null
    private var image: Int? = null

    var number:Int? = null

    private var _handDBDao: HandDBDao? = null


    constructor(id: Long?, image: Int?) {
        this.id = id
        this.image = image
        val daoSession = App.daoSession
        _handDBDao = daoSession!!.handDBDao
    }

    constructor(image: Int?) {
        this.image = image
        val daoSession = App.daoSession
        _handDBDao = daoSession!!.handDBDao
    }


    constructor(){
        val daoSession = App.daoSession
        _handDBDao = daoSession!!.handDBDao
    }


    constructor(image: Int?, number: Int?) {
        this.image = image
        this.number = number
        val daoSession = App.daoSession
        _handDBDao = daoSession!!.handDBDao
    }

    constructor(id: Long?,image: Int?, number: Int?) {
        this.id = id
        this.image = image
        this.number = number
    }

    fun insert(){
        var handDB = HandDB()
        handDB.image = this.image
        handDB.number = this.number
        this.id = _handDBDao!!.insert(handDB)
    }
    fun loadHands():ArrayList<Hand>{
        var result = ArrayList<Hand>()
        for(handDB in _handDBDao!!.loadAll()){
            var hand = Hand(handDB.id,handDB.image,handDB.number)
            result.add(hand)
        }
        return result
    }
    fun loadHandsAsGridItem():ArrayList<Item>{
        var result = ArrayList<Item>()
        for(handDB in _handDBDao!!.loadAll()){
            var item = Item(handDB.image)
            result.add(item)
        }
        return result
    }
    fun makeAllGone(context: Activity, number:Int, colorCode:Int){
        var resourceName = "hand_" + number + "_" + Global.getColorNameByCode(colorCode)
        var resourceID = context.resources.getIdentifier(resourceName, "id", context.packageName)
        var analogClock = context.findViewById<AnalogClock>(resourceID)
        for(i in Global.handList.indices){
            for(j in 1..4){
                var resourceNameTemp = "hand_" + Global.handList[i].number + "_" + Global.getColorNameByCode(j)
                var resourceIDTemp = context.resources.getIdentifier(resourceNameTemp, "id", context.packageName)
                var temp = context.findViewById<AnalogClock>(resourceIDTemp)
                temp.visibility = View.GONE
            }
//            var temp = context.findViewById<AnalogClock>(Global.handList[i].analogClock!!)
//            temp.visibility = View.GONE
        }
        analogClock.visibility = View.VISIBLE
    }
    fun makeAllGoneWidget(context: Context, number:Int, colorCode:Int, remoteViews:RemoteViews){
        var resourceName = "hand_" + number + "_widget_" + Global.getColorNameByCode(colorCode)
        var resourceID = context.resources.getIdentifier(resourceName, "id", context.packageName)
        for(hand in Global.handList){
            for(i in 1..4){
                var resourceNameTemp = "hand_" + hand.number + "_widget_" + Global.getColorNameByCode(i)
                var resourceIDTemp = context.resources.getIdentifier(resourceNameTemp, "id", context.packageName)
                remoteViews.setViewVisibility(resourceIDTemp, View.GONE)
            }
//            remoteViews.setViewVisibility(hand.analogClockWidget!!, View.GONE)
        }
        remoteViews.setViewVisibility(resourceID, View.VISIBLE)
    }

}