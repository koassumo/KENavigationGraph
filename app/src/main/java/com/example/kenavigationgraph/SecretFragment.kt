package com.example.kenavigationgraph

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kenavigationgraph.databinding.FragmentSecretBinding


// 1.Вместо использования onCreateView для связывания с макетным файлом xml используется
// конструктор, куда передаем xml, т.е. не пустые скобки - (R.layout.fragment_secret)
// 2. Также желательно вернуться в макетный файл и там тоже проставить обратную ссылку на фрагмент

class SecretFragment : Fragment(R.layout.fragment_secret) {
        private lateinit var binding: FragmentSecretBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecretBinding.bind(view)

        // Кнопка 1. Назад
        binding.goBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        // Кнопка 2. На начальный экран
        binding.closeBoxBtn.setOnClickListener {

            // Вариант 1. Back - это обычно кнопка "Назад" на телефоне. Выход назад именно туда
            // откуда пришли (может даже в другое приложение)
            findNavController().popBackStack(R.id.rootFragment, false)
            // т.е. здесь не просто назад, а с указание destination. false - не нужно закрывать

            // Вариант 2. Up - обычно стрелочка в toolbar, как бы "на каталог выше". Т.е. выход
            // всегда в предыдущий фрагмент. Up никогда не выходит из приложения, поэтому стрелки
            // нет на главном экране приложения
            // findNavController().navigateUp()
            // Для справки - в mainActivity часто переопределяют:
            //            override fun onBackPressed() {
            //                super.onBackPressed()
            //            }
            //
            //            override fun onSupportNavigateUp(): Boolean {
            //                onBackPressed()
            //                return true
            //            }

        }



    }
}