package space.basyk.superscanner.screens.listitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.basyk.superscanner.data.repositories.DeleteRepository
import space.basyk.superscanner.models.ScanModel
import space.basyk.superscanner.utils.DeleteUseCase
import space.basyk.superscanner.utils.REPOSITORY

class ListAllViewModel: ViewModel() {
    val getAllScan = REPOSITORY.allScans

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.deleteAllScans()
        }
    }

    val repo = DeleteRepository(DeleteUseCase(viewModelScope))
    fun deleteScanned(scanModel: ScanModel){
       repo.deleteScan(scanModel)
    }

     fun searchDatabase(searchQuery: String,onSuccess: () -> Unit): LiveData<List<ScanModel>>{
        return REPOSITORY.getSearchScan(searchQuery){
            onSuccess()
        }

    }
}