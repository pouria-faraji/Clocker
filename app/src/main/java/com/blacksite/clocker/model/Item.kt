package com.blacksite.clocker.model

/**
 * Created by p.faraji on 4/17/2018.
 */
class Item {
    var image: Int? = null
    var imageWhite: Int? = null
    constructor(image: Int) {
        this.image = image
    }
    constructor(image: Int, imageWhite:Int) {
        this.image = image
        this.imageWhite = imageWhite
    }
}