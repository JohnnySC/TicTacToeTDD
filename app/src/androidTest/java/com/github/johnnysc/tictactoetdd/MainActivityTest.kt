package com.github.johnnysc.tictactoetdd

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Asatryan on 28.05.2022
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest : BaseTest() {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_player_one_win(): Unit = MainPage().run {
        //region initial check
        view(textId).checkText(player1StepText)
        view(ulButtonId).checkNoDrawable()
        view(umButtonId).checkNoDrawable()
        view(urButtonId).checkNoDrawable()
        view(mlButtonId).checkNoDrawable()
        view(mrButtonId).checkNoDrawable()
        view(mmButtonId).checkNoDrawable()
        view(dlButtonId).checkNoDrawable()
        view(dmButtonId).checkNoDrawable()
        view(drButtonId).checkNoDrawable()
        //endregion

        view(ulButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(umButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(mmButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(mrButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(drButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player1Won)

        view(dlButtonId).tap().checkNoDrawable()
        view(textId).checkText(player1Won)
    }

    @Test
    fun test_game_over() : Unit = MainPage().run {
        //region initial check
        view(textId).checkText(player1StepText)
        view(ulButtonId).checkNoDrawable()
        view(umButtonId).checkNoDrawable()
        view(urButtonId).checkNoDrawable()
        view(mlButtonId).checkNoDrawable()
        view(mrButtonId).checkNoDrawable()
        view(mmButtonId).checkNoDrawable()
        view(dlButtonId).checkNoDrawable()
        view(dmButtonId).checkNoDrawable()
        view(drButtonId).checkNoDrawable()
        //endregion

        view(mlButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(dlButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(dmButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(mmButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(mrButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(drButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(urButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(umButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(ulButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(gameOverText)

        view(umButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(gameOverText)
    }

    @Test
    fun test_new_game_after_1_win() : Unit = MainPage().run {
        //region first player won
        view(textId).checkText(player1StepText)
        view(ulButtonId).checkNoDrawable()
        view(umButtonId).checkNoDrawable()
        view(urButtonId).checkNoDrawable()
        view(mlButtonId).checkNoDrawable()
        view(mrButtonId).checkNoDrawable()
        view(mmButtonId).checkNoDrawable()
        view(dlButtonId).checkNoDrawable()
        view(dmButtonId).checkNoDrawable()
        view(drButtonId).checkNoDrawable()

        view(ulButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(umButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(mmButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(mrButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(drButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player1Won)
        //endregion

        view(newGameButtonId).tap()

        view(textId).checkText(player1StepText)
        view(ulButtonId).checkNoDrawable()
        view(umButtonId).checkNoDrawable()
        view(urButtonId).checkNoDrawable()
        view(mlButtonId).checkNoDrawable()
        view(mrButtonId).checkNoDrawable()
        view(mmButtonId).checkNoDrawable()
        view(dlButtonId).checkNoDrawable()
        view(dmButtonId).checkNoDrawable()
        view(drButtonId).checkNoDrawable()
    }

    /**
     * 1. 3 steps made
     * 2. New game
     * 3. Player 2 wins
     * 4. New game
     */
    @Test
    fun test_new_game_after_3_steps_and_replay_new_game() : Unit = MainPage().run {
        //region 1. 3 steps made
        view(textId).checkText(player1StepText)
        view(ulButtonId).checkNoDrawable()
        view(umButtonId).checkNoDrawable()
        view(urButtonId).checkNoDrawable()
        view(mlButtonId).checkNoDrawable()
        view(mrButtonId).checkNoDrawable()
        view(mmButtonId).checkNoDrawable()
        view(dlButtonId).checkNoDrawable()
        view(dmButtonId).checkNoDrawable()
        view(drButtonId).checkNoDrawable()

        view(ulButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(umButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(mmButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        //endregion

        //2. New game
        view(newGameButtonId).tap()

        view(textId).checkText(player1StepText)
        view(ulButtonId).checkNoDrawable()
        view(umButtonId).checkNoDrawable()
        view(urButtonId).checkNoDrawable()
        view(mlButtonId).checkNoDrawable()
        view(mrButtonId).checkNoDrawable()
        view(mmButtonId).checkNoDrawable()
        view(dlButtonId).checkNoDrawable()
        view(dmButtonId).checkNoDrawable()
        view(drButtonId).checkNoDrawable()

        //3. PLayer 2 wins
        view(ulButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(umButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(mlButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(mmButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player1StepText)

        view(drButtonId).tap().checkDrawable(xResourceId)
        view(textId).checkText(player2StepText)

        view(dmButtonId).tap().checkDrawable(oResourceId)
        view(textId).checkText(player2Won)

        //4.New game
        view(newGameButtonId).tap()

        view(textId).checkText(player1StepText)
        view(ulButtonId).checkNoDrawable()
        view(umButtonId).checkNoDrawable()
        view(urButtonId).checkNoDrawable()
        view(mlButtonId).checkNoDrawable()
        view(mrButtonId).checkNoDrawable()
        view(mmButtonId).checkNoDrawable()
        view(dlButtonId).checkNoDrawable()
        view(dmButtonId).checkNoDrawable()
        view(drButtonId).checkNoDrawable()
    }
}