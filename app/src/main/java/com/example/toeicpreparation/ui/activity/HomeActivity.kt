package com.example.toeicpreparation.ui.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.toeicpreparation.R
import com.example.toeicpreparation.ui.fragment.HomeFragment
import com.example.toeicpreparation.ui.fragment.VocabularyFragment
import androidx.core.graphics.toColorInt
import com.example.toeicpreparation.ui.fragment.AccountFragment
import com.example.toeicpreparation.ui.fragment.ProggressFragment


class HomeActivity : AppCompatActivity() {

    private lateinit var homeIcon : ImageView
    private lateinit var vocabularyIcon : ImageView
    private lateinit var proggressIcon : ImageView
    private lateinit var accountIcon : ImageView

    private lateinit var homeText : TextView
    private lateinit var vocabularyText : TextView
    private lateinit var proggressText : TextView
    private lateinit var accountText : TextView

    private lateinit var homeOption : LinearLayout
    private lateinit var vocabularyOption : LinearLayout
    private lateinit var proggressOption : LinearLayout
    private lateinit var accountOption : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_sky)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = false
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replaceFragment(HomeFragment())

        homeIcon = findViewById<ImageView>(R.id.homeIcon)
        vocabularyIcon = findViewById<ImageView>(R.id.vocabularyIcon)
        proggressIcon = findViewById<ImageView>(R.id.proggressIcon)
        accountIcon = findViewById<ImageView>(R.id.accountIcon)

        homeText = findViewById<TextView>(R.id.homeT)
        vocabularyText = findViewById<TextView>(R.id.vocabularyT)
        proggressText = findViewById<TextView>(R.id.proggressT)
        accountText = findViewById<TextView>(R.id.accountT)

        homeOption = findViewById<LinearLayout>(R.id.homeOption)
        vocabularyOption = findViewById<LinearLayout>(R.id.vocabularyOption)
        proggressOption = findViewById<LinearLayout>(R.id.proggressOption)
        accountOption = findViewById<LinearLayout>(R.id.accountOption)

        homeText.setTextColor(getResources().getColor(R.color.blue_sky))
        homeIcon.setColorFilter(getResources().getColor(R.color.blue_sky))

        homeOption.setOnClickListener {
            replaceFragment(HomeFragment())
            resetIcons()
            homeText.setTextColor(getResources().getColor(R.color.blue_sky))
            homeIcon.setColorFilter(getResources().getColor(R.color.blue_sky))
        }

        vocabularyOption.setOnClickListener {
            replaceFragment(VocabularyFragment())
            resetIcons()
            vocabularyIcon.setColorFilter(getResources().getColor(R.color.blue_sky))
            vocabularyText.setTextColor(getResources().getColor(R.color.blue_sky))
        }

        proggressOption.setOnClickListener {
            replaceFragment(ProggressFragment())
            resetIcons()
            proggressIcon.setColorFilter(getResources().getColor(R.color.blue_sky))
            proggressText.setTextColor(getResources().getColor(R.color.blue_sky))
        }

        accountOption.setOnClickListener {
            replaceFragment(AccountFragment())
            resetIcons()
            accountIcon.setColorFilter(getResources().getColor(R.color.blue_sky))
            accountText.setTextColor(getResources().getColor(R.color.blue_sky))
        }

//        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.home -> replaceFragment(HomeFragment())
//                R.id.vocabulary -> replaceFragment(VocabularyFragment())
//                R.id.account -> replaceFragment(AccountFragment())
//            }
//            true
//        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.homeLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun resetIcons() {
        homeIcon.setColorFilter("#82898F".toColorInt());
        vocabularyIcon.setColorFilter("#82898F".toColorInt());
        proggressIcon.setColorFilter("#82898F".toColorInt());
        accountIcon.setColorFilter("#82898F".toColorInt());
        accountText.setTextColor("#82898F".toColorInt())
        proggressText.setTextColor("#82898F".toColorInt())
        homeText.setTextColor("#82898F".toColorInt())
        vocabularyText.setTextColor("#82898F".toColorInt())
    }
}