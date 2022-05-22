package com.github.johnnysc.tictactoetdd

import android.widget.ImageButton

/**
 * @author Asatryan on 22.05.2022
 */
abstract class EmptyCell : CellUi() {
    override fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    ) = Unit

    override fun show(viewsList: Map<CellID, ImageButton>) = Unit
}