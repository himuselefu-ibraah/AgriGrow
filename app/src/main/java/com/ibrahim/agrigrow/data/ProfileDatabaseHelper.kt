package com.ibrahim.agrigrow.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class UserProfile(val name: String, val email: String, val phone: String, val imageId: Int)

class ProfileDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "ProfileDB", null, 1) {

    // Keep a single database reference open
    private val db: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS profile (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT, " +
                    "email TEXT, " +
                    "phone TEXT, " +
                    "imageId INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS profile")
        onCreate(db)
    }

    fun saveOrUpdateProfile(name: String, email: String, phone: String, imageId: Int) {
        val values = ContentValues().apply {
            put("name", name)
            put("email", email)
            put("phone", phone)
            put("imageId", imageId)
        }

        val cursor = db.rawQuery("SELECT * FROM profile WHERE id = 1", null)
        if (cursor.moveToFirst()) {
            db.update("profile", values, "id = ?", arrayOf("1"))
        } else {
            values.put("id", 1)
            db.insert("profile", null, values)
        }
        cursor.close()
    }

    fun getProfile(): UserProfile? {
        val cursor = db.rawQuery("SELECT * FROM profile WHERE id = 1", null)
        return if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            val phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
            val imageId = cursor.getInt(cursor.getColumnIndexOrThrow("imageId"))
            cursor.close()
            UserProfile(name, email, phone, imageId)
        } else {
            cursor.close()
            null
        }
    }
}
