package com.example.guessit.screens.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guessit.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private var binding: FragmentGameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        //viewModelProvider is called everytime we rotate the phone or go to home screen
        // but ViewModel is called only once
        Log.i("GameFragment", "Called ViewModelProviders")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        //first you pass the fragment using the keyword this
        //second you pass the specific ViewModel class that you want here

        binding?.correctButton?.setOnClickListener {
            viewModel.onCorrect()
        } //call the viewModel versions of the method
        binding?.skipButton?.setOnClickListener {
            viewModel.onSkip()
        }

        //set up the observer relationship, first we need a reference to live data
        //this code is going to get triggered anytime the live data changes
        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding?.scoreText?.text = newScore.toString()
        } )

        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding?.wordText?.text = newWord.toString()
        })

        return binding?.root
    }

    //called when the game is finished
    // any navigation that you do is going to need to be done in the fragment
    //because navigation needs access to a nav controller, and nav controlller is found by passsing in a viewr fragment
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score.value ?: 0)
        //Elvis Operator--if this is actually an integer value, then pass through the integer value otherwise pass through zero
        findNavController().navigate(action)
    }


}