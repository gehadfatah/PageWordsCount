package com.goda.pageword.ui

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.goda.pageword.R
import com.goda.pageword.firstChildOf
import org.hamcrest.Matchers.*

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PageWordsCountActivityTest {

    @Test
    fun mainActivityTest() {
        // Start up Tasks screen
        val activityScenario = ActivityScenario.launch(PageWordsCountActivity::class.java)

        val yourRecycler = onView(
            allOf(
                firstChildOf(withId(R.id.container))
              /*  withId(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout"))

                )*/,
                instanceOf(RecyclerView::class.java)
            )
        )
        yourRecycler.check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assertTrue(
                view is RecyclerView &&
                        view.adapter != null && view.adapter?.itemCount ?: -1 > 0
            )

        }
    }
}