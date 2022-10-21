package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private var wordList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    private var _score = 0
    val score: Int
        get() = _score

    private var _currentWordCount = 0
    val currentWordCount: Int
        get() = _currentWordCount

    private lateinit var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord

    private fun getNextWord(){
        currentWord = allWordsList.random()
        val temWord = currentWord.toCharArray()
        temWord.shuffle()

        while (String(temWord).equals(currentWord,false)){
            temWord.shuffle()
        }

        if (wordList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScrambledWord = String(temWord)
            ++_currentWordCount
            wordList.add(currentWord)
        }
    }

    init {
        Log.d("GameFragment","GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment","GameViewModel destroy!")
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore(){
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean{
        if (playerWord.equals(currentWord, true)){
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordList.clear()
        getNextWord()
    }
}