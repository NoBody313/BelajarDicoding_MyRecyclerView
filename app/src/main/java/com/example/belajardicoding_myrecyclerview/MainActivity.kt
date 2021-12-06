package com.example.belajardicoding_myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belajardicoding_myrecyclerview.adapter.CardViewHeroAdapter
import com.example.belajardicoding_myrecyclerview.adapter.GridHeroAdapter
import com.example.belajardicoding_myrecyclerview.adapter.ListHeroAdapter
import com.example.belajardicoding_myrecyclerview.data.Hero
import com.example.belajardicoding_myrecyclerview.data.HeroesData

class MainActivity : AppCompatActivity() {

    private lateinit var rvHero: RecyclerView
    private var list: ArrayList<Hero> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvHero = findViewById(R.id.rv_heroes)
        rvHero.setHasFixedSize(true)

        list.addAll(HeroesData.listData)
        showRecyclerList()

    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(this, "Kamu memilih" + hero.name, Toast.LENGTH_SHORT).show()
    }

    // Linear Layout
    private fun showRecyclerList() {
        rvHero.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHero.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    // Grid Layout
    private fun showRecyclerGrid() {
        rvHero.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rvHero.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object : OnItemClickCallback {
           override fun onItemClicked(data: Hero) {
               showRecycleCardView()
           }
        })
    }

    // CardView
    private fun showRecycleCardView() {
        rvHero.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        rvHero.adapter = cardViewHeroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {

        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }

            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }

            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecycleCardView()
            }
        }
        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}