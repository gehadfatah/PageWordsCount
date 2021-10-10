package com.goda.pageword.presentation.common

import com.goda.data.local.models.Word


object WordListUtil {

    fun reSortList(list: MutableList<Word>, isAscending: Boolean) =
        list.apply {
            if (isAscending)
                sortBy { item -> item.repeatedNo }
            else
                sortByDescending { item -> item.repeatedNo }
        }

}