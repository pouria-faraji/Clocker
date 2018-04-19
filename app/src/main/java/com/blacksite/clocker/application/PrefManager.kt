package com.blacksite.clocker.application

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by p.faraji on 4/18/2018.
 */
class PrefManager(internal var _context: Context) {
    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor

    // shared pref mode
    internal var PRIVATE_MODE = 0

    var isFirstTimeLaunch: Boolean
        get() = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }

    var facePosition: Int
        get() = pref.getInt(FACE_POSITION, 0)
        set(position){
            editor.putInt(FACE_POSITION, position)
            editor.commit()
        }
    var dialPosition: Int
        get() = pref.getInt(DIAL_POSITION, 0)
        set(position){
            editor.putInt(DIAL_POSITION, position)
            editor.commit()
        }
    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object {

        // Shared preferences file name
        private val PREF_NAME = "clocker_preference"

        private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

        private val FACE_POSITION = "facePosition"
        private val DIAL_POSITION = "dialPosition"
    }
}