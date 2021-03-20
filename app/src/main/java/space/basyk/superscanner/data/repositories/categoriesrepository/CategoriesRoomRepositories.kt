package space.basyk.superscanner.data.repositories.categoriesrepository

import androidx.lifecycle.LiveData
import space.basyk.superscanner.data.dao.CategoriesDao
import space.basyk.superscanner.models.CategoriesModel

class CategoriesRoomRepositories(private val categoriesDao: CategoriesDao): CategoriesRepository {
    override val allCategories: LiveData<List<CategoriesModel>>
        get() = categoriesDao.getAllCategories()

    override suspend fun insertCategories(categoriesModel: CategoriesModel, onSuccess: () -> Unit) {
        categoriesDao.insertCategory(categoriesModel)
        onSuccess()
    }

    override suspend fun deleteCategories(categoriesModel: CategoriesModel, onSuccess:() -> Unit) {
        categoriesDao.deleteCategories(categoriesModel)
        onSuccess()
    }
}