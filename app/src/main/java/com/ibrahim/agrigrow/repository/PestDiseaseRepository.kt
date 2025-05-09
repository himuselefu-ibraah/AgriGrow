package com.ibrahim.agrigrow.repository

import android.content.Context
import com.ibrahim.agrigrow.data.PestDiseaseDatabaseHelper
import com.ibrahim.agrigrow.model.PestDisease

class PestDiseaseRepository(context: Context) {
    private val dbHelper = PestDiseaseDatabaseHelper(context)

    fun getAllPests(): List<PestDisease> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM pests", null)
        val pests = mutableListOf<PestDisease>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val description = cursor.getString(2)
            pests.add(PestDisease(id, name, description))
        }

        cursor.close()
        db.close()
        return pests
    }
}


