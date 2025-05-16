package com.example.toeicpreparation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toeicpreparation.R
import com.example.toeicpreparation.ui.adapter.TopicAdapter
import com.example.toeicpreparation.ui.viewmodel.VocabularyTopicViewModel

class VocabularyFragment : Fragment() {

    private lateinit var viewModel: VocabularyTopicViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TopicAdapter
    private lateinit var emptyView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_vocabulary, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.listTopic)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TopicAdapter(emptyList())
        recyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[VocabularyTopicViewModel::class.java]
        emptyView = view.findViewById<View>(R.id.emptyView)

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.topics.observe(viewLifecycleOwner) { result ->
            result.onSuccess { topics ->
                if (topics.isEmpty()) {
//                    recyclerView.visibility = View.GONE
                    emptyView.visibility = View.VISIBLE
                } else {
                    adapter.updateTopics(topics)
                    recyclerView.visibility = View.VISIBLE
                    emptyView.visibility = View.GONE
                }
            }.onFailure {
//                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            }
        }

        if (viewModel.topics.value?.getOrNull().isNullOrEmpty()) {
            viewModel.loadTopicsIfNeeded()
        }
    }
}

