package org.d3if3106.weather

import android.provider.BaseColumns

class WeatherContract {
    // Nama tabel
    object WeatherEntry : BaseColumns {
        const val TABLE_NAME = "weather"
        const val COLUMN_DATE = "date"
        const val COLUMN_TEMPERATURE = "temperature"
        const val COLUMN_DESCRIPTION = "description"
    }
}