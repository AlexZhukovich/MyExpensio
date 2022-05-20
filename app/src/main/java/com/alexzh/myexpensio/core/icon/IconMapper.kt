package com.alexzh.myexpensio.core.icon

interface IconMapper {

    fun getIcon(name: String, default: Int): Int
}