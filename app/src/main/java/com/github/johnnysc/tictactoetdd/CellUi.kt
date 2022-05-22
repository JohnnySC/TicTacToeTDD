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

    abstract fun show(viewsList: Map<CellID, ImageButton>)

    data class X(private val id: CellID) : CellX(id)

    data class O(private val id: CellID) : CellO(id)

    data class Reset(private val dataMap: Map<CellID, Int>) : CellReset(dataMap)

    object Empty : EmptyCell()
}