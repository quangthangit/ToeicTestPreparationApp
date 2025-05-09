package com.example.toeicpreparation.ui.activity

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.toeicpreparation.R
import com.example.toeicpreparation.data.remote.Vocabulary
import com.example.toeicpreparation.ui.viewmodel.VocabularyViewModel


class OverViewVocabularyActivity : AppCompatActivity() {

    private lateinit var btnClose : ImageView

    private lateinit var vocabularyList: List<Vocabulary>
    private var currentIndex = 0

    private lateinit var viewModel: VocabularyViewModel

    private lateinit var en: TextView
    private lateinit var vi: TextView
    private lateinit var number : TextView
    private lateinit var pronunciation : TextView
    private lateinit var example : TextView

    private lateinit var btnNext: Button
    private lateinit var btnLast: Button

    private lateinit var vocabularyLayout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_over_view_vocabulary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnClose = findViewById<ImageView>(R.id.btn_close_overview)
        btnClose.setOnClickListener {
            finish()
        }

        en = findViewById<TextView>(R.id.en)
        vi = findViewById<TextView>(R.id.vi)
        number = findViewById<TextView>(R.id.number)
        pronunciation = findViewById<TextView>(R.id.pronunciation)
        example = findViewById<TextView>(R.id.example)

        btnLast = findViewById<Button>(R.id.btn_last)
        btnNext = findViewById<Button>(R.id.btn_next)

        viewModel = ViewModelProvider(this)[VocabularyViewModel::class.java]

        btnNext.setOnClickListener {
            if (currentIndex < vocabularyList.size - 1) {
                currentIndex++
                displayVocabulary(currentIndex, isNext = true)
            }
        }

        btnLast.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                displayVocabulary(currentIndex, isNext = false)
            }
        }

        viewModel.vocabulary.observe(this) { result ->
            result.onSuccess { list ->
                if (list.isNotEmpty()) {
                    vocabularyList = list
                    displayVocabulary(currentIndex)
                }
            }
            result.onFailure { error ->
            }
        }
        viewModel.loadData(1)
    }

    private fun displayVocabulary(index: Int, isNext: Boolean = true) {
        val outAnim = if (isNext) {
            AnimationUtils.loadAnimation(this, R.anim.slide_out_left)
        } else {
            AnimationUtils.loadAnimation(this, R.anim.slide_out_right)
        }

        val inAnim = if (isNext) {
            AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        } else {
            AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        }

        vocabularyLayout = findViewById(R.id.vocabularyLayout)

        vocabularyLayout.startAnimation(outAnim)

        outAnim.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {}

            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                val vocab = vocabularyList[index]
                en.text = vocab.en
                vi.text = vocab.vi
                number.text = buildString {
                    append("Question ")
                    append(index + 1)
                }
                pronunciation.text = vocab.pronunciation

                vocabularyLayout.startAnimation(inAnim)
            }

            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
        })
    }
}