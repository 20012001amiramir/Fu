package com.example.fu.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fu.R
import com.example.fu.databinding.FragmentDashboardBinding
import com.example.fu.di.Scopes
import com.example.fu.di.factory.viewModels

class DashboardFragment : Fragment(R.layout.fragment_dashboard){

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dashboardViewModel: DashboardViewModel by viewModels(Scopes.APP_SCOPE , Scopes.APP_ACTIVITY_SCOPE)

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}