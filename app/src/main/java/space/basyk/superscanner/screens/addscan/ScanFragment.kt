package space.basyk.superscanner.screens.addscan

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import com.budiyev.android.codescanner.*
import kotlinx.coroutines.launch
import space.basyk.superscanner.R
import space.basyk.superscanner.databinding.FragmentScanBinding
import space.basyk.superscanner.utils.APP_ACTIVITY
import space.basyk.superscanner.utils.CAMERA_REQUEST_CODE
import space.basyk.superscanner.utils.showToast


 class ScanFragment : Fragment() {

    private var binding: FragmentScanBinding ?= null
    private val mBinding get() = binding!!
    lateinit var viewModel: ScanViewModel
    private lateinit var codeScanner: CodeScanner

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScanBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        codeScanner.stopPreview()

    }
    private fun init() {
        viewModel = ViewModelProvider(this).get(ScanViewModel::class.java)
        viewModel.initDatabase()
        viewModel.initDatabaseCategory()
        viewModel.num.observe(this) {
            checkNum(it)
        }
        setupPermission()
        codesScanner()
    }

     private fun checkNum(it: String) {
         val bundle = Bundle()
         bundle.putSerializable("number", it)
         viewModel.getQrResult(it){scanModel ->
             if (scanModel == null){
                 viewModel.viewModelScope.launch {
                     APP_ACTIVITY.navController.navigate(R.id.action_scanFragment_to_addItemFragment, bundle)
                 }
             }else{
                 viewModel.viewModelScope.launch {

                     showToast("Такой товар уже есть")
                 }
             }
         }

     }

     private fun codesScanner() {
        codeScanner = CodeScanner(APP_ACTIVITY, mBinding.scannerView)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                    APP_ACTIVITY.runOnUiThread {
                        viewModel.num.value = it.text
                }
            }
            errorCallback = ErrorCallback {
                APP_ACTIVITY.runOnUiThread {
                    showToast("Ошибка сканирования: ${it.message}")
                }
            }
        }
        mBinding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermission(){
        val permission = ContextCompat.checkSelfPermission(APP_ACTIVITY, android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(APP_ACTIVITY, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
       when(requestCode){
           CAMERA_REQUEST_CODE -> {
               if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                   showToast("Дайте разрешение")
               }
           }
       }
    }

}