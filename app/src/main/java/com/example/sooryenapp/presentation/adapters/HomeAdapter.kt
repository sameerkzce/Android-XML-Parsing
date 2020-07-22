package com.example.sooryenapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sooryenapp.R
import com.example.sooryenapp.databinding.AdapterHomeBinding
import com.example.sooryenapp.dto.EntryDto
import java.lang.ref.WeakReference
/**
 * adapter class of Home fragment list view
 * */
class HomeAdapter(
    private val entryList: ArrayList<EntryDto>,
    private val listener: OnEntryClickListener
) : RecyclerView.Adapter<HomeAdapter.EntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val binding: AdapterHomeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_home, parent, false
        )
        return EntryViewHolder(binding, listener)
    }

    inner class EntryViewHolder(val binding: AdapterHomeBinding, listener: OnEntryClickListener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private val listenerRef: WeakReference<OnEntryClickListener> = WeakReference(listener);
        fun bind(item: EntryDto) {
            binding.viewModel = item
            binding.cardView.setOnClickListener(this)
        }

        //setting click listener for item
        override fun onClick(view: View?) {
            listenerRef.get()?.onClickEntry(entryList[adapterPosition])
        }
    }

    override fun getItemCount(): Int = entryList.size

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) =
        holder.bind(entryList[position])
}

//interface for getting on click listener
public interface OnEntryClickListener {
    fun onClickEntry(entry: EntryDto?)
}