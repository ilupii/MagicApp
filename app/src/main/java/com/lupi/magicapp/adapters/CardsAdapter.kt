package com.lupi.magicapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lupi.magicapp.R
import com.lupi.magicapp.models.Card
import com.lupi.magicapp.viewholders.CardsViewHolder

class CardsAdapter(private val cardsList : List<Card>) : RecyclerView.Adapter<CardsViewHolder>(){

    var onItemClick : ((Card) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CardsViewHolder(layoutInflater.inflate(R.layout.card_layout, parent, false))
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val card = cardsList[position]
        holder.render(card)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(card)
        }
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

}