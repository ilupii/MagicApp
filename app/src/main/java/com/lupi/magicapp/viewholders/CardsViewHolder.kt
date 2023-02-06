package com.lupi.magicapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lupi.magicapp.R
import com.lupi.magicapp.databinding.CardLayoutBinding
import com.lupi.magicapp.models.Card
import com.lupi.magicapp.utils.Constants
import com.squareup.picasso.Picasso

class CardsViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {

    private val cardViewHolderViewBinding = CardLayoutBinding.bind(view)

    fun render(card : Card){
        cardViewHolderViewBinding.txtTituloCarta.text = card.name

        cardViewHolderViewBinding.txtTipo.text = card.type

        if((card.type == Constants.CARD_TYPE_SPELL) or (card.type == Constants.CARD_TYPE_TRAP)){
            cardViewHolderViewBinding.txtAtaque.visibility = View.INVISIBLE
            cardViewHolderViewBinding.txtDefensa.visibility = View.INVISIBLE
            cardViewHolderViewBinding.txtValorAtaqueCardLayout.visibility = View.INVISIBLE
            cardViewHolderViewBinding.txtValorDefensaCardLayout.visibility = View.INVISIBLE
        }else{
            cardViewHolderViewBinding.txtAtaque.visibility = View.VISIBLE
            cardViewHolderViewBinding.txtDefensa.visibility = View.VISIBLE
            cardViewHolderViewBinding.txtValorAtaqueCardLayout.visibility = View.VISIBLE
            cardViewHolderViewBinding.txtValorDefensaCardLayout.visibility = View.VISIBLE

            cardViewHolderViewBinding.txtValorAtaqueCardLayout.text = view.resources.getString(R.string.txt_valor_ataque_card_layout, card.atk.toString())
            cardViewHolderViewBinding.txtValorDefensaCardLayout.text = view.resources.getString(R.string.txt_valor_defensa_card_layout, card.def.toString())
        }

        Picasso
            .get()
            .load(card.cardImages[0].imageUrl)
            .fit()
            .into(cardViewHolderViewBinding.imgCarta)

    }

}