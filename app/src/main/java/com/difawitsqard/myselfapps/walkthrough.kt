package com.difawitsqard.myselfapps

// Created : 26/05/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton

class walkthrough : AppCompatActivity() {

    private lateinit var walkthroughItemsAdapter: walkthroughItemsAdapter
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var btnGetStarted: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)
        btnGetStarted = findViewById(R.id.btn_getStarted)  // Initialize the button here
        setWalkthroughItems()
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setWalkthroughItems() {
        val imageArray = resources.getStringArray(R.array.walkthrough_images)
        val titleArray = resources.getStringArray(R.array.walkthrough_titles)
        val descriptionArray = resources.getStringArray(R.array.walkthrough_descriptions)

        val walkthroughItems = mutableListOf<walkthroughItem>()

        for (i in imageArray.indices) {
            walkthroughItems.add(
                walkthroughItem(
                    walkthroughImage = resources.getIdentifier(imageArray[i], "drawable", packageName),
                    title = titleArray[i],
                    description = descriptionArray[i]
                )
            )
        }

        walkthroughItemsAdapter = walkthroughItemsAdapter(walkthroughItems)
        val walkthroughViewPager = findViewById<ViewPager2>(R.id.walkthroughViewPager)
        walkthroughViewPager.adapter = walkthroughItemsAdapter
        walkthroughViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                if (position == walkthroughItemsAdapter.itemCount - 1) {
                    btnGetStarted.text = "Mulai"
                } else {
                    btnGetStarted.text = "Berikutnya"
                }
            }
        })
        (walkthroughViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            goToMainActivity()
        }
        btnGetStarted.setOnClickListener {
            if (walkthroughViewPager.currentItem + 1 < walkthroughItemsAdapter.itemCount) {
                walkthroughViewPager.currentItem += 1
            } else {
                goToMainActivity()
            }
        }
    }

    private fun goToMainActivity() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    private fun setupIndicators() {
        indicatorContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(walkthroughItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_backround
                    )
                )
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_active_backround
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_backround
                    )
                )
            }
        }
    }
}
