package space.basyk.superscanner.screens.addcategories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.basyk.superscanner.models.CategoriesModel
import space.basyk.superscanner.utils.REPOSITORY_CATEGORIES

class AddCategoriesViewModel: ViewModel() {
    fun insertCategory(categoriesModel: CategoriesModel, onSuccess:() -> Unit)=
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY_CATEGORIES.insertCategories(categoriesModel){
                onSuccess()
            }
        }
}