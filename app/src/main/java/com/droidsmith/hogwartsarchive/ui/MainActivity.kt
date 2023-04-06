package com.droidsmith.hogwartsarchive.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidsmith.hogwartsarchive.databinding.ActivityMainBinding
import com.droidsmith.hogwartsarchive.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kev.njunge.hogwartsarchive.util.Resource

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CharacterAdapter.OnItemClickListener {

    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterAdapter = CharacterAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = characterAdapter
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, true)
            }

            viewModel.characters.observe(this@MainActivity) { result ->

                characterAdapter.submitList(result.data)

                characterProgress.isVisible =
                    result is Resource.Loading && result.data.isNullOrEmpty()
                tvErrorText.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                tvErrorText.text = result.error?.localizedMessage
            }
        }


    }

    //    override fun onItemClick(position: Int) {
//        val selectedCharacter = viewModel.characters.value?.data?.get(position)
//        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
//            putExtra("selectedCharacter", selectedCharacter)
//        }
//        startActivity(intent)
//    }
    override fun onItemClick(position: Int) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        val character = viewModel.characters.value?.data?.get(position)
        character?.let {
            intent.putExtra("image", it.image)
            intent.putExtra("name", it.name)
            intent.putExtra("dob", it.dateOfBirth)
            intent.putExtra("actor", it.actor)
            intent.putExtra("gender", it.gender)
            intent.putExtra("patronus", it.patronus)
            intent.putExtra("house", it.house)
            intent.putExtra("ancestry", it.ancestry)
            startActivity(intent)
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_search,menu)
//
//        val searchItem = menu?.findItem(R.id.action_search)
//        val searchView = searchItem?.actionView as androidx.appcompat.widget.SearchView
//
//        searchView.setOnQueryTextListener(object : OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (query != null){
//                }
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//        })
//        return true
//    }


}