package space.basyk.superscanner.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import space.basyk.superscanner.data.dao.CategoriesDao
import space.basyk.superscanner.data.dao.ScanDao
import space.basyk.superscanner.models.CategoriesModel
import space.basyk.superscanner.models.ScanModel

@Database(entities = [ScanModel::class, CategoriesModel::class], version = 5)
abstract class ScanDatabase: RoomDatabase() {

    abstract fun getScanDao(): ScanDao
    abstract fun getCategories(): CategoriesDao

    companion object{
        @Volatile
        private var database: ScanDatabase ?= null

        @Synchronized
        fun getInstance(context: Context):ScanDatabase{
            return if (database == null){
                database = Room.databaseBuilder(context, ScanDatabase::class.java, "db").fallbackToDestructiveMigration().build()
                database as ScanDatabase
            }else{
                database as ScanDatabase
            }
        }
    }

}