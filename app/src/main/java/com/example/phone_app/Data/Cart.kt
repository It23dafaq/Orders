package com.example.phone_app.Data

class Cart {
    var productName: String=""
    var productPrice: Float = 0.0F

    constructor()

    constructor(productName: String,productPrice: Float){
        this.productName=productName
        this.productPrice=productPrice
    }
}