package space.basyk.superscanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import space.basyk.superscanner.models.ScanModel

@Dao
interface ScanDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(scanModel: ScanModel)

    @Delete
    suspend fun delete(scanModel: ScanModel)

    @Query("SELECT * from scan_table")
    fun getAllItems(): LiveData<List<ScanModel>>

    @Query("DELETE from scan_table")
    fun deleteAllItems()

    @Query("SELECT * FROM scan_table WHERE num = :num")
    fun getQrResult(num: String): ScanModel

    @Query("SELECT * from scan_table WHERE categories LIKE :searchQuery")
    fun searchScan(searchQuery: String): LiveData<List<ScanModel>>
}