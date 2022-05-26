package com.example.fu.ui.home

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.fu.R
import com.example.fu.databinding.FragmentDashboardBinding
import com.example.fu.databinding.FragmentHomeBinding
import com.example.fu.di.Scopes
import com.example.fu.di.factory.viewModels
import com.example.fu.di.hasPermissions
import com.example.fu.ui.dashboard.DashboardViewModel
import com.example.fu.ui.enter.LoginViewModel
import com.example.fu.util.navigateSafe

@RequiresApi(Build.VERSION_CODES.N)
class HomeFragment : Fragment(R.layout.fragment_home) {


    private val binding by viewBinding(FragmentHomeBinding::bind)

    val homeViewModel: HomeViewModel by viewModels(Scopes.APP_SCOPE , Scopes.APP_ACTIVITY_SCOPE)

    private lateinit var codeScanner: CodeScanner

    private val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA
    )

    private val MY_PERMISSIONS_REQUEST = 777

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                }
                return
            }
            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initQrReader()

        val activity = requireActivity()
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                homeViewModel.loadGarbageInfo(it.text)
            }
        }
        binding.scannerView.setOnClickListener {
            if(context?.let { it1 -> hasPermissions(it1, arrayOf(Manifest.permission.CAMERA)) } == true) {
                codeScanner.startPreview()
            }
            else{
                AlertDialog
                    .Builder(it.context)
                    .setTitle("Error")
                    .setPositiveButton("Settings") { dialog, _ ->
                        openSettings()
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        homeViewModel.subjectGarbageState.observe(viewLifecycleOwner){
            when(it){
                is HomeViewModel.SubjectGarbageGetState.Blank -> {
                    binding.ProgressBar.isVisible = false
                }
                is HomeViewModel.SubjectGarbageGetState.LoadingProgress -> {
                    binding.ProgressBar.isVisible = true
                }
                is HomeViewModel.SubjectGarbageGetState.Data -> {
                    binding.ProgressBar.isVisible = false
                    if(it.info.success){
                        Toast.makeText(context, it.info.messages?.get(0) ?: "", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, it.info.messages?.get(0) ?: "", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        if(context?.let { it1 -> hasPermissions(it1, arrayOf(Manifest.permission.CAMERA)) } == true) {
            codeScanner.startPreview()
        }
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    fun initQrReader(){
        codeScanner = CodeScanner(requireContext(), binding.scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether
    }

    private fun openSettings() {

        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${requireActivity().packageName}")).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }

        activity?.startActivity(intent)
    }

}