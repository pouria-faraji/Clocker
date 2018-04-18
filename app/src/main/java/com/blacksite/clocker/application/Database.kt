package com.blacksite.clocker.application

import android.content.Context
import com.blacksite.clocker.R
import com.blacksite.clocker.model.`object`.Face

/**
 * Created by p.faraji on 4/18/2018.
 */
class Database {
    fun initialize(){
        setupFace()
    }
    fun setupFace(){
        var faceList = ArrayList<Face>()
        faceList.add(Face(R.drawable.splash))
        faceList.add(Face(R.drawable.splash_red))
        faceList.add(Face(R.drawable.splash))
        faceList.add(Face(R.drawable.splash_red))
        for (face in faceList){
            face.insert()
        }
    }
}