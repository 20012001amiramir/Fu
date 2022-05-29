package com.example.fu.ui.home

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fu.R
import com.example.fu.data.network.request.AddGarbageRequest
import com.example.fu.databinding.FragmentDialogAddGarbageBinding
import com.example.fu.di.AppModule
import com.example.fu.di.Scopes
import com.example.fu.di.factory.viewModels
import com.example.fu.di.objectScopeName
import com.example.fu.ui.common.extention.*
import com.example.fu.ui.dashboard.GarbageViewState
import com.example.fu.util.versionAllowsToFullyColorizeNavBar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import toothpick.Toothpick
import java.io.File


class AddGarbageDialogFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentDialogAddGarbageBinding::bind)

    private val args: AddGarbageDialogFragmentArgs by navArgs()

//  private val tagList: List<>

    private val viewModel: AddGarbageDialogViewModel by viewModels(Scopes.APP_ACTIVITY_SCOPE, Scopes.objectScopeName )
    
//    private val delegates = ListDelegationAdapter(
//
//    )
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Toothpick
            .openScope(Scopes.objectScopeName)
            .installModules(context?.let { AppModule(context = it) })
        return inflater.inflate(R.layout.fragment_dialog_add_garbage, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            forceExpandedState()
            forceBackgroundRoundCorners { dialog }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            barcode.setText(args.barcode)
            addChipView("something")
            addChipView("something")
            addChipView("something")
            addChipView("something")
            addChipView("something")
            addChipView("something")
            close.setOnClickListener {
                activity?.onBackPressed()
            }
            okey.setOnClickListener {
                viewModel.addGarbage(
                    AddGarbageRequest(
                        binding.name.text.toString(),
                        2,
                        binding.barcode.text.toString(),
                        null
                    )
                )
            }
            viewModel.addGarbageState.observe(viewLifecycleOwner){
                when(it){
                    is AddGarbageViewState.BlankViewState -> {
                        binding.proggressBar.isVisible = false
                        okey.isVisible = true
                    }
                    is AddGarbageViewState.LoadingViewState -> {
                        binding.proggressBar.isVisible = true
                        okey.isVisible = false
                    }
                    is AddGarbageViewState.LoadingError -> {
                        binding.proggressBar.isVisible = false
                        okey.isVisible = true
                        Toast.makeText(context, "Чето не так(", Toast.LENGTH_SHORT).show()
                    }
                    is AddGarbageViewState.Data -> {
                        if(it.data.success){
                            activity?.onBackPressed()
                        }
                        else{
                            Toast.makeText(context, "Чето не так(", Toast.LENGTH_SHORT).show()
                        }
                        okey.isVisible = true
                        binding.proggressBar.isVisible = false
                    }
                }

            }
        }
    }

    private fun addChipView(chipText: String) {
        val chip = layoutInflater.inflate(R.layout.item_garbage_type, binding.types, false) as Chip
        chip.text = chipText
        val paddingDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 10f,
            resources.displayMetrics
        ).toInt()
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
        binding.types.addView(chip)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (versionAllowsToFullyColorizeNavBar()) {
            requireView().setFitsSystemWindowsForParents(false)
        }
    }

}