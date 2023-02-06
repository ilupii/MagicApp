package com.lupi.magicapp.ui.master

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lupi.magicapp.R
import com.lupi.magicapp.adapters.CardsAdapter
import com.lupi.magicapp.databinding.FragmentMasterBinding
import com.lupi.magicapp.utils.ApiResponse
import com.lupi.magicapp.utils.ErrorType
import com.lupi.magicapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MasterFragment : Fragment(R.layout.fragment_master) {

    private val masterFragmentViewBinding : FragmentMasterBinding by viewBinding(FragmentMasterBinding::bind)
    private val masterViewModel : MasterViewModel by viewModels()

    private lateinit var cardsAdapter: CardsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        masterViewModel.getAllCards()
    }

    private fun initViewModel(){

        masterViewModel.getMutableCardList().observe(viewLifecycleOwner) { response ->
            when(response){
                is ApiResponse.Loading ->{
                    updateUILoading()
                }
                is ApiResponse.Success ->{

                    updateUISuccess()

                    masterFragmentViewBinding.recyclerView.layoutManager = LinearLayoutManager(context)

                    cardsAdapter = CardsAdapter(response.data!!)

                    cardsAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

                    cardsAdapter.onItemClick = { card ->
                        findNavController().navigate(
                            MasterFragmentDirections.actionMasterFragmentToDetailFragment(
                                card.id
                            )
                        )
                    }

                    masterFragmentViewBinding.recyclerView.adapter = cardsAdapter

                }
                is ApiResponse.Error ->{
                    updateUIError()

                    when(response.errorType){
                        ErrorType.NO_NETWORK_CONNECTION_ERROR ->{
                            masterFragmentViewBinding.txtErrorMaster.text = resources.getString(R.string.txt_no_network_connection_error)
                        }
                        ErrorType.GENERIC_ERROR ->{
                            masterFragmentViewBinding.txtErrorMaster.text = resources.getString(R.string.txt_generic_error)
                        }
                        else -> {
                            masterFragmentViewBinding.txtErrorMaster.text = resources.getString(R.string.txt_generic_error)
                        }
                    }
                }
            }
        }

    }

    private fun updateUILoading() {
        masterFragmentViewBinding.pgBarMaster.visibility = View.VISIBLE
        masterFragmentViewBinding.txtErrorMaster.visibility = View.GONE
        masterFragmentViewBinding.recyclerView.visibility = View.GONE
    }

    private fun updateUISuccess() {
        masterFragmentViewBinding.pgBarMaster.visibility = View.GONE
        masterFragmentViewBinding.txtErrorMaster.visibility = View.GONE
        masterFragmentViewBinding.recyclerView.visibility = View.VISIBLE
    }

    private fun updateUIError() {
        masterFragmentViewBinding.pgBarMaster.visibility = View.GONE
        masterFragmentViewBinding.txtErrorMaster.visibility = View.VISIBLE
        masterFragmentViewBinding.recyclerView.visibility = View.GONE
    }

}