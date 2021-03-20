package space.basyk.superscanner.screens.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import space.basyk.superscanner.R
import space.basyk.superscanner.databinding.FragmentSearchBinding
import space.basyk.superscanner.utils.APP_ACTIVITY
import space.basyk.superscanner.utils.showToast


class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding ?= null
    private val mBinding get() = binding!!
    lateinit var viewModel: SearchViewModel
    lateinit var recyclerView: RecyclerView
    val adapter by lazy { SearchAdapter(viewModel) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        recyclerView.adapter = null
    }
    private fun init() {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        recyclerView = mBinding.recyclerCategories
        recyclerView.adapter = adapter
        viewModel.getAllCategories.observe(this, {
            val list = it.asReversed()
            adapter.setList(list)
        })
        mBinding.btnPlus.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_search_scan_to_addCategoriesFragment)
        }

        val itemSwipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               showToast("Категория " + adapter.delCategory(viewHolder.adapterPosition).titleCategories + " удалена")
            }

        }
        val swap = ItemTouchHelper(itemSwipe)
        swap.attachToRecyclerView(recyclerView)

    }


}