package com.example.fu.ui.common.view

import android.animation.ArgbEvaluator
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.fu.R
import com.example.fu.ui.common.extention.dpToPx
import com.example.fu.ui.common.extention.getOrAddChildView
import com.example.fu.ui.common.extention.makeChildViewsGone

class PageIndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var viewPager: ViewPager2? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private val argbEvaluator = ArgbEvaluator()

    @ColorInt
    private val dotColorNormal: Int

    @ColorInt
    private val dotColorActivated: Int

    init {
        showDividers = SHOW_DIVIDER_MIDDLE
        dividerDrawable = resources.getDrawable(R.drawable.divider_onboarding_page_indicator, null)
        orientation = HORIZONTAL

        with(
            context
                .theme
                .obtainStyledAttributes(
                    attrs,
                    R.styleable.OnboardingPageIndicatorView,
                    defStyleAttr,
                    0
                )
        ) {
            try {
                dotColorNormal =
                    getColorStateList(R.styleable.OnboardingPageIndicatorView_dotColorNormal)
                        ?.defaultColor
                        ?: resources.getColorStateList(R.color.realWhite, null).defaultColor

                dotColorActivated =
                    getColorStateList(R.styleable.OnboardingPageIndicatorView_dotColorActivated)
                        ?.defaultColor
                        ?: resources.getColorStateList(R.color.violet, null).defaultColor
            } finally {
                recycle()
            }
        }

        if (isInEditMode) {
            populateDotsFromViewPager(5)
        }
    }

    fun bind(viewPager: ViewPager2) {
        unbind() // Previous viewPager.
        val adapter = viewPager.adapter
            ?: throw IllegalStateException("OnboardingPageIndicatorView bound before ViewPager2 has an adapter")
        this.viewPager = viewPager
        this.viewPager!!.isSaveEnabled = false
        this.adapter = adapter
        populateDotsFromViewPager()
        viewPager.registerOnPageChangeCallback(animateDotSwitching)
        adapter.registerAdapterDataObserver(pageCountObserver)
    }

    private fun unbind() {
        viewPager?.unregisterOnPageChangeCallback(animateDotSwitching)
        adapter?.unregisterAdapterDataObserver(pageCountObserver)
        viewPager = null
    }

    private fun populateDotsFromViewPager(forceDotCount: Int? = null) {
        val dotCount = forceDotCount ?: (viewPager?.adapter?.itemCount ?: 0)
        for (i in 0 until dotCount) {
            getOrAddChildView(index = i, newInstance = { View(context) }).resetToDefaultDotState()
        }
        makeChildViewsGone(dotCount..childCount)
    }

    private val animateDotSwitching = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            doOnLayout {
                getChildAt(position)?.applyActivatedState(
                    widthFraction = (1 - positionOffset),
                    colorFraction = positionOffset,
                    fromColor = dotColorActivated,
                    toColor = dotColorNormal
                )
                getChildAt(position + 1)?.applyActivatedState(
                    widthFraction = positionOffset,
                    colorFraction = positionOffset,
                    fromColor = dotColorNormal,
                    toColor = dotColorActivated
                )
            }
        }
    }

    private val pageCountObserver = object : RecyclerView.AdapterDataObserver() {

        override fun onChanged() {
            populateDotsFromViewPager()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            populateDotsFromViewPager()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            populateDotsFromViewPager()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            populateDotsFromViewPager()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            populateDotsFromViewPager()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            populateDotsFromViewPager()
        }
    }

    private fun View.resetToDefaultDotState() {
        isVisible = true
        background =
            resources.getDrawable(R.drawable.bg_onboarding_page_indicator_normal, null)
        backgroundTintList =
            ColorStateList.valueOf(dotColorNormal)
        updateLayoutParams<ViewGroup.LayoutParams> {
            width = dpToPx(NORMAL_DOT_WIDTH).toInt()
            height = dpToPx(NORMAL_DOT_HEIGHT).toInt()
        }
    }

    private fun View.applyActivatedState(
        widthFraction: Float,
        colorFraction: Float,
        @ColorInt fromColor: Int,
        @ColorInt toColor: Int
    ) {
        backgroundTintList =
            ColorStateList.valueOf(
                argbEvaluator.evaluate(
                    colorFraction,
                    fromColor,
                    toColor
                ) as Int
            )
        updateLayoutParams<ViewGroup.LayoutParams> {
            width = dpToPx((NORMAL_DOT_WIDTH + ACTIVATED_DOT_WIDTH_DIFF * widthFraction)).toInt()
        }
    }

    companion object {

        @Dimension(unit = Dimension.DP)
        const val ACTIVATED_DOT_WIDTH_DIFF = 12F

        @Dimension(unit = Dimension.DP)
        const val NORMAL_DOT_WIDTH = 12F

        @Dimension(unit = Dimension.DP)
        const val NORMAL_DOT_HEIGHT = 6F
    }
}