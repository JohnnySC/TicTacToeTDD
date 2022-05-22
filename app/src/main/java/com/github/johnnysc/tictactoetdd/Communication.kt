package com.github.johnnysc.tictactoetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * @author Asatryan on 21.05.2022
 */
interface Communication<T> {

    fun map(data: T)

    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    abstract class Base<T : Any> : Communication<T> {

        private val liveData = MutableLiveData<T>()

        override fun map(data: T) = liveData.setValue(data)

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            liveData.observe(owner, observer)
    }
}