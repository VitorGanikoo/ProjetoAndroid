package com.ganiko.vitor.spajulioganiko

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class TerapiaAdapter (
    val terapias: List<Terapias>,
    val onClick: (Terapias) -> Unit

    ): RecyclerView.Adapter<TerapiaAdapter.TerapiaViewHolder>(){


        class TerapiaViewHolder (view: View): RecyclerView.ViewHolder(view){
            val cardNome: TextView
            val cardImg: ImageView
            val cardView: CardView


            init {
                cardNome = view.findViewById(R.id.card_Nome)
                cardImg = view.findViewById(R.id.card_Imagem)
                cardView = view.findViewById(R.id.card_Terapias)
            }

        }


        override fun getItemCount() = this.terapias.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TerapiaViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_terapias, parent, false)

            val holder = TerapiaViewHolder(view)
            return holder
        }

        override fun onBindViewHolder(holder: TerapiaViewHolder, position: Int) {
            val contexto = holder.itemView.context
            val terapia = this.terapias[position]

            holder.cardNome.text = terapia.nome

            Picasso.with(contexto).load(terapia.foto).fit().into(holder.cardImg, object: com.squareup.picasso.Callback{
                override fun onSuccess() {

                }

                override fun onError() {

                }
            })


            holder.itemView.setOnClickListener{onClick(terapia)}
        }
}