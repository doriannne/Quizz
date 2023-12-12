package com.app.quizz.quizz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.quizz.databinding.FragmentQuizzBinding

class FragmentQuizz : Fragment() {
    private val binding by lazy { FragmentQuizzBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getList().observe(viewLifecycleOwner) {
            binding.firstAns.text = it?.get(0)?.value1
            println(it)
        }
    }
}