package com.example.gameingpoh.favorite

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gameingpoh.core.ui.GameAdapter
//import com.example.gameingpoh.core.ui.ViewModelFactory
import com.example.gameingpoh.databinding.FragmentFavoriteBinding
import com.example.gameingpoh.detail.DetailGameActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment() {

//    private lateinit var favoriteViewModel: FavoriteViewModel
private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val tourismAdapter = GameAdapter()
            tourismAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailGameActivity::class.java)
                intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

//            val factory = ViewModelFactory.getInstance(requireActivity())
//            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteGame.observe(viewLifecycleOwner, { dataGame ->
                tourismAdapter.setData(dataGame)
                binding.viewEmpty.root.visibility = if (dataGame.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvGame) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tourismAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}