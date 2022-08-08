package com.example.guessit.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.guessit.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private var binding: FragmentScoreBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScoreBinding.inflate(inflater, container, false)

        binding?.scoreText?.text = arguments?.let { ScoreFragmentArgs.fromBundle(it).score.toString() }
        binding?.playAgainButton?.setOnClickListener { onPlayAgain() }
        return binding?.root
    }

    private fun onPlayAgain() {
        findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
    }

}