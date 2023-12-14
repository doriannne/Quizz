package com.app.quizz.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.quizz.R
import com.app.quizz.databinding.FragmentQuizzBinding
import com.app.quizz.databinding.FragmentResultBinding
import com.app.quizz.quizz.FragmentQuizz
import com.app.quizz.quizz.QuizViewModel
import com.app.quizz.quizz.QuizzData

class FragmentResult : Fragment() {
    private val binding by lazy { FragmentResultBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }
    private var quizzDataList : ArrayList<QuizzData>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizzDataList = arguments?.getSerializable("datalist") as? ArrayList<QuizzData>

        quizzDataList?.forEachIndexed { index, quizzData ->
            val view = when (index){
                0 -> binding.firstCor
                1 -> binding.secondCor
                2 -> binding.thirdCor
                3 -> binding.fourthCor
                4 -> binding.fivesCor
                else -> null
            }
            view?.text = gettrueAnswear(quizzData)
        }
        val score = quizzDataList?.filter { it.trueanswear == it.userAnswear }
        binding.yourRes.text = getString(R.string.result).replace("{score}", score?.size.toString())

        binding.restart.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            val nextFragment = FragmentQuizz()
            fragmentTransaction.replace(R.id.fragmentContainerView, nextFragment)
            fragmentTransaction.commit()
        }
    }

    fun  gettrueAnswear(quizzData : QuizzData) : String{
        val trueanswear = when(quizzData.trueanswear){
            1 -> quizzData.value1
            2 -> quizzData.value2
            3 -> quizzData.value3
            else -> quizzData.value4
        }
        return trueanswear
    }
}