package com.ibrahim.agrigrow.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibrahim.agrigrow.model.AnimalHealth

@Database(entities = [AnimalHealth::class], version = 1)
abstract class AnimalHealthDatabase : RoomDatabase() {
    abstract fun animalHealthDao(): AnimalHealthDao

    companion object {
        @Volatile private var INSTANCE: AnimalHealthDatabase? = null

        fun getDatabase(context: Context): AnimalHealthDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AnimalHealthDatabase::class.java,
                    "animal_health_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}



