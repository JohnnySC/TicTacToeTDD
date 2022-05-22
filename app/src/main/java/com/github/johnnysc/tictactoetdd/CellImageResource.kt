package com.github.johnnysc.tictactoetdd

import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 * @author Asatryan on 22.05.2022
 */
interface CellImageResource {

    fun apply(imageView: ImageView)

    abstract class Base(@DrawableRes private val id: Int) : CellImageResource {
        override fun apply(imageView: ImageView) = imageView.setImageResource(id)
    }

    class X : Base(R.drawable.x)
    class O : Base(R.drawable.o)
    class Empty : Base(0)
}