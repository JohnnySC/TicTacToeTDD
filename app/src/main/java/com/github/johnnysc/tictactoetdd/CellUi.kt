package com.github.johnnysc.tictactoetdd

import android.widget.ImageButton

/**
 * @author Asatryan on 21.05.2022
 */
abstract class CellUi {

    abstract fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    )

    abstract fun show(viewsList: MutableMap<CellID, ImageButton>)

    data class X(private val id: CellID) : CellUi() {
        override fun map(
            communicationResult: ResultCommunication,
            updateCommunication: UpdateCommunication,
            message: String
        ) {
            communicationResult.map(message)
            updateCommunication.map(this)
        }

        override fun show(viewsList: MutableMap<CellID, ImageButton>) {
            viewsList[id]?.setImageResource(X_RESOURCE_ID)
        }
    }

    data class O(private val id: CellID) : CellUi() {
        override fun map(
            communicationResult: ResultCommunication,
            updateCommunication: UpdateCommunication,
            message: String
        ) {
            communicationResult.map(message)
            updateCommunication.map(this)
        }

        override fun show(viewsList: MutableMap<CellID, ImageButton>) {
            viewsList[id]?.setImageResource(O_RESOURCE_ID)
        }
    }

    object Empty : CellUi() {
        override fun map(
            communicationResult: ResultCommunication,
            updateCommunication: UpdateCommunication,
            message: String
        ) = Unit

        override fun show(viewsList: MutableMap<CellID, ImageButton>) = Unit
    }

    class Reset(private val dataMap: MutableMap<CellID, Int>) : CellUi() {
        override fun map(
            communicationResult: ResultCommunication,
            updateCommunication: UpdateCommunication,
            message: String
        ) {
            communicationResult.map(message)
            updateCommunication.map(this)
        }

        override fun show(viewsList: MutableMap<CellID, ImageButton>) {
            viewsList.forEach {
                val resourceId = when (dataMap[it.key]) {
                    0 -> 0
                    1 -> X_RESOURCE_ID
                    else -> O_RESOURCE_ID
                }
                it.value.setImageResource(resourceId)
            }
        }
    }

    companion object {
        private const val X_RESOURCE_ID = R.drawable.x
        private const val O_RESOURCE_ID = R.drawable.o
    }
}