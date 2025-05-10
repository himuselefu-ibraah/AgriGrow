package com.ibrahim.agrigrow.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProfitMarginDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "profit_margin.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "profit_margin"
        private const val COLUMN_ID = "id"
        private const val COLUMN_CROP_NAME = "crop_name"
        private const val COLUMN_COST = "cost"
        private const val COLUMN_INCOME = "income"
        private const val COLUMN_PROFIT_MARGIN = "profit_margin"

        private const val CREATE_TABLE_QUERY = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CROP_NAME TEXT,
                $COLUMN_COST REAL,
                $COLUMN_INCOME REAL,
                $COLUMN_PROFIT_MARGIN REAL
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Save profit margin data
    fun saveProfitMargin(cropName: String, cost: Double, income: Double) {
        val db = writableDatabase
        val profitMargin = (income - cost) / income * 100
        val query = "INSERT INTO $TABLE_NAME ($COLUMN_CROP_NAME, $COLUMN_COST, $COLUMN_INCOME, $COLUMN_PROFIT_MARGIN) VALUES (?, ?, ?, ?)"
        val statement = db.compileStatement(query)
        statement.bindString(1, cropName)
        statement.bindDouble(2, cost)
        statement.bindDouble(3, income)
        statement.bindDouble(4, profitMargin)
        statement.executeInsert()
    }

    // Fetch all profit margins
    fun getAllProfitMargins(): List<ProfitMargin> {
        val profitMargins = mutableListOf<ProfitMargin>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val cropName = cursor.getString(cursor.getColumnIndex(COLUMN_CROP_NAME))
                val cost = cursor.getDouble(cursor.getColumnIndex(COLUMN_COST))
                val income = cursor.getDouble(cursor.getColumnIndex(COLUMN_INCOME))
                val profitMargin = cursor.getDouble(cursor.getColumnIndex(COLUMN_PROFIT_MARGIN))

                profitMargins.add(ProfitMargin(cropName, cost, income, profitMargin))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return profitMargins
    }
}

data class ProfitMargin(val cropName: String, val cost: Double, val income: Double, val profitMargin: Double)
