package com.example.yourappname

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zybooks.assignment6.Expense
import com.zybooks.assignment6.R
import kotlin.io.path.name

class ExpenseAdapter(private val expenses: MutableList<Expense>) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.expenseRecycleView)
        val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = expenses[position]
        holder.nameTextView.text = currentExpense.name
        holder.amountTextView.text = currentExpense.amount.toString()

        holder.deleteButton.setOnClickListener {
            expenses.removeAt(position)
            notifyItemRemoved(position)
        }

    }


    override fun getItemCount() = expenses.size
}