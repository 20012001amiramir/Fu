package com.example.fu.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fu.R
import com.example.fu.databinding.FragmentHomeBinding
import com.example.fu.databinding.FragmentNotificationsBinding
import com.example.fu.di.Scopes
import com.example.fu.di.factory.viewModels
import com.example.fu.ui.home.HomeViewModel

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {


    private val binding by viewBinding(FragmentNotificationsBinding::bind)

    val viewModel: NotificationsViewModel by viewModels(Scopes.APP_SCOPE , Scopes.APP_ACTIVITY_SCOPE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val textView: TextView = binding.textNotifications
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}