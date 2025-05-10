package com.ibrahim.agrigrow.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FertilizerDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CROP TEXT,
                $COLUMN_AREA TEXT,
                $COLUMN_FERTILIZER_AMOUNT TEXT
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun saveCalculation(crop: String, area: String, fertilizerAmount: String) {
        val db = this.writableDatabase
        val insertStatement = """
            INSERT INTO $TABLE_NAME ($COLUMN_CROP, $COLUMN_AREA, $COLUMN_FERTILIZER_AMOUNT)
            VALUES (?, ?, ?)
        """
        val statement = db.compileStatement(insertStatement)
        statement.bindString(1, crop)
        statement.bindString(2, area)
        statement.bindString(3, fertilizerAmount)
        statement.executeInsert()
        db.close()
    }

    companion object {
        private const val DATABASE_NAME = "fertilizer_calculations.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "calculations"
        const val COLUMN_ID = "id"
        const val COLUMN_CROP = "crop"
        const val COLUMN_AREA = "area"
        const val COLUMN_FERTILIZER_AMOUNT = "fertilizer_amount"
    }
}


