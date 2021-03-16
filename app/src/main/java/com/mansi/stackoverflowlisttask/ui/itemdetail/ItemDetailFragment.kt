package com.mansi.stackoverflowlisttask.ui.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mansi.stackoverflowlisttask.databinding.ItemDetailFragmentBinding
import com.mansi.stackoverflowlisttask.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    private var binding: ItemDetailFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("stack_link")?.let { bindCharacter(it) }

    }

    private fun bindCharacter(links: String) {
        binding.stackpageWebview.loadUrl(links)
    }
}
