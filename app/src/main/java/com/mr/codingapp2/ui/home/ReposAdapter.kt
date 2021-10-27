package com.mr.codingapp2.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mr.codingapp2.R
import com.mr.codingapp2.backend.model.Repository
import com.mr.codingapp2.databinding.ItemRepoBinding

class ReposAdapter(
    private val onClick: (Repository) -> Unit
) : ListAdapter<Repository, ReposAdapter.ViewHolder>(RepositoryDiffCallback) {

    inner class ViewHolder(
        private val binding: ItemRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var currentRepo: Repository? = null

        init {
            itemView.setOnClickListener {
                currentRepo?.let {
                    onClick(it)
                }
            }
        }

        fun bind(repo: Repository) {
            currentRepo = repo

            val context = itemView.context
            binding.tvTitle.text = repo.name
            binding.tvBody.text = repo.description
            binding.tvBottom.text = context.getString(
                R.string.repo_bottom_text,
                repo.stargazersCount,
                repo.forksCount
            )
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_repo, viewGroup, false)
        return ViewHolder(ItemRepoBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val repo = getItem(position)
        viewHolder.bind(repo)
    }
}

object RepositoryDiffCallback : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }
}
