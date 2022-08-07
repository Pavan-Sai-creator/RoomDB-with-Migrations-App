package com.example.roomdbwithmigrationsapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 2)
@TypeConverters(Convertors::class)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDAO() : ContactDAO

    companion object{

        val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }
        }

        @Volatile
        private var INSTANCE : ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase{
            synchronized(this){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ContactDatabase::class.java,
                    "contactDB")
                        .addMigrations(migration_1_2)
                        .build()
                }
                    return INSTANCE!!
            }
        }

    }
}