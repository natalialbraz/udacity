package com.example.guessit.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guessit.databinding.FragmentGameBinding

class GameViewModel:  ViewModel(){

    //The current word
    var word = MutableLiveData<String>()
    //The current score
    var score = MutableLiveData<Int>() //a live data whose value can be changed
    // The list of words = the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init{
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
        score.value = 0
        word.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    //moves to the next word i the list
    private fun nextWord() {
        //select and remove a word in the list
        if (wordList.isEmpty()) {
            //gameFinished()
            //the gameFinished method is still in the fragment, and needs to stay
            //it is pretty tricky to solve because the constraint that the viewModel can't know about the fragment
        } else {
            word.value = wordList.removeAt(0)
        }
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    //Methods for buttons presses
    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }
}