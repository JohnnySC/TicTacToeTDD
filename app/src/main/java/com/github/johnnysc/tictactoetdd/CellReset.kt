package com.github.johnnysc.tictactoetdd

import android.widget.ImageButton

/**
 * @author Asatryan on 22.05.2022
 */
abstract class CellReset(private val dataMap: Map<CellID, Int>) : CellUi() {

    private val xResource = CellImageResource.X()
    private val oResource = CellImageResource.O()
    private val emptyResource = CellImageResource.Empty()

    override fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    ) {
        communicationResult.map(message)
        updateCommunication.map(this)
    }

    override fun show(viewsList: Map<CellID, ImageButton>) {
        viewsList.forEach {
            val resource = when (dataMap[it.key]) {
                0 -> emptyResource
                1 -> xResource
                else -> oResource
            }
            resource.apply(it.value)
        }
    }
}