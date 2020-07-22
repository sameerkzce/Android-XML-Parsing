package com.example.sooryenapp.presentation.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sooryenapp.R
import com.example.sooryenapp.databinding.HomeFragmentBinding
import com.example.sooryenapp.dto.EntryDto
import com.example.sooryenapp.presentation.adapters.HomeAdapter
import com.example.sooryenapp.presentation.adapters.OnEntryClickListener
import com.example.sooryenapp.presentation.base.BaseFragment
import com.example.sooryenapp.presentation.navigator.HomeNavigator
import com.example.sooryenapp.presentation.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * Home fragment will init first on start of the app using navigation component
 * */

class HomeFragment : BaseFragment(), HomeNavigator {
    val TAG: String = HomeFragment::class.java.simpleName
    private lateinit var data: ArrayList<EntryDto>
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.navigator = this
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!
        val isSyncDone = sharedPref.getBoolean(getString(R.string.isSyncDone), false)
        viewModel.getTopSongs(isSyncDone)

        setDataChangeListener(viewModel)
    }

    private fun setDataChangeListener(viewModel: HomeViewModel) {
        viewModel.entryList.observe(requireActivity(), Observer {
            this.data.clear()
            this.data.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
        })
    }

    /* init the all related views and listener
    * */
    private fun init() {
        data = ArrayList()
        initRecyclerView()
    }

    /*
   * init the recycler view with layout manager and set the adapter
   *  adapter also handle the on item tap click lister
    */
    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            homeAdapter = HomeAdapter(data, object : OnEntryClickListener {
                override fun onClickEntry(entryDto: EntryDto?) {
                    Log.d(TAG, "on click=" + entryDto?.audioLink)
                    val bundle =
                        bundleOf(EntryDetailFragment.ENTRYOBJ to entryDto)
                    findNavController().navigate(R.id.actionEntryDetailFragment, bundle)
                }
            })
            adapter = homeAdapter
        }
    }

    override fun showError(title: String, message: String) {
        showAlert(title, message)
    }

    override fun updateSync(value: Boolean) {
        with(sharedPref.edit()) {
            putBoolean(getString(R.string.isSyncDone), value)
            commit()
        }
    }
}