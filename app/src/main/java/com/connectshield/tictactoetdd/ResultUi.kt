package com.connectshield.tictactoetdd

/**
 * @author Asatryan on 21.05.2022
 */
data class ResultUi(
    private val message: String,
    private val cell: CellUi
) {
    fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication
    ) = cell.map(communicationResult, updateCommunication, message)
}