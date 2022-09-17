package com.example.kenavigationgraph

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kenavigationgraph.databinding.FragmentRootBinding

// 1.Вместо использования onCreateView для связывания с макетным файлом xml используется
// конструктор, куда передаем xml, т.е. не пустые скобки - (R.layout.fragment_secret)
// 2. Также желательно вернуться в макетный файл и там тоже проставить обратную ссылку на фрагмент

class RootFragment : Fragment(R.layout.fragment_root) {
    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(requireView())   //иниц-я binding, здесь можно просто (view)

        // Кнопка 1. Зеленый
        binding.openYellowBoxBtn.setOnClickListener {
            openBox(Color.rgb(255, 255, 200))
        }

        // Кнопка 2. Желтый
        binding.openGreenBoxBtn.setOnClickListener {
            openBox(Color.rgb(200, 255, 200))
        }

        // Здесь отлавливаем передаваемые данные при возврате с последующего экрана
        parentFragmentManager.setFragmentResultListener(BoxFragment.REQUEST_CODE, viewLifecycleOwner) { _, data ->
            val number = data.getInt(BoxFragment.EXTRA_RANDOM_NUMBER)
            Toast.makeText(requireContext(), "Random number: $number", Toast.LENGTH_SHORT).show()
        }
    }

    // метод для перехода тут общий, т.к. различие по кнопкам только в цвете
    private fun openBox (color: Int) {
        findNavController().navigate(
            R.id.action_rootFragment_to_boxFragment,            // переход (action)
            bundleOf(BoxFragment.ARG_COLOR to color,     // передача данных
            )
        )
        // Classic way for compare:
        // activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, BlankFragment())?.addToBackStack(null)?.commit()
    }

}