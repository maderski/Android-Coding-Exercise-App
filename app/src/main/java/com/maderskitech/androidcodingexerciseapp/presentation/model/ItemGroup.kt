package com.maderskitech.androidcodingexerciseapp.presentation.model

import com.maderskitech.kmpcodingexercisenetwork.domain.model.Item

/**
 * Group of Items with the same List ID.
 */
data class ItemGroup(
    val listId: String,
    val items: List<Item>
)
