package com.blacksite.clocker.model.`object`

import android.app.Activity
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
    public var analogClock: Int? = null
    public var analogClockWidget: Int? = null
    private var image: Int? = null
    private var _handDBDao: HandDBDao? = null


    constructor(id: Long?, analogClock: Int?, image: Int?) {
        this.id = id
        this.analogClock = analogClock
        this.image = image
        val daoSession = App.daoSession
        _handDBDao = daoSession!!.handDBDao
    }

    constructor(analogClock: Int?, image: Int?) {
        this.analogClock = analogClock
        this.image = image
        val daoSession = App.daoSession
        _handDBDao = daoSession!!.handDBDao
    }


    constructor(){
        val daoSession = App.daoSession
        _handDBDao = daoSession!!.handDBDao
    }


    constructor(analogClock: Int?, analogClockWidget: Int?, image: Int?) {
        this.analogClock = analogClock
        this.analogClockWidget = analogClockWidget
        this.image = image
        val daoSession = App.daoSession
        _handDBDao = daoSession!!.handDBDao
    }

    constructor(id: Long?, analogClock: Int?, analogClockWidget: Int?, image: Int?) {
        this.id = id
        this.analogClock = analogClock
        this.analogClockWidget = analogClockWidget
        this.image = image
    }

    fun insert(){
        var handDB = HandDB()
        handDB.analogClock = this.analogClock
        handDB.analogClockWidget = this.analogClockWidget
        handDB.image = this.image
        this.id = _handDBDao!!.insert(handDB)
    }
    fun loadHands():ArrayList<Hand>{
        var result = ArrayList<Hand>()
        for(handDB in _handDBDao!!.loadAll()){
            var hand = Hand(handDB.id, handDB.analogClock, handDB.analogClockWidget,handDB.image)
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
    fun makeAllGone(context: Activity, analogClockReference:Int){
        var analogClock = context.findViewById<AnalogClock>(analogClockReference)
        for(i in Global.handList.indices){
            var temp = context.findViewById<AnalogClock>(Global.handList[i].analogClock!!)
            temp.visibility = View.GONE
        }
        analogClock.visibility = View.VISIBLE
    }
    fun makeAllGoneWidget(analogClockReference:Int, remoteViews:RemoteViews){
//        var analogClock = context.findViewById<AnalogClock>(analogClockReference)
        for(hand in Global.handList){
            remoteViews.setViewVisibility(hand.analogClockWidget!!, View.GONE)
//            var temp = context.findViewById<AnalogClock>(Global.handList[i].analogClock!!)
//            temp.visibility = View.GONE
        }
        remoteViews.setViewVisibility(analogClockReference, View.VISIBLE)
//        analogClock.visibility = View.VISIBLE
    }
}