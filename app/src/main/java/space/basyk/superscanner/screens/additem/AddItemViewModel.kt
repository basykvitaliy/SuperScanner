package space.basyk.superscanner.screens.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.basyk.superscanner.models.ScanModel
import space.basyk.superscanner.utils.REPOSITORY
import space.basyk.superscanner.utils.REPOSITORY_CATEGORIES

class AddItemViewModel: ViewModel() {

    val getAllCateg = REPOSITORY_CATEGORIES.allCategories

    fun addScan(scanModel: ScanModel, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insertScan(scanModel){
                onSuccess()
            }
    }

}