package com.example.fu.ui.dashboard

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fu.R
import com.example.fu.databinding.FragmentDashboardBinding
import com.example.fu.di.AppModule
import com.example.fu.di.Scopes
import com.example.fu.di.factory.viewModels
import com.example.fu.di.objectScopeName
import com.example.fu.ui.common.extention.swapItems
import com.example.fu.ui.home.HomeViewModel
import com.example.fu.ui.typeGarbageListDelegate
import com.example.fu.util.navigateSafe
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import toothpick.Toothpick

class ScanedGarbagesFragment : Fragment(R.layout.fragment_dashboard){

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    private val delegates = ListDelegationAdapter(
        typeGarbageListDelegate()
    )

    private val scanedGarbagesViewModel: ScanedGarbagesViewModel by viewModels(Scopes.APP_SCOPE , Scopes.objectScopeName)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toothpick
            .openScope(Scopes.objectScopeName)
            .installModules(context?.let { AppModule(context = it) })

        binding.recyclerview.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = delegates
        }
        scanedGarbagesViewModel.loadGarbagesInfo()

        scanedGarbagesViewModel.garbageScannedGarbagesState.observe(viewLifecycleOwner){
            when(it){
                is GarbageViewState.BlankViewState -> {
                    binding.proggressBar.isVisible = false
                    binding.empty.isVisible = true
                }
                is GarbageViewState.LoadingViewState -> {
                    binding.proggressBar.isVisible = true
                    binding.empty.isVisible = false
                }
                is GarbageViewState.Data -> {
                    binding.proggressBar.isVisible = false
                    binding.empty.isVisible = false

                    delegates.swapItems(it.data.data)

                }
            }
        }

    }
}