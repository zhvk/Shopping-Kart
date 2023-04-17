package com.zhvk.shoppingkart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.zhvk.shoppingkart.data.DataSource
import com.zhvk.shoppingkart.databinding.FragmentBrowseBinding
import com.zhvk.shoppingkart.model.BrowseProductAdapter
import com.zhvk.shoppingkart.model.CartViewModel

class BrowseFragment : Fragment() {

    private val sharedViewModel: CartViewModel by activityViewModels()

    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_browse, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@BrowseFragment
        }
        binding.productsHeaderNumber.text = DataSource.products.size.toString()
        binding.recyclerView.adapter = BrowseProductAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        // Specify fixed size to improve performance
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}