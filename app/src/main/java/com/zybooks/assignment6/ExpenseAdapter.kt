package com.example.yourappname

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zybooks.assignment6.Expense
import com.zybooks.assignment6.R

class ExpenseAdapter(
    private val expenses: List<Expense>,
    private val onDeleteClickListener: (Expense) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.expenseNameTextView)
        private val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateEditTextDate)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

        fun bind(expense: Expense) {
            nameTextView.text = expense.name
            amountTextView.text = "$${expense.amount}"
            dateTextView.text = expense.date
            deleteButton.setOnClickListener {
                onDeleteClickListener(expense)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount(): Int = expenses.size
}
