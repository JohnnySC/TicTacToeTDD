package com.github.johnnysc.tictactoetdd

import android.app.Application

/**
 * @author Asatryan on 22.05.2022
 */
class TicTacToeApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        mainViewModel = MainViewModel(
            ResultCommunication.Base(),
            UpdateCommunication.Base(),
            MainInteractor.Base(MainRepository.Base(ManageResources.Base(this)))
        )
    }
}