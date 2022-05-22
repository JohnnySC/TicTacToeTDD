package com.github.johnnysc.tictactoetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * @author Asatryan on 21.05.2022
 */
class MainViewModel(
    private val communicationResult: ResultCommunication,
    private val updateCommunication: UpdateCommunication,
    private val interactor: MainInteractor
) {

    fun observe(owner: LifecycleOwner, observer: Observer<String>) =
        communicationResult.observe(owner, observer)

    fun observeResult(owner: LifecycleOwner, observer: Observer<CellUi>) =
        updateCommunication.observe(owner, observer)

    fun tap(cellID: CellID) =
        interactor.handle(cellID).map(communicationResult, updateCommunication)

    fun newGame() =
        interactor.reset().map(communicationResult, updateCommunication)

    fun init() =
        interactor.init().map(communicationResult, updateCommunication)
}