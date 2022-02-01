package com.example.androidgasstation.entities

import kotlin.properties.Delegates

class Gas (private val gasID: Int, private val gasName: String, private val gasPrice : Double){
    var id : Int by Delegates.notNull()
    var name : String by Delegates.notNull()
    var price : Double by Delegates.notNull()
    init {
        this.id = gasID
        this.name = gasName
        this.price = gasPrice
    }

    companion object {
        private val unleaded = Gas(1, "Unleaded", 3.49)
        private val regular = Gas(2, "Regular", 3.59)
        private val special = Gas(3, "Special", 3.69)
        private val premium = Gas(4, "Premium", 3.89)

        @JvmStatic val gasOptions = arrayOf(unleaded, regular, special, premium)
    }
}