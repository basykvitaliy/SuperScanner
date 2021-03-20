package space.basyk.superscanner.screens.additem

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.drawToBitmap
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.categories_item.view.*
import kotlinx.android.synthetic.main.spinner_item.view.*
import space.basyk.superscanner.R
import space.basyk.superscanner.databinding.FragmentAddItemBinding
import space.basyk.superscanner.models.CategoriesModel
import space.basyk.superscanner.models.ScanModel
import space.basyk.superscanner.utils.APP_ACTIVITY
import space.basyk.superscanner.utils.CAMERA_REQUEST_CODE
import space.basyk.superscanner.utils.showToast
import java.io.File


class AddItemFragment : Fragment() {

    private var binding: FragmentAddItemBinding ?= null
    private val mBinding get() = binding!!
    lateinit var viewModel: AddItemViewModel
    lateinit var currentTextView: String
    lateinit var spinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemBinding.inflate(layoutInflater, container, false)
        currentTextView = arguments?.getSerializable("number") as String
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }
    private fun init() {
        viewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
        setSpinner()
        mBinding.tvNumber.text = currentTextView
        mBinding.btnAdd.setOnClickListener {
            val categories = mBinding.spinnerCategories.selectedItem as CategoriesModel
            val num = mBinding.tvNumber.text.toString()
            val name = mBinding.tvName.text.toString()
            val price = mBinding.tvPrice.text.toString().toInt()
            //val photo = mBinding.setPhoto.drawToBitmap()
            viewModel.addScan(ScanModel(num = num, name = name, price = price, categories = categories.titleCategories)){}
            Log.d("ASD", mBinding.spinnerCategories.selectedItem.toString())
            APP_ACTIVITY.navController.navigate(R.id.action_addItemFragment_to_scanFragment)
        }
        mBinding.imagePhoto.setOnClickListener {
            showToast("Button click")
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, CAMERA_REQUEST_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE){
            var picture = data?.getParcelableExtra<Bitmap>("data")
            mBinding.setPhoto.visibility = View.VISIBLE
            mBinding.setPhoto.setImageBitmap(picture)

        }
    }

    fun setSpinner(){
        spinner = mBinding.spinnerCategories
        viewModel.getAllCateg.observe(this, {list ->
            val adapter = CategoryAdapter(APP_ACTIVITY, list.asReversed())
            spinner.adapter = adapter
        })

    }



}