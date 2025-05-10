package com.ibrahim.agrigrow.data
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProfileDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "profile.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE profile (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                email TEXT,
                phone TEXT,
                image_id INTEGER
            );
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS profile")
        onCreate(db)
    }

    fun saveProfile(name: String, email: String, phone: String, imageId: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("email", email)
            put("phone", phone)
            put("image_id", imageId)
        }
        db.insert("profile", null, values)
    }
}

