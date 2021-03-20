package space.basyk.superscanner.data.repositories

import androidx.lifecycle.LiveData
import space.basyk.superscanner.data.dao.ScanDao
import space.basyk.superscanner.models.ScanModel

class ScanRoomRepository(private val scanDao: ScanDao): ScanDatabaseRepository {

    override val allScans: LiveData<List<ScanModel>>
        get() = scanDao.getAllItems()

    override suspend fun deleteAllScans() {
        scanDao.deleteAllItems()
    }

    override suspend fun insertScan(scanModel: ScanModel, onSuccess: () -> Unit) {
        scanDao.insert(scanModel)
        onSuccess()
    }

    override suspend fun deleteScan(scanModel: ScanModel, onSuccess: () -> Unit) {
        scanDao.delete(scanModel)
        onSuccess()
    }

    override suspend fun getQrResult(num: String, onSuccess: (ScanModel?) -> Unit) {
        onSuccess(scanDao.getQrResult(num))
    }


    override fun getSearchScan(searchQuery: String, onSuccess: () -> Unit): LiveData<List<ScanModel>> {
        return scanDao.searchScan(searchQuery)

    }


}