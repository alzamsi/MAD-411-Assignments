package com.zybooks.assignment6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment



class FooterFragment : Fragment() {
    private lateinit var totalExpensesTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_footer, container, false)
        totalExpensesTextView = view.findViewById(R.id.totalExpensesTextView)
        return view
    }

    fun updateTotalExpenses(total: Double) {
        totalExpensesTextView.text = "Total Expenses: $$total"
    }
}
