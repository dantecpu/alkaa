package com.escodro.alkaa.ui

import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import com.escodro.alkaa.R
import com.escodro.alkaa.framework.AcceptanceTest
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Test class to validate the Category screen and flow.
 */
class CategoryFragmentTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Before
    fun navigateToCategoryScreen() {
        openActionBarOverflowOrOptionsMenu(context)
        events.clickOnViewWithText(R.string.task_menu_category)
        checkThat.toolbarContainsTitle(R.id.toolbar_main_toolbar, R.string.category_list_label)
    }

    @After
    fun cleanTable() {
        daoRepository.getCategoryDao().cleanTable()
    }

    @Test
    fun areAllViewsIsCompletelyDisplayed() {
        checkThat.viewIsCompletelyDisplayed(R.id.button_categorylist_add)
        checkThat.viewIsCompletelyDisplayed(R.id.recyclerview_categorylist_list)
    }

    @Test
    fun isDescriptionSingleLine() {
        addCategory(
            "Lorem ipsum dolor sit amet, te elit possit suavitate duo. Nec sale sonet" +
                    " scriptorem ei, option prompta ut sed. At everti discere oportere sea."
        )
        checkThat.textHasFixedLines(R.id.textview_itemcategory_description, 1)
    }

    @Test
    fun addCategory() {
        addCategory("Work")
    }

    private fun addCategory(categoryName: String) {
        events.clickOnView(R.id.button_categorylist_add)
        events.textOnView(R.id.edittext_categorynew_description, categoryName)
        events.clickOnView(R.id.button_categorynew_add)
        checkThat.recyclerViewContainsItem(R.id.recyclerview_categorylist_list, categoryName)
    }
}
