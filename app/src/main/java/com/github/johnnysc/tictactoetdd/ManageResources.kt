package com.github.johnnysc.tictactoetdd

import android.content.Context
import androidx.annotation.StringRes

/**
 * @author Asatryan on 22.05.2022
 */
interface ManageResources {

    fun string(@StringRes id: Int): String

    class Base(private val context: Context) : ManageResources {
        override fun string(@StringRes id: Int) = context.getString(id)
    }
}