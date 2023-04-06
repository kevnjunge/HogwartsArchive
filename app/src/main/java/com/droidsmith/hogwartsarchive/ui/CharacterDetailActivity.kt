package com.droidsmith.hogwartsarchive.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.droidsmith.hogwartsarchive.R
import com.droidsmith.hogwartsarchive.databinding.ActivityCharacterDetailBinding


class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val dob = intent.getStringExtra("dob")
        val actor = intent.getStringExtra("actor")
        val gender = intent.getStringExtra("gender")
        val patronus = intent.getStringExtra("patronus")
        val house = intent.getStringExtra("house")
        val ancestry = intent.getStringExtra("ancestry")

        Glide.with(this)
            .load(image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.image_not_found)
            .override(500, 500)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.IvCharacterDetailImage)
        binding.tvActor.text = getString(R.string.actor, actor )
        binding.tvCharacterName.text = getString(R.string.character_name, name )
        binding.tvGender.text = getString(R.string.gender, gender )
        binding.tvDOB.text = getString(R.string.dob, dob )
        binding.tvAncestry.text = getString(R.string.ancestry, ancestry )
        binding.tvHouse.text = getString(R.string.house, house )
        binding.tvPatronus.text = getString(R.string.patronus, patronus )



    }
}