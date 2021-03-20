package space.basyk.superscanner.screens.listitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_add_item.view.*
import kotlinx.android.synthetic.main.scan_item.view.*
import space.basyk.superscanner.R
import space.basyk.superscanner.models.ScanModel
import space.basyk.superscanner.utils.APP_ACTIVITY
import space.basyk.superscanner.utils.showToast

class ScanAdapter(private var viewModel: ListAllViewModel):RecyclerView.Adapter<ScanAdapter.MyViewHolder>() {

    var listScan = emptyList<ScanModel>()
    private lateinit var deleteViewModel: ListAllViewModel

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scan_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Picasso.with(APP_ACTIVITY).load(R.drawable.ic_launcher_background).centerCrop().into(holder.itemView.img_product)
        Glide.with(APP_ACTIVITY).load(listScan[position].photo).placeholder(R.drawable.ic_launcher_background).into(holder.itemView.img_product)
        holder.itemView.num_scan.text = listScan[position].num
        holder.itemView.name_scan.text = listScan[position].name
        holder.itemView.price_scan.text = listScan[position].price.toString()
        holder.itemView.tv_categories.text = listScan[position].categories
        holder.itemView.img_delete.setOnClickListener {
            showToast("Товар удален!")
            deleteViewModel.deleteScanned(listScan[position])
        }


    }

    override fun getItemCount(): Int {
        return listScan.size
    }

    fun setList(list: List<ScanModel>){
        listScan = list
        deleteViewModel = viewModel
        notifyDataSetChanged()
    }


}