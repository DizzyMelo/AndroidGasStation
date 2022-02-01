package com.example.androidgasstation.enums

enum class PaymentType(val description: String) {
    CASH("Cash"),
    CARD("Card"),
    GOOGLE_PAY("Google Pay"),
    APPLE_PAY("Apple Pay")
}