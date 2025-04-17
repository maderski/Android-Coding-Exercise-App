package com.maderskitech.androidcodingexerciseapp.presentation.displayitems.model

import com.maderskitech.kmpcodingexercisenetwork.domain.model.Item

/**
 * A group of Items with the same List ID.
 */
data class ItemGroup(
    val listId: Int,
    val items: List<Item>
)
