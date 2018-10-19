package com.ganiko.vitor.spajulioganiko


import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class ClienteAdapter (
        val clientes: List<Clientes>,
        val onClick: (Clientes) -> Unit

): RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>(){


    class ClienteViewHolder (view: View): RecyclerView.ViewHolder(view){
        val cardNome: TextView
        val cardImg: ImageView
        var cardProgress: ProgressBar
        val cardView: CardView


        init {
            cardNome = view.findViewById<TextView>(R.id.card_Nome)
            cardImg = view.findViewById<ImageView>(R.id.card_Imagem)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_Clientes)
        }

    }


    override fun getItemCount() = this.clientes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_clientes, parent, false)

        val holder = ClienteViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val contexto = holder.itemView.context
        val cliente = this.clientes[position]

        holder.cardNome.text = cliente.nome
        holder.cardProgress.visibility = View.VISIBLE

        Picasso.with(contexto).load(cliente.foto).fit().into(holder.cardImg, object: com.squareup.picasso.Callback{
            override fun onSuccess() {
                holder.cardProgress.visibility = View.GONE
            }

            override fun onError() {
                holder.cardProgress.visibility = View.GONE
            }
        })


        holder.itemView.setOnClickListener{onClick(cliente)}
    }

}
