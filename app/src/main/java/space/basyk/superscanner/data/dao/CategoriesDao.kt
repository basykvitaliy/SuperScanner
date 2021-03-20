package space.basyk.superscanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import space.basyk.superscanner.models.CategoriesModel
import space.basyk.superscanner.models.ScanModel

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(categoriesModel: CategoriesModel)

    @Delete
    suspend fun deleteCategories(categoriesModel: CategoriesModel)

    @Query("SELECT * from categories_table")
    fun getAllCategories(): LiveData<List<CategoriesModel>>


}