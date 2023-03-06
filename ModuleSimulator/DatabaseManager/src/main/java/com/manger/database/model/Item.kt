package com.manger.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manger.database.DatabaseConst.ITEM_DATABASE_NAME

@Entity(tableName = ITEM_DATABASE_NAME)
data class Item(
    @PrimaryKey
    var id: Long,

    @ColumnInfo
    var content: String
)
