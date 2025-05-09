package com.ibrahim.agrigrow.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crops")
data class Crop(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val region: String,
    val plantingMonth: String,
    val harvestingMonth: String
)

