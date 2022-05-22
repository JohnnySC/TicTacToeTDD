package com.github.johnnysc.tictactoetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 21.05.2022
 */
class MainViewModelTest {

    @Test
    fun `test player one win`() {
        val communicationResult = TestCommunication()
        val communicationUpdate = TestUpdateCommunication()
        val interactor = MainInteractor.Base(MainRepository.Base(TestManageResources()))
        val viewModel = MainViewModel(communicationResult, communicationUpdate, interactor)
        viewModel.init()

        assertEquals("Player 1, go!", communicationResult.text)

        viewModel.tap(CellID.UL)
        assertEquals("Player 2, go!", communicationResult.text)
        assertEquals(CellUi.X(CellID.UL), communicationUpdate.value)

        viewModel.tap(CellID.UM)
        assertEquals("Player 1, go!", communicationResult.text)
        assertEquals(CellUi.O(CellID.UM), communicationUpdate.value)

        viewModel.tap(CellID.MM)
        assertEquals("Player 2, go!", communicationResult.text)
        assertEquals(CellUi.X(CellID.MM), communicationUpdate.value)

        viewModel.tap(CellID.MR)
        assertEquals("Player 1, go!", communicationResult.text)
        assertEquals(CellUi.O(CellID.MR), communicationUpdate.value)

        viewModel.tap(CellID.DR)
        assertEquals("Player 1 won!", communicationResult.text)
        assertEquals(CellUi.X(CellID.DR), communicationUpdate.value)
        assertEquals(6, communicationResult.count)
        assertEquals(6, communicationUpdate.count)

        viewModel.tap(CellID.DL)
        assertEquals(6, communicationResult.count)
        assertEquals(6, communicationUpdate.count)
    }

    @Test
    fun `test player one double tap`() {
        val communicationResult = TestCommunication()
        val communicationUpdate = TestUpdateCommunication()
        val interactor = MainInteractor.Base(MainRepository.Base(TestManageResources()))
        val viewModel = MainViewModel(communicationResult, communicationUpdate, interactor)

        viewModel.tap(CellID.UL)
        assertEquals("Player 2, go!", communicationResult.text)
        assertEquals(1, communicationResult.count)
        assertEquals(CellUi.X(CellID.UL), communicationUpdate.value)
        assertEquals(1, communicationUpdate.count)

        viewModel.tap(CellID.UL)
        assertEquals(1, communicationResult.count)
        assertEquals(1, communicationUpdate.count)
    }

    @Test
    fun `test game over`() {
        val communicationResult = TestCommunication()
        val communicationUpdate = TestUpdateCommunication()
        val interactor = MainInteractor.Base(MainRepository.Base(TestManageResources()))
        val viewModel = MainViewModel(communicationResult, communicationUpdate, interactor)
        viewModel.init()

        assertEquals("Player 1, go!", communicationResult.text)

        viewModel.tap(CellID.ML)
        assertEquals("Player 2, go!", communicationResult.text)
        assertEquals(CellUi.X(CellID.ML), communicationUpdate.value)

        viewModel.tap(CellID.UL)
        assertEquals("Player 1, go!", communicationResult.text)
        assertEquals(CellUi.O(CellID.UL), communicationUpdate.value)

        viewModel.tap(CellID.UM)
        assertEquals("Player 2, go!", communicationResult.text)
        assertEquals(CellUi.X(CellID.UM), communicationUpdate.value)

        viewModel.tap(CellID.MM)
        assertEquals("Player 1, go!", communicationResult.text)
        assertEquals(CellUi.O(CellID.MM), communicationUpdate.value)

        viewModel.tap(CellID.MR)
        assertEquals("Player 2, go!", communicationResult.text)
        assertEquals(CellUi.X(CellID.MR), communicationUpdate.value)

        viewModel.tap(CellID.UR)
        assertEquals("Player 1, go!", communicationResult.text)
        assertEquals(CellUi.O(CellID.UR), communicationUpdate.value)

        viewModel.tap(CellID.DR)
        assertEquals("Player 2, go!", communicationResult.text)
        assertEquals(CellUi.X(CellID.DR), communicationUpdate.value)

        viewModel.tap(CellID.DM)
        assertEquals("Player 1, go!", communicationResult.text)
        assertEquals(CellUi.O(CellID.DM), communicationUpdate.value)

        viewModel.tap(CellID.DL)
        assertEquals("GAME OVER", communicationResult.text)
        assertEquals(CellUi.X(CellID.DL), communicationUpdate.value)
    }

    class TestCommunication : ResultCommunication {

        var count = 0
        var text = ""

        override fun map(data: String) {
            text = data
            count++
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<String>) = Unit
    }

    class TestUpdateCommunication : UpdateCommunication {

        var value: CellUi = CellUi.Empty
        var count = 0

        override fun map(data: CellUi) {
            value = data
            count++
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<CellUi>) = Unit
    }

    class TestManageResources : ManageResources {
        override fun string(id: Int) = when (id) {
            R.string.player_2_won -> "Player 2 won!"
            R.string.player_1_won -> "Player 1 won!"
            R.string.player_1_go -> "Player 1, go!"
            R.string.player_2_go -> "Player 2, go!"
            R.string.game_over -> "GAME OVER"
            else -> "unknown"
        }
    }
}