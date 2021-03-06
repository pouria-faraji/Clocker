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
        get() = pref.getInt(FACE_POSITION, 1)
        set(position){
            editor.putInt(FACE_POSITION, position)
            editor.commit()
        }
    var dialPosition: Int
        get() = pref.getInt(DIAL_POSITION, 1)
        set(position){
            editor.putInt(DIAL_POSITION, position)
            editor.commit()
        }
    var handPosition: Int
        get() = pref.getInt(HAND_POSITION, 0)
        set(position){
            editor.putInt(HAND_POSITION, position)
            editor.commit()
        }
    var faceColor: String
        get() = pref.getString(FACE_COLOR, "#000000")
        set(color){
            editor.putString(FACE_COLOR, color)
            editor.commit()
        }
    var faceColorDialog: String
        get() = pref.getString(FACE_COLOR_DIALOG, "#00fff4")
        set(color){
            editor.putString(FACE_COLOR_DIALOG, color)
            editor.commit()
        }
    var dialColor: String
        get() = pref.getString(DIAL_COLOR, "#ffffff")
        set(color){
            editor.putString(DIAL_COLOR, color)
            editor.commit()
        }
    var whiteBackgroundCheck: Boolean
        get() = pref.getBoolean(WHITE_BACKGROUND_CHECK, true)
        set(check){
            editor.putBoolean(WHITE_BACKGROUND_CHECK, check)
            editor.commit()
        }
    var dialBackgroundCheck: Boolean
        get() = pref.getBoolean(DIAL_BACKGROUND_CHECK, true)
        set(check){
            editor.putBoolean(DIAL_BACKGROUND_CHECK, check)
            editor.commit()
        }
    var cachedBitmap: String
        get() = pref.getString(CACHED_BITMAP, "null")
        set(cachedBitmap){
            editor.putString(CACHED_BITMAP, cachedBitmap)
            editor.commit()
        }
    var colorCode: Int
        get() = pref.getInt(COLOR_CODE, 1)
        set(colorCode){
            editor.putInt(COLOR_CODE, colorCode)
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
        private val HAND_POSITION = "handPosition"
        private val FACE_COLOR =  "faceColor"
        private val FACE_COLOR_DIALOG =  "faceColorDialog"
        private val DIAL_COLOR =  "dialColor"
        private val WHITE_BACKGROUND_CHECK =  "whiteBackgroundCheck"
        private val DIAL_BACKGROUND_CHECK =  "dialBackgroundCheck"
        private val CACHED_BITMAP =  "cachedBitmap"
        private val COLOR_CODE =  "colorCode"
    }
}