package com.example.coffescript

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = [Entity_Item_profile::class], version = 1)
abstract class DataBase_Profile: RoomDatabase() {

    abstract fun getDao_Profile(): Dao_profile
    companion object{
        fun getDb_Profile(context: Context): DataBase_Profile{
            return Room.databaseBuilder(context.applicationContext, DataBase_Profile::class.java, "Data_Profile.db" ).fallbackToDestructiveMigration().build()
        }
    }
}