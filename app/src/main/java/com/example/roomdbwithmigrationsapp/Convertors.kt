package com.example.roomdbwithmigrationsapp

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

class Convertors {
    @TypeConverter
    fun fromLongToDate(value: Long): Date{
        return Date(value)
    }

    @TypeConverter
    fun fromDateToLong(value: Date): Long{
        return value.time
    }
}