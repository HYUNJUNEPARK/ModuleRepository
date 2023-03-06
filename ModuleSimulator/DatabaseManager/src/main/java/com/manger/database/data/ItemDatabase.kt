package com.manger.database.data

import android.content.Context
import androidx.room.*
import com.manger.database.DatabaseConst.ITEM_DATABASE_NAME
import com.manger.database.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        private var instance: ItemDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ItemDatabase? {
            if (instance == null) {
                synchronized(ItemDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java,
                        ITEM_DATABASE_NAME
                    ).build()
                }
            }
            return instance
        }
    }
}

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: Item)

    @Query("SELECT * FROM $ITEM_DATABASE_NAME")
    fun getAllItems(): List<Item>

    @Query("SELECT content FROM $ITEM_DATABASE_NAME WHERE id = :id")
    fun getItemById(id:Long): String

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM $ITEM_DATABASE_NAME")
    fun deleteAllItems()
}