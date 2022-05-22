package com.connectshield.tictactoetdd

/**
 * @author Asatryan on 21.05.2022
 */
interface ResultCommunication : Communication<String> {
    class Base : Communication.Base<String>(), ResultCommunication
}