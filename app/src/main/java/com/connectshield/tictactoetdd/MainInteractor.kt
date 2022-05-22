package com.connectshield.tictactoetdd

/**
 * @author Asatryan on 21.05.2022
 */
interface MainInteractor {

    fun handle(cellId: CellID): ResultUi
    fun reset(): ResultUi
    fun init(): ResultUi

    class Base(private val repository: MainRepository) : MainInteractor {

        override fun handle(cellId: CellID) =
            if (repository.isGameOver())
                ResultUi("", CellUi.Empty)
            else {
                if (repository.cellEmpty(cellId)) {
                    repository.updateCell(cellId)
                    var message = repository.updateMessage()

                    val upRow = repository.winCase(CellID.UL, CellID.UM, CellID.UR)
                    val midRow = repository.winCase(CellID.ML, CellID.MM, CellID.MR)
                    val downRow = repository.winCase(CellID.DL, CellID.DM, CellID.DR)
                    val diagonalLeft = repository.winCase(CellID.UL, CellID.MM, CellID.DR)
                    val diagonalRight = repository.winCase(CellID.UR, CellID.MM, CellID.DL)
                    val leftColumn = repository.winCase(CellID.UL, CellID.ML, CellID.DL)
                    val midColumn = repository.winCase(CellID.UM, CellID.MM, CellID.DM)
                    val rightColumn = repository.winCase(CellID.UR, CellID.MR, CellID.DR)
                    if (
                        upRow || midRow || downRow ||
                        diagonalLeft || diagonalRight ||
                        leftColumn || rightColumn || midColumn
                    ) {
                        val firstWon = when {
                            diagonalLeft || diagonalRight || midRow || midColumn ->
                                repository.firstPlayerWon(CellID.MM)
                            upRow || leftColumn -> repository.firstPlayerWon(CellID.UL)
                            upRow || midColumn -> repository.firstPlayerWon(CellID.UM)
                            upRow || rightColumn -> repository.firstPlayerWon(CellID.UR)
                            midRow || leftColumn -> repository.firstPlayerWon(CellID.ML)
                            midRow || rightColumn -> repository.firstPlayerWon(CellID.MR)
                            downRow || leftColumn -> repository.firstPlayerWon(CellID.DL)
                            downRow || midColumn -> repository.firstPlayerWon(CellID.DM)
                            downRow || rightColumn -> repository.firstPlayerWon(CellID.DR)
                            else -> false
                        }

                        message = repository.playerWinMessage(firstWon)
                    }

                    if (!repository.isGameOver()) {
                        if (repository.checkGameOver()) {
                            message = repository.gameOverMessage()
                        }
                    }

                    val cell = repository.cell(cellId)
                    repository.updateCurrentPlayer()
                    ResultUi(message, cell)
                } else
                    ResultUi("", CellUi.Empty)
            }

        override fun reset(): ResultUi {
            repository.reset()
            return init()
        }

        override fun init() = repository.init()
    }
}