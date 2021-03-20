package space.basyk.superscanner.data.repositories.categoriesrepository

import androidx.lifecycle.LiveData
import space.basyk.superscanner.models.CategoriesModel


interface CategoriesRepository {

    val allCategories: LiveData<List<CategoriesModel>>
    suspend fun insertCategories(categoriesModel: CategoriesModel, onSuccess:() -> Unit)
    suspend fun deleteCategories(categoriesModel: CategoriesModel, onSuccess:() -> Unit)

}