package com.example.kenavigationgraph

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.kenavigationgraph.databinding.FragmentBoxBinding
import kotlin.random.Random

// 1.Вместо использования onCreateView для связывания с макетным файлом xml используется
// конструктор, куда передаем xml, т.е. не пустые скобки - (R.layout.fragment_secret)
// 2. Также желательно вернуться в макетный файл и там тоже проставить обратную ссылку на фрагмент

class BoxFragment: Fragment (R.layout.fragment_box) {

    companion object {
        // для получения данных из предыдущего фрагмента
        const val ARG_COLOR = "color"
        // для возвращения данных в предыдущий фрагмент
        const val REQUEST_CODE = "RANDOM_NUMBER_REQUEST_CODE"
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"
    }

    private lateinit var binding: FragmentBoxBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

        // получение данных из bundle
        val color = requireArguments().getInt(ARG_COLOR)
        binding.root.setBackgroundColor(color)

        // Кнопка 1. Просто назад
        binding.goBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        // Кнопка 2. На секрет фрагмент
        binding.openSecretBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_boxFragment_to_secretFragment

//                navOptions {
//                    anim {
//                        enter = R.anim.enter
//                        exit = R.anim.exit
//                        popEnter = R.anim.pop_enter
//                        popExit = R.anim.pop_exit
//                    }
//                }
            )
        }

        // Кнопка 3. Назад с передачей данных
        binding.generateNumberBtn.setOnClickListener {
            val number = Random.nextInt(100)
            // передача данных "назад" здесь идет более удобным, чем встроенный способ:
            parentFragmentManager.setFragmentResult(REQUEST_CODE, bundleOf(EXTRA_RANDOM_NUMBER to number))
            findNavController().popBackStack()


            // Ниже еще один, встроенный в Nav вариант передачи данных обратно по стеку. Не очень
            // удобный - не особо читаемый код и при повороте экрана данные приходят второй раз:
            // findNavController().previousBackStackEntry?.savedStateHandle?.set("") ...
            // а данную строку нужно вставить в предыдущем фрагменте, чтобы отловить данные:
            // findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("")?.observe(viewLifecycleOwner) {}

        }

    }
}