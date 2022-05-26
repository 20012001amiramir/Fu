package com.example.fu.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fu.R
import com.example.fu.databinding.FragmentDialogAddGarbageBinding
import com.example.fu.di.Scopes
import com.example.fu.di.factory.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.example.fu.ui.common.extention.*
import com.example.fu.util.versionAllowsToFullyColorizeNavBar

class AddGarbageDialogFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentDialogAddGarbageBinding::bind)

    private val viewModel: AddGarbageDialogViewModel by viewModels(Scopes.APP_ACTIVITY_SCOPE, Scopes.ADD_GARBAGE )
    
    private val delegates = ListDelegationAdapter(
       
    )
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dialog_add_garbage, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            forceExpandedState()
            forceBackgroundRoundCorners { dialog }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){

        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (versionAllowsToFullyColorizeNavBar()) {
            requireView().setFitsSystemWindowsForParents(false)
        }
    }

}