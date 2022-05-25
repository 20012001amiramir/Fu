package com.example.fu.ui.enter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fu.R
import com.example.fu.databinding.FragmentLoginBinding
import com.example.fu.databinding.FragmentMainEnterBinding
import com.example.fu.databinding.FragmentRegisterBinding
import com.example.fu.di.Scopes
import com.example.fu.di.factory.viewModels
import com.example.fu.util.navigateSafe

class MainEnterFragment : Fragment(R.layout.fragment_main_enter) {


    private val binding by viewBinding(FragmentMainEnterBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            register.setOnClickListener{
                findNavController().navigateSafe(R.id.mainEnterFragment, R.id.action_mainEnterFragment_to_registerFragment)
            }
            login.setOnClickListener{
                findNavController().navigateSafe(R.id.mainEnterFragment, R.id.action_mainEnterFragment_to_loginFragment)

            }
        }


    }
}