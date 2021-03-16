package com.mansi.stackoverflowlisttask.ui.items

import android.content.res.AssetManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mansi.stackoverflowlisttask.R
import com.mansi.stackoverflowlisttask.data.entities.Items
import com.mansi.stackoverflowlisttask.data.entities.ItemsList
import com.mansi.stackoverflowlisttask.databinding.ItemsFragmentBinding
import com.mansi.stackoverflowlisttask.di.AppModule
import com.mansi.stackoverflowlisttask.utils.Resource
import com.mansi.stackoverflowlisttask.utils.autoCleared
import com.squareup.moshi.JsonAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ItemFragment : Fragment() ,ItemsAdapter.ItemsItemListener {

    private var binding: ItemsFragmentBinding by autoCleared()
    private val viewModel: ItemsViewModel by activityViewModels()
    private lateinit var adapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        //setupObservers()  -- API is not calling
        setUpLocalApiResponse()

        //** Set the colors of the Pull To Refresh View
        binding.swipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(binding.itemsRv.context, R.color.colorPrimary))
        binding.swipeRefreshLayout.setColorSchemeColors(Color.WHITE)

        binding.swipeRefreshLayout.setOnRefreshListener {
            setupObservers()
            binding.swipeRefreshLayout.isRefreshing = false
        }

       binding.tryAgainText.setOnClickListener {
           // If API is running ---
            setupRecyclerView()
            setupObservers()
        }
    }



    private fun setupRecyclerView() {
        binding.itemsRv.visibility = View.GONE
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.shimmerFrameLayout.startShimmerAnimation()


        adapter = ItemsAdapter(this)
        binding.itemsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.itemsRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.itemsCall.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        binding.itemsRv.adapter = adapter
                        adapter.setItems(ArrayList(it.data))
                    }

                    binding.itemsRv.visibility = View.VISIBLE
                    binding.shimmerFrameLayout.stopShimmerAnimation()

                    binding.shimmerFrameLayout.visibility = View.GONE
                    binding.gifImage.visibility = View.GONE
                    binding.somethingWentWrongText.visibility = View.GONE
                    binding.tryAgainText.visibility = View.GONE

                }
                Resource.Status.LOADING ->{
                    binding.shimmerFrameLayout.startShimmerAnimation()
                    binding.shimmerFrameLayout.visibility = View.VISIBLE

                    binding.gifImage.visibility = View.GONE
                    binding.somethingWentWrongText.visibility = View.GONE
                    binding.tryAgainText.visibility = View.GONE
                    binding.itemsRv.visibility = View.GONE
                }

                Resource.Status.ERROR ->{
                    binding.gifImage.visibility = View.VISIBLE
                    binding.somethingWentWrongText.visibility = View.VISIBLE
                    binding.tryAgainText.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.itemsRv.visibility = View.GONE
                    Glide.with(this).load(R.drawable.caveman).into(binding.gifImage)
                    binding.shimmerFrameLayout.visibility = View.GONE
                }

            }
        })
    }

    private fun setUpLocalApiResponse() {
        val jsonResponse = context?.assets?.readFile("response.json")
        val jsonAdapter: JsonAdapter<ItemsList> = AppModule.moshi.adapter(ItemsList::class.java)
        val itemsList = jsonAdapter.fromJson(jsonResponse)
        adapter.setItems(itemsList?.items as MutableList<Items>)
        binding.itemsRv.visibility = View.VISIBLE
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.shimmerFrameLayout.stopShimmerAnimation()
    }

    fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }

    override fun onClickedItem(ItemId: String) {
        findNavController().navigate(
            R.id.action_itemsFragment_to_itemDetailFragment,
            bundleOf("stack_link" to ItemId)
        )
    }

}
