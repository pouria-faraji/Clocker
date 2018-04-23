package com.blacksite.clocker.model.`object`

import com.blacksite.clocker.application.App
import com.blacksite.clocker.model.Item
import com.blacksite.clocker.model.db.FaceDB
import com.blacksite.clocker.model.db.FaceDBDao

/**
 * Created by p.faraji on 4/18/2018.
 */
class Face {
    private var id: Long? = null
    private var image: Int? = null
    private var imageWhite: Int? = null
    private var _faceDBDao: FaceDBDao? = null

    constructor(){
        val daoSession = App.daoSession
        _faceDBDao = daoSession!!.faceDBDao
    }

    constructor(id: Long?, image: Int?, imageWhite:Int?) {
        this.id = id
        this.image = image
        this.imageWhite = imageWhite
        val daoSession = App.daoSession
        _faceDBDao = daoSession!!.faceDBDao
    }

    constructor(image: Int?, imageWhite: Int?) {
        this.image = image
        this.imageWhite = imageWhite
        val daoSession = App.daoSession
        _faceDBDao = daoSession!!.faceDBDao
    }


    fun insert(){
        var faceDB = FaceDB()
        faceDB.image = this.image
        faceDB.imageWhite = this.imageWhite
        this.id = _faceDBDao!!.insert(faceDB)
    }
    fun loadFaces():ArrayList<Face>{
        var result = ArrayList<Face>()
        for(faceDB in _faceDBDao!!.loadAll()){
            var face = Face(faceDB.id, faceDB.image, faceDB.imageWhite)
            result.add(face)
        }
        return result
    }
    fun loadFacesAsGridItem():ArrayList<Item>{
        var result = ArrayList<Item>()
        for(faceDB in _faceDBDao!!.loadAll()){
            var item = Item(faceDB.image, faceDB.imageWhite)
            result.add(item)
        }
        return result
    }
}