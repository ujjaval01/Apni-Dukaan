package com.uv.apnidukaan.model

data class Transaction(
    val id: Int,
    val itemName: String,
    val quantity: Int,
    val price: Double,
    val date: String,
    val type: String // "SELL" or "BUY"
)
