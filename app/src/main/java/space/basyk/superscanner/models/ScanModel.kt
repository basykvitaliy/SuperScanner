package space.basyk.superscanner.models

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "scan_table")
data class ScanModel (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val num: String,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val price: Int,

    @ColumnInfo
    val categories: String,

    @ColumnInfo
    val photo: String = ""
): Serializable