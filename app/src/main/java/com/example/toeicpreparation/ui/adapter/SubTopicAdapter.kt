package com.example.toeicpreparation.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toeicpreparation.R
import com.example.toeicpreparation.data.remote.VocabularySubTopic
import com.example.toeicpreparation.ui.activity.OverViewVocabularyActivity
import com.example.toeicpreparation.ui.activity.QuizVocabularyActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class SubTopicAdapter(private var itemList: List<VocabularySubTopic>) : RecyclerView.Adapter<SubTopicAdapter.TopicViewHolder>() {

    private var inflater: LayoutInflater? = null

    class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val number: TextView = itemView.findViewById(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sub_topic, parent, false)
        inflater = LayoutInflater.from(parent.context)
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.name.text = itemList[position].name
        holder.number.text = (position+1).toString()
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val dialog = BottomSheetDialog(context)
            val view = inflater!!.inflate(R.layout.bottom_sheet_subtopic, null)
            val btnOverview = view.findViewById<Button>(R.id.btn_overview)
            val btnQuiz = view.findViewById<Button>(R.id.btn_quiz)
            btnOverview.setOnClickListener {
                val intent = Intent(context, OverViewVocabularyActivity::class.java)
                context.startActivity(intent)
                dialog.dismiss()
            }
            btnQuiz.setOnClickListener {
                val intent = Intent(context, QuizVocabularyActivity::class.java)
                context.startActivity(intent)
                dialog.dismiss()
            }
            dialog.setContentView(view)
            dialog.show()
        }
    }

    fun updateData(newDatas : List<VocabularySubTopic>) {
        itemList = newDatas
        notifyDataSetChanged()
    }

    override fun getItemCount() = itemList.size
}
