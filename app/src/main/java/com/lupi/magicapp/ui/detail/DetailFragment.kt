package com.lupi.magicapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.lupi.magicapp.R
import com.lupi.magicapp.databinding.FragmentDetailBinding
import com.lupi.magicapp.utils.ApiResponse
import com.lupi.magicapp.utils.Constants
import com.lupi.magicapp.utils.ErrorType
import com.lupi.magicapp.utils.viewBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val detailFragmentBinding : FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)
    private val detailViewModel : DetailViewModel by viewModels()

    private var cardID: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            DetailFragmentArgs.fromBundle(bundle).let { args ->
                if(args.cardID != 0){
                    cardID = args.cardID
                }
            }
        }

        initViewModel()

    }

    private fun initViewModel(){

        detailViewModel.getMutableCard().observe(viewLifecycleOwner){ response ->

            when(response){
                is ApiResponse.Loading ->{
                    updateUILoading()
                }
                is ApiResponse.Success ->{

                    updateUISuccess()

                    Picasso
                        .get()
                        .load(response.data!!.cardImages[0].imageUrl)
                        .into(detailFragmentBinding.imgCardDetalle)

                    detailFragmentBinding.txtNombreDetalle.text = response.data.name
                    detailFragmentBinding.txtTipoDetalle.text = response.data.type

                    updateUITypeCard(response.data.type)

                    detailFragmentBinding.txtAtaqueDetalle.text = response.data.atk.toString()
                    detailFragmentBinding.txtDefensaDetalle.text = response.data.def.toString()
                    detailFragmentBinding.txtNivelDetalle.text = resources.getString(R.string.txt_nive_detalle_format, response.data.level.toString())

                    detailFragmentBinding.txtDescripcionDetalle.text = response.data.desc

                }
                is ApiResponse.Error ->{
                    updateUIError()

                    when(response.errorType){
                        ErrorType.NO_NETWORK_CONNECTION_ERROR ->{
                            detailFragmentBinding.txtErrorDetalle.text = resources.getString(R.string.txt_no_network_connection_error)
                        }
                        ErrorType.GENERIC_ERROR -> {
                            detailFragmentBinding.txtErrorDetalle.text = resources.getString(R.string.txt_generic_error)
                        }
                        else -> {
                            detailFragmentBinding.txtErrorDetalle.text = resources.getString(R.string.txt_generic_error)
                        }
                    }

                }
            }

        }

        detailViewModel.getCard(cardID)

    }

    private fun updateUILoading(){
        detailFragmentBinding.pbBarDetalle.visibility = View.VISIBLE

        detailFragmentBinding.imgCardDetalle.visibility = View.GONE

        detailFragmentBinding.txtTituloDetalleNombre.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleTipo.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleNivel.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleAtaque.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleDefensa.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleDescripcion.visibility = View.GONE

        detailFragmentBinding.txtNombreDetalle.visibility = View.GONE
        detailFragmentBinding.txtTipoDetalle.visibility = View.GONE
        detailFragmentBinding.txtNivelDetalle.visibility = View.GONE
        detailFragmentBinding.txtAtaqueDetalle.visibility = View.GONE
        detailFragmentBinding.txtDefensaDetalle.visibility = View.GONE
        detailFragmentBinding.txtDescripcionDetalle.visibility = View.GONE

        detailFragmentBinding.txtErrorDetalle.visibility = View.GONE
    }

    private fun updateUISuccess() {
        detailFragmentBinding.pbBarDetalle.visibility = View.GONE

        detailFragmentBinding.imgCardDetalle.visibility = View.VISIBLE

        detailFragmentBinding.txtTituloDetalleNombre.visibility = View.VISIBLE
        detailFragmentBinding.txtTituloDetalleTipo.visibility = View.VISIBLE
        detailFragmentBinding.txtTituloDetalleNivel.visibility = View.VISIBLE
        detailFragmentBinding.txtTituloDetalleAtaque.visibility = View.VISIBLE
        detailFragmentBinding.txtTituloDetalleDefensa.visibility = View.VISIBLE
        detailFragmentBinding.txtTituloDetalleDescripcion.visibility = View.VISIBLE

        detailFragmentBinding.txtNombreDetalle.visibility = View.VISIBLE
        detailFragmentBinding.txtTipoDetalle.visibility = View.VISIBLE
        detailFragmentBinding.txtNivelDetalle.visibility = View.VISIBLE
        detailFragmentBinding.txtAtaqueDetalle.visibility = View.VISIBLE
        detailFragmentBinding.txtDefensaDetalle.visibility = View.VISIBLE
        detailFragmentBinding.txtDescripcionDetalle.visibility = View.VISIBLE

        detailFragmentBinding.txtErrorDetalle.visibility = View.GONE
    }

    private fun updateUITypeCard(type: String) {
        if((type == Constants.CARD_TYPE_SPELL) or (type == Constants.CARD_TYPE_TRAP)){

            detailFragmentBinding.txtAtaqueDetalle.visibility = View.GONE
            detailFragmentBinding.txtDefensaDetalle.visibility = View.GONE
            detailFragmentBinding.txtNivelDetalle.visibility = View.GONE

            detailFragmentBinding.txtTituloDetalleAtaque.visibility = View.GONE
            detailFragmentBinding.txtTituloDetalleDefensa.visibility = View.GONE
            detailFragmentBinding.txtTituloDetalleNivel.visibility = View.GONE

        }else{

            detailFragmentBinding.txtAtaqueDetalle.visibility = View.VISIBLE
            detailFragmentBinding.txtDefensaDetalle.visibility = View.VISIBLE
            detailFragmentBinding.txtNivelDetalle.visibility = View.VISIBLE

            detailFragmentBinding.txtTituloDetalleAtaque.visibility = View.VISIBLE
            detailFragmentBinding.txtTituloDetalleDefensa.visibility = View.VISIBLE
            detailFragmentBinding.txtTituloDetalleNivel.visibility = View.VISIBLE

        }
    }

    private fun updateUIError() {
        detailFragmentBinding.pbBarDetalle.visibility = View.GONE

        detailFragmentBinding.imgCardDetalle.visibility = View.GONE

        detailFragmentBinding.txtTituloDetalleNombre.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleTipo.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleNivel.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleAtaque.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleDefensa.visibility = View.GONE
        detailFragmentBinding.txtTituloDetalleDescripcion.visibility = View.GONE

        detailFragmentBinding.txtNombreDetalle.visibility = View.GONE
        detailFragmentBinding.txtTipoDetalle.visibility = View.GONE
        detailFragmentBinding.txtNivelDetalle.visibility = View.GONE
        detailFragmentBinding.txtAtaqueDetalle.visibility = View.GONE
        detailFragmentBinding.txtDefensaDetalle.visibility = View.GONE
        detailFragmentBinding.txtDescripcionDetalle.visibility = View.GONE

        detailFragmentBinding.txtErrorDetalle.visibility = View.VISIBLE
    }
}