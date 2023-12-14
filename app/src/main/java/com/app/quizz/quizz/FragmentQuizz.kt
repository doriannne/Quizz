package com.app.quizz.quizz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.quizz.R
import com.app.quizz.databinding.FragmentQuizzBinding
import com.app.quizz.results.FragmentResult

class FragmentQuizz : Fragment() {
    private var currentPosition = -1
    private val binding by lazy { FragmentQuizzBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }
    private var list : List<QuizzData>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getList().observe(viewLifecycleOwner) { listObserved ->
            list = listObserved
            binding.progressBar.visibility = View.GONE
            binding.mainElements.visibility = View.VISIBLE
            nextQuestion()
        }
    }

    private fun nextQuestion() {
        if (currentPosition >= 4) {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            val nextFragment = FragmentResult()
            nextFragment.arguments = bundleOf("datalist" to ArrayList(list))
            fragmentTransaction.replace(R.id.fragmentContainerView, nextFragment)
            fragmentTransaction.commit()
        } else {
            currentPosition += 1
            binding.firstAns.text = list?.get(currentPosition)?.value1
            binding.secondAns.text = list?.get(currentPosition)?.value2
            binding.thirdAns.text = list?.get(currentPosition)?.value3
            binding.fourtAns.text = list?.get(currentPosition)?.value4
            binding.question.text = list?.get(currentPosition)?.question

            binding.firstAns.setOnClickListener {
                list?.get(currentPosition)?.userAnswear = 1
                nextQuestion()
            }
            binding.secondAns.setOnClickListener {
                list?.get(currentPosition)?.userAnswear = 2
                nextQuestion()
            }
            binding.thirdAns.setOnClickListener {
                list?.get(currentPosition)?.userAnswear = 3
                nextQuestion()
            }
            binding.fourtAns.setOnClickListener {
                list?.get(currentPosition)?.userAnswear = 4
                nextQuestion()
            }
        }
    }
}