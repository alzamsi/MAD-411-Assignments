package com.zybooks.assignment6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourappname.ExpenseAdapter

class MainActivity : AppCompatActivity() {
    private val expenseList = mutableListOf<Expense>()
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val expenseEditText = findViewById<EditText>(R.id.expenseEditTextText)
        val amountEditText = findViewById<EditText>(R.id.amountEditTextNumberDecimal)
        val dateEditText = findViewById<EditText>(R.id.dateEditTextDate)
        val addExpenseButton = findViewById<Button>(R.id.addExpenseButton)
        val expenseRecyclerView = findViewById<RecyclerView>(R.id.expenseRecycleView)

        expenseAdapter = ExpenseAdapter(expenseList) { expense ->
            val index = expenseList.indexOf(expense)
            if (index != -1) {
                expenseList.removeAt(index)
                expenseAdapter.notifyItemRemoved(index)
            }
        }
        expenseRecyclerView.layoutManager = LinearLayoutManager(this)
        expenseRecyclerView.adapter = expenseAdapter

        addExpenseButton.setOnClickListener {
            val expenseName = expenseEditText.text.toString()
            val amount = amountEditText.text.toString().toDoubleOrNull() ?: 0.0
            val date = dateEditText.text.toString()

            if (expenseName.isNotEmpty() && amount > 0.0 && date.isNotEmpty()) {
                expenseList.add(Expense(expenseName, amount, date))
                expenseAdapter.notifyItemInserted(expenseList.size - 1)

                Toast.makeText(this, "Expense Added: $expenseName, $amount on $date", Toast.LENGTH_LONG).show()

                expenseEditText.text.clear()
                amountEditText.text.clear()
                dateEditText.text.clear()
            } else {
                Toast.makeText(this, "Please fill all fields with valid values", Toast.LENGTH_LONG).show()
            }
        }
    }
}