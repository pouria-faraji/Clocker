package com.blacksite.clocker.model.`object`

import com.blacksite.clocker.application.App
import com.blacksite.clocker.model.Item
import com.blacksite.clocker.model.db.DialDB
import com.blacksite.clocker.model.db.DialDBDao
import com.blacksite.clocker.model.db.FaceDB
import com.blacksite.clocker.model.db.FaceDBDao

/**
 * Created by p.faraji on 4/18/2018.
 */
class Dial {
    private var id: Long? = null
    private var image: Int? = null
    private var _dialDBDao: DialDBDao? = null

    constructor(){
        val daoSession = App.daoSession
        _dialDBDao = daoSession!!.dialDBDao
    }

    constructor(id: Long?, image: Int?) {
        this.id = id
        this.image = image
        val daoSession = App.daoSession
        _dialDBDao = daoSession!!.dialDBDao
    }

    constructor(image: Int?) {
        this.image = image
        val daoSession = App.daoSession
        _dialDBDao = daoSession!!.dialDBDao
    }


    fun insert(){
        var dialDB = DialDB()
        dialDB.image = this.image
        this.id = _dialDBDao!!.insert(dialDB)
    }
    fun loadFaces():ArrayList<Dial>{
        var result = ArrayList<Dial>()
        for(dialDB in _dialDBDao!!.loadAll()){
            var face = Dial(dialDB.id, dialDB.image)
            result.add(face)
        }
        return result
    }
    fun loadDialsAsGridItem():ArrayList<Item>{
        var result = ArrayList<Item>()
        for(dialDB in _dialDBDao!!.loadAll()){
            var item = Item(dialDB.image)
            result.add(item)
        }
        return result
    }
}