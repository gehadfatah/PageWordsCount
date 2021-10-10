package com.goda.pageword.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goda.data.local.models.Word
import com.goda.pageword.databinding.WordItemBinding
import com.goda.pageword.presentation.common.WordListUtil.reSortList

class WordsListAdapter : ListAdapter<Word, WordsListAdapter.WordViewHolder>(
    WordsDiffCallBack()
) {
     var isAscending = true

    fun reSort(list:List<Word>) {

        submitList(list)
        isAscending = !isAscending
    }
    fun search(list:List<Word>) {

      submitList(list)
    }
    class WordViewHolder(private val binding: WordItemBinding) :   RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) {
            binding.wordTv.text = word.content
            binding.countTv.text = word.repeatedNo.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
       return WordViewHolder(
            WordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class WordsDiffCallBack : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(
        oldItem: Word,
        newItem: Word
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Word,
        newItem: Word
    ): Boolean {
        return oldItem == newItem
    }


}