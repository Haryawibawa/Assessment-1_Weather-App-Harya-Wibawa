package org.d3if3106.weather

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import org.d3if3106.weather.WeatherContract.WeatherEntry

class WeatherDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "weather.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_WEATHER_TABLE = """
            CREATE TABLE ${WeatherEntry.TABLE_NAME} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${WeatherEntry.COLUMN_DATE} INTEGER,
                ${WeatherEntry.COLUMN_TEMPERATURE} REAL,
                ${WeatherEntry.COLUMN_DESCRIPTION} TEXT
            );
        """.trimIndent()

        db.execSQL(SQL_CREATE_WEATHER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Implementasikan jika Anda ingin memperbarui skema basis data di masa mendatang
    }
}
