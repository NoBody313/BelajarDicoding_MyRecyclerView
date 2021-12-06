package com.example.belajardicoding_myrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.belajardicoding_myrecyclerview.OnItemClickCallback
import com.example.belajardicoding_myrecyclerview.R
import com.example.belajardicoding_myrecyclerview.data.Hero

class CardViewHeroAdapter(private val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<CardViewHeroAdapter.CardViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare: Button = itemView.findViewById(R.id.btn_set_share)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_hero, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val hero = listHero[position]

        Glide.with(holder.itemView.context)
            .load(hero.photo)
            .apply(RequestOptions().override(350, 350))
            .into(holder.imgPhoto)

        // Text
        holder.tvName.text = hero.name
        holder.tvDetail.text = hero.detail

        // Button

        //Favorite Btn
        holder.btnFavorite.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Favorite" + listHero[holder.adapterPosition].name,
                Toast.LENGTH_SHORT
            ).show()
        }

        // Share Btn
        holder.btnShare.setOnClickListener {
            Toast.makeText(
                holder.btnShare.context, "Share" + listHero[holder.adapterPosition].name,
                Toast.LENGTH_SHORT
            ).show()
        }

        // Item View
        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Kamu memilih" + listHero[holder.adapterPosition].name,
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listHero.size
    }
}