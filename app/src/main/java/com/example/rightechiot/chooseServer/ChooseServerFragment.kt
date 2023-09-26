package com.example.rightechiot.chooseServer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rightechiot.R
import com.example.rightechiot.Screens
import com.example.rightechiot.databinding.FragmentSaveServerBinding
import com.example.rightechiot.router
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class ChooseServerFragment : Fragment(R.layout.fragment_save_server) {

    lateinit var binding: FragmentSaveServerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSaveServerBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = arrayOf(
            getString(R.string.server_app),
            getString(R.string.server_dev),
            getString(R.string.server_sandbox),
            getString(R.string.server_custom)
        )
        (binding.server as? MaterialAutoCompleteTextView)?.setSimpleItems(items)

        binding.saveButton.setOnClickListener {
            router.replaceScreen(Screens.dashboards())
        }
        binding.backText.setOnClickListener { router.exit() }
        binding.back.setOnClickListener { router.exit() }
    }
}