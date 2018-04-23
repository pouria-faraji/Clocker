package com.blacksite.clocker.application

import android.content.Context
import com.blacksite.clocker.R
import com.blacksite.clocker.model.`object`.Dial
import com.blacksite.clocker.model.`object`.Face

/**
 * Created by p.faraji on 4/18/2018.
 */
class Database {
    fun initialize(){
        setupFace()
        setupDial()
    }
    fun setupFace(){
        var faceList = ArrayList<Face>()
        faceList.add(Face(R.drawable.transparent_512, R.drawable.transparent_512))
        faceList.add(Face(R.drawable.skull_512, R.drawable.skull_512))
        faceList.add(Face(R.drawable.jellyfish_512, R.drawable.jellyfish_512))
        faceList.add(Face(R.drawable.lion_512, R.drawable.lion_512_w))
        faceList.add(Face(R.drawable.tiger_512, R.drawable.tiger_512_w))
        faceList.add(Face(R.drawable.panther_512, R.drawable.panther_512_w))
        for (face in faceList){
            face.insert()
        }
    }
    fun setupDial(){
        var dialList = ArrayList<Dial>()
        dialList.add(Dial(R.drawable.no_dial))
        dialList.add(Dial(R.drawable.dial_1))
        dialList.add(Dial(R.drawable.dial_2))
        for (dial in dialList){
            dial.insert()
        }
    }
}