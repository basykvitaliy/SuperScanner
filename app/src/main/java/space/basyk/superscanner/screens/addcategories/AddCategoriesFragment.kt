package space.basyk.superscanner.screens.addcategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import space.basyk.superscanner.R
import space.basyk.superscanner.databinding.FragmentAddCategoriesBinding
import space.basyk.superscanner.models.CategoriesModel
import space.basyk.superscanner.utils.APP_ACTIVITY


class AddCategoriesFragment : Fragment() {

    private var binding: FragmentAddCategoriesBinding ?= null
    private val mBinding get() = binding!!
    lateinit var viewModel: AddCategoriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddCategoriesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()

    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(AddCategoriesViewModel::class.java)
        mBinding.btnAdd.setOnClickListener {
            val title = mBinding.tvCategory.text.toString()
            viewModel.insertCategory(CategoriesModel(titleCategories = title)){}
            APP_ACTIVITY.navController.navigate(R.id.action_addCategoriesFragment_to_search_scan)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}