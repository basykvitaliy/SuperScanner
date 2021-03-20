package space.basyk.superscanner.data.repositories

import space.basyk.superscanner.models.CategoriesModel
import space.basyk.superscanner.models.ScanModel
import space.basyk.superscanner.utils.DeleteUseCase

class DeleteRepository(private val deleteUseCase: DeleteUseCase) {
    fun deleteScan(scanModel: ScanModel){
        deleteUseCase.deleteScan(scanModel){}
    }

    fun deleteCategories(categoriesModel: CategoriesModel){
        deleteUseCase.deleteCategories(categoriesModel){}
    }
}