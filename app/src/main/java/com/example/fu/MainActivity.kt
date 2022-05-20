package com.example.fu

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fu.databinding.ActivityMainBinding
import com.example.fu.di.AppActivityModule
import com.example.fu.di.Scopes.APP_ACTIVITY_SCOPE
import com.example.fu.di.Scopes.APP_SCOPE
import com.example.fu.di.factory.viewModels
import com.example.fu.util.ifNotNull
import com.example.fu.util.navController
import com.example.fu.util.versionAllowsToFullyColorizeNavBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import toothpick.Toothpick
import toothpick.smoothie.lifecycle.closeOnDestroy

class MainActivity : AppCompatActivity() {

    private var blockingDialog: AlertDialog? = null

    private lateinit var binding: ActivityMainBinding

    private val viewModel: AppActivityViewModel by viewModels(APP_SCOPE, APP_ACTIVITY_SCOPE)

    private val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA
    )

    private val topLevelDestinations = listOf(
        R.id.navigation_home,
        R.id.navigation_dashboard,
        R.id.navigation_notifications
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.let {
            ActivityCompat.requestPermissions(
                it, PERMISSIONS,
                777
            )
        }

        Toothpick.inject(this, Toothpick.openScope(APP_SCOPE))

        Toothpick
            .openScopes(APP_SCOPE, APP_ACTIVITY_SCOPE)
            .installModules(AppActivityModule(activitycontext = this))
            .closeOnDestroy(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = navController(R.id.navHostFragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            changeVisibilityOfBottomNavigation(destination.id)
            changeNavigationBarColorsIfPossible(destination.id)
        }

        initBottomNavigation(navController)

        viewModel.appViewState.observe(this, ::renderAppViewState)

    }

    private fun initBottomNavigation(navController: NavController) {
        with(binding) {
            NavigationUI.setupWithNavController(navView, navController)
        }
    }

    private fun changeNavigationBarColorsIfPossible(@IdRes destinationId: Int) {
        if (versionAllowsToFullyColorizeNavBar()) {
            window?.navigationBarColor = when (destinationId) {
                in topLevelDestinations -> getColor(R.color.white)
                else -> getColor(R.color.white)
            }

        }
    }

    private fun changeVisibilityOfBottomNavigation(@IdRes destinationId: Int) {
        binding.navView.isVisible = destinationId in topLevelDestinations
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        blockingDialog?.let {
            outState.putBundle(BLOCKING_DIALOG_TAG, it.onSaveInstanceState())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        ifNotNull(blockingDialog, savedInstanceState.getBundle(BLOCKING_DIALOG_TAG)) { dialog, bundle ->
            dialog.onRestoreInstanceState(bundle)
        }
    }

    private fun setMainScreen(@IdRes destinationId: Int) {
        val navController = findNavController(R.id.navHostFragment)
        val navGraph = navController.navInflater.inflate(R.navigation.mobile_navigation)
        navGraph.setStartDestination(destinationId)
        navController.graph = navGraph

        changeNavigationBarColorsIfPossible(destinationId)

    }

    private fun renderAppViewState(appViewState: AppActivityViewModel.AppViewState) {
        when (appViewState) {
            is AppActivityViewModel.AppViewState.Onboarding -> {
                setMainScreen(R.id.navigation_home )
            }
            is AppActivityViewModel.AppViewState.Login -> {
                setMainScreen(R.id.navigation_dashboard)
            }
            is AppActivityViewModel.AppViewState.Schedule -> {
                setMainScreen(R.id.navigation_notifications)
            }
            is AppActivityViewModel.AppViewState.QrReader -> {
                setMainScreen(R.id.navigation_home)
            }
            is AppActivityViewModel.AppViewState.CurrentFragmentState -> {
                //This is state activity should operate after auth is done to support configuration changes
                //without replacing current fragment. In this state Android lifecycle decides the fate of current screen
            }
        }
    }
    companion object {
        const val LOGOUT_KEY = "LOGOUT_KEY"
        const val ID_CURRENT_PAGE = "ID_CURRENT_PAGE"
        private const val LOADING_DIALOG_TAG = "LOADING_DIALOG_TAG"
        private const val BLOCKING_DIALOG_TAG = "BLOCKING_DIALOG_TAG"
    }
}