package com.ganiko.vitor.spajulioganiko

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ConsultaAdapter (
    val consultas: List<Consultas>,
    val onClick: (Consultas) -> Unit

    ): RecyclerView.Adapter<ConsultaAdapter.ConsultaViewHolder>(){


        class ConsultaViewHolder (view: View): RecyclerView.ViewHolder(view){
            val cardNome: TextView
            val cardImg: ImageView
            val cardView: CardView


            init {
                cardNome = view.findViewById(R.id.card_Nome_Consulta)
                cardImg = view.findViewById(R.id.card_Imagem_Consulta)
                cardView = view.findViewById(R.id.card_Consultas)
            }

        }


        override fun getItemCount() = this.consultas.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultaViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_consultas, parent, false)

            val holder = ConsultaViewHolder(view)
            return holder
        }

        override fun onBindViewHolder(holder: ConsultaViewHolder, position: Int) {
            val contexto = holder.itemView.context
            val consulta = this.consultas[position]

            holder.cardNome.text = consulta.nomeCliente

            Picasso.with(contexto).load(consulta.foto).fit().into(holder.cardImg, object: com.squareup.picasso.Callback{
                override fun onSuccess() {

                }

                override fun onError() {

                }
            })


            holder.itemView.setOnClickListener{onClick(consulta)}
        }
}