package com.example.androidgasstation.utils

class Metrics {
    companion object {
        // 13 gallons per 60 secons / 0.22 per second / 0.043 per 1/5 second
        // amount / price = gallons

        private const val GALLON_PER_MINUTE = 13.0
        private const val GALLON_PER_SECOND = GALLON_PER_MINUTE/60.0
        private const val SPEED_RATIO = 100.0
        const val PUMPER_SPEED = 1000 / SPEED_RATIO
        const val AMOUNT_SPEED_RATIO = GALLON_PER_SECOND / SPEED_RATIO
    }
}