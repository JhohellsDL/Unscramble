package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private var wordList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    private val _score = 0
    val score: Int
    get() = _score

    private var currentWordCount = 0
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
            ++currentWordCount
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
}