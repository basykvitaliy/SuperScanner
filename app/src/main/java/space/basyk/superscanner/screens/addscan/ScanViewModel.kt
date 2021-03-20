package space.basyk.superscanner.screens.addscan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.basyk.superscanner.data.database.ScanDatabase
import space.basyk.superscanner.data.repositories.ScanDatabaseRepository
import space.basyk.superscanner.data.repositories.ScanRoomRepository
import space.basyk.superscanner.data.repositories.categoriesrepository.CategoriesRoomRepositories
import space.basyk.superscanner.models.ScanModel
import space.basyk.superscanner.utils.REPOSITORY
import space.basyk.superscanner.utils.REPOSITORY_CATEGORIES
import space.basyk.superscanner.utils.showToast

class ScanViewModel(application: Application): AndroidViewModel(application) {

    private val context = application
    val num = MutableLiveData<String>()

    fun initDatabase(){
        val daoScan = ScanDatabase.getInstance(context).getScanDao()
        REPOSITORY = ScanRoomRepository(daoScan)
    }

    fun initDatabaseCategory(){
        val daoCategory = ScanDatabase.getInstance(context).getCategories()
        REPOSITORY_CATEGORIES = CategoriesRoomRepositories(daoCategory)
    }

    fun getQrResult(num: String, onSuccess:(ScanModel?) -> Unit)=
        viewModelScope.launch(Dispatchers.IO) {
           REPOSITORY.getQrResult(num){
               onSuccess(it)
           }
    }


}