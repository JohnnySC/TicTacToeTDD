package com.github.johnnysc.tictactoetdd

import android.widget.ImageButton

/**
 * @author Asatryan on 22.05.2022
 */
abstract class CellO(private val id: CellID) : CellUi() {
    private val oImageResource = CellImageResource.O()

    override fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    ) {
        communicationResult.map(message)
        updateCommunication.map(this)
    }

    override fun show(viewsList: Map<CellID, ImageButton>) {
        viewsList[id]?.let { oImageResource.apply(it) }
    }
}