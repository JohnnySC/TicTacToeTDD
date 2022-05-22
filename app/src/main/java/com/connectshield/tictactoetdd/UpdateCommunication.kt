package com.connectshield.tictactoetdd

/**
 * @author Asatryan on 21.05.2022
 */
interface UpdateCommunication : Communication<CellUi> {
    class Base : Communication.Base<CellUi>(), UpdateCommunication
}