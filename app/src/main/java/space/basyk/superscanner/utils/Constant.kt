package space.basyk.superscanner.utils

import space.basyk.superscanner.MainActivity
import space.basyk.superscanner.data.repositories.ScanDatabaseRepository
import space.basyk.superscanner.data.repositories.categoriesrepository.CategoriesRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: ScanDatabaseRepository
lateinit var REPOSITORY_CATEGORIES: CategoriesRepository
const val CAMERA_REQUEST_CODE = 120