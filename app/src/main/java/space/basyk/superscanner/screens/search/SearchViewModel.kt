package space.basyk.superscanner.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.basyk.superscanner.data.repositories.DeleteRepository
import space.basyk.superscanner.models.CategoriesModel
import space.basyk.superscanner.models.ScanModel
import space.basyk.superscanner.utils.DeleteUseCase
import space.basyk.superscanner.utils.REPOSITORY_CATEGORIES

class SearchViewModel: ViewModel() {
    val getAllCategories = REPOSITORY_CATEGORIES.allCategories

    val repo = DeleteRepository(DeleteUseCase(viewModelScope))
    fun deleteCategories(categoriesModel: CategoriesModel){
        repo.deleteCategories(categoriesModel)
    }
}