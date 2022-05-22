package com.github.johnnysc.tictactoetdd

/**
 * @author Asatryan on 22.05.2022
 */
interface MainRepository {

    fun isGameOver(): Boolean
    fun checkGameOver(): Boolean
    fun reset()
    fun init(): ResultUi
    fun cellEmpty(cellId: CellID): Boolean
    fun updateCell(cellId: CellID)
    fun cell(cellId: CellID): CellUi
    fun updateCurrentPlayer()
    fun updateMessage(): String
    fun winCase(first: CellID, second: CellID, third: CellID): Boolean
    fun firstPlayerWon(cellId: CellID): Boolean
    fun gameOverMessage(): String
    fun playerWinMessage(firstWon: Boolean): String

    class Base(private val manageResources: ManageResources) : MainRepository {

        private var currentPlayerIsFirst = true
        private val dataMap = mutableMapOf<CellID, Int>()
        private var gameOver = false
        private var lastMessage = manageResources.string(R.string.player_1_go)

        init {
            CellID.values().forEach {
                dataMap[it] = 0
            }
        }

        override fun isGameOver(): Boolean = gameOver

        override fun checkGameOver(): Boolean {
            var count = 0
            dataMap.forEach {
                if (it.value == 0) count++
            }
            gameOver = count == 0
            return isGameOver()
        }

        override fun init() = ResultUi(lastMessage, CellUi.Reset(dataMap))

        override fun cellEmpty(cellId: CellID) = dataMap[cellId] == 0

        override fun updateCell(cellId: CellID) {
            dataMap[cellId] = if (currentPlayerIsFirst) 1 else -1
        }

        override fun cell(cellId: CellID) =
            if (currentPlayerIsFirst) CellUi.X(cellId) else CellUi.O(cellId)

        override fun updateCurrentPlayer() {
            currentPlayerIsFirst = !currentPlayerIsFirst
        }

        override fun updateMessage(): String {
            lastMessage = manageResources.string(
                if (currentPlayerIsFirst)
                    R.string.player_2_go
                else
                    R.string.player_1_go
            )
            return lastMessage
        }

        override fun winCase(first: CellID, second: CellID, third: CellID) =
            dataMap[third] != 0 &&
                    dataMap[first] == dataMap[second] &&
                    dataMap[second] == dataMap[third]

        override fun firstPlayerWon(cellId: CellID) = dataMap[cellId] == 1

        override fun gameOverMessage(): String {
            lastMessage = manageResources.string(R.string.game_over)
            return lastMessage
        }

        override fun playerWinMessage(firstWon: Boolean): String {
            lastMessage = manageResources.string(
                if (firstWon) R.string.player_1_won else R.string.player_2_won
            )
            gameOver = true
            return lastMessage
        }

        override fun reset() {
            gameOver = false
            lastMessage = manageResources.string(R.string.player_1_go)
            currentPlayerIsFirst = true
            CellID.values().forEach {
                dataMap[it] = 0
            }
        }
    }
}