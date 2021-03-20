package space.basyk.superscanner.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.basyk.superscanner.models.CategoriesModel
import space.basyk.superscanner.models.ScanModel

class DeleteUseCase(private val viewModelScope: CoroutineScope) {
    fun deleteScan(scanModel: ScanModel, onSuccess:() -> Unit) =
            viewModelScope.launch(Dispatchers.IO) {
                REPOSITORY.deleteScan(scanModel){
                    onSuccess()
                }
            }

    fun deleteCategories(categoriesModel: CategoriesModel, onSuccess:() -> Unit)=
            viewModelScope.launch(Dispatchers.IO) {
                REPOSITORY_CATEGORIES.deleteCategories(categoriesModel){
                    onSuccess()
                }
            }
}