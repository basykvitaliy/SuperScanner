package space.basyk.superscanner.data.repositories

import androidx.lifecycle.LiveData
import space.basyk.superscanner.models.ScanModel

interface ScanDatabaseRepository {
    val allScans: LiveData<List<ScanModel>>

    suspend fun deleteAllScans()
    suspend fun insertScan(scanModel: ScanModel, onSuccess:() -> Unit)
    suspend fun deleteScan(scanModel: ScanModel, onSuccess:() -> Unit)
    suspend fun getQrResult(num: String, onSuccess: (ScanModel?) -> Unit)
    fun getSearchScan(searchQuery: String, onSuccess: () -> Unit): LiveData<List<ScanModel>>

}