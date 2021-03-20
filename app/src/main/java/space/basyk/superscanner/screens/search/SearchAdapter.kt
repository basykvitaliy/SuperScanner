package space.basyk.superscanner.screens.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.categories_item.view.*
import space.basyk.superscanner.R
import space.basyk.superscanner.models.CategoriesModel

class SearchAdapter(private var viewModel: SearchViewModel): RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {



    private var listCategories = emptyList<CategoriesModel>()
    private lateinit var deleteViewModel: SearchViewModel

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.title.text = listCategories[position].titleCategories

    }

    override fun getItemCount(): Int {
        return listCategories.size
    }

    fun setList(list: List<CategoriesModel>){
        listCategories = list
        deleteViewModel = viewModel
        notifyDataSetChanged()
    }

    fun delCategory(pos:Int): CategoriesModel{
       deleteViewModel.deleteCategories(listCategories[pos])
        return listCategories[pos]

    }




}