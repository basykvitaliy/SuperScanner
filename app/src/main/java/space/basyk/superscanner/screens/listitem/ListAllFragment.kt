package space.basyk.superscanner.screens.listitem

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import space.basyk.superscanner.R
import space.basyk.superscanner.databinding.FragmentListAllBinding
import space.basyk.superscanner.utils.showToast


class ListAllFragment : Fragment(){

    private var binding: FragmentListAllBinding ?= null
    private val mBinding get() = binding!!
    lateinit var viewModel: ListAllViewModel
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { ScanAdapter(viewModel) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListAllBinding.inflate(layoutInflater, container ,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        recyclerView.adapter = null
    }
    private fun init() {
        viewModel = ViewModelProvider(this).get(ListAllViewModel::class.java)
        recyclerView = mBinding.recView
        recyclerView.adapter = adapter
        viewModel.getAllScan.observe(this) {
            val list = it.asReversed()
            adapter.setList(list)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
        searchView?.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                search.collapseActionView()
                showToast("Lock for $query")
                if (query != null){
                    searchDatabase(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    searchDatabase(newText)
                }
                return false
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all_table -> {
                viewModel.deleteAll()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"
        viewModel.searchDatabase(searchQuery){}.observe(this, { list ->
            list.let {
                adapter.setList(it)
            }
        })
    }



}