package space.basyk.superscanner.screens.additem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.spinner_item.view.*
import space.basyk.superscanner.R
import space.basyk.superscanner.models.CategoriesModel
import space.basyk.superscanner.utils.APP_ACTIVITY

class CategoryAdapter(context: Context, listCategory: List<CategoriesModel>): ArrayAdapter<CategoriesModel>(context, 0, listCategory) {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View{
        val category = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)

        view.title_spinner.text = category?.titleCategories
        return view
    }



}