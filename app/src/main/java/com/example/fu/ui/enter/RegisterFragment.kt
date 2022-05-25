package com.example.fu.ui.enter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fu.R
import com.example.fu.databinding.FragmentRegisterBinding
import com.example.fu.di.Scopes
import com.example.fu.di.factory.viewModels

class RegisterFragment : Fragment(R.layout.fragment_register) {


    private val binding by viewBinding(FragmentRegisterBinding::bind)

    val viewModel: RegisterViewModel by viewModels(Scopes.APP_SCOPE , Scopes.APP_ACTIVITY_SCOPE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.text.observe(viewLifecycleOwner) {
        }
    }
}