package com.zybooks.assignment6

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourappname.ExpenseAdapter
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val expenses = mutableListOf<Expense>()
    private lateinit var adapter: ExpenseAdapter
    private lateinit var dateEditText: EditText
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        loadFragment(HeaderFragment(), R.id.headerFragmentContainer)
        footerFragment = FooterFragment()
        loadFragment(footerFragment, R.id.footerFragmentContainer)

        // Initialize views
        val expenseNameEditText = findViewById<EditText>(R.id.expenseNameEditText)
        val amountEditText = findViewById<EditText>(R.id.amountEditText)
        dateEditText = findViewById<EditText>(R.id.dateEditText)
        val addExpenseButton = findViewById<Button>(R.id.addExpenseButton)
        val recyclerView = findViewById<RecyclerView>(R.id.expensesRecyclerView)

        // Initialize RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpenseAdapter(expenses) { expense: Expense ->
            expenses.remove(expense)
            adapter.notifyDataSetChanged()
        }
        recyclerView.adapter = adapter

        // Set up Date EditText
        dateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        // Set up Add Expense Button
        addExpenseButton.setOnClickListener {
            val name = expenseNameEditText.text.toString()
            val amount = amountEditText.text.toString().toDoubleOrNull()
            val date = if (dateEditText.text.isNotBlank()) dateEditText.text.toString() else null

            if (name.isNotEmpty() && amount != null) {
                val expense = Expense(name, amount, date)
                expenses.add(expense)
                adapter.notifyDataSetChanged()
                footerFragment.updateTotalAmount(amount)

                // Clear input fields
                expenseNameEditText.text.clear()
                amountEditText.text.clear()
                dateEditText.text.clear()
            } else {
                Toast.makeText(this, "Please enter valid expense details", Toast.LENGTH_SHORT).show()
            }
        }



    }

    override fun onStart() {
        super.onStart()
        Log.d("ActivityLifecycle", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityLifecycle", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ActivityLifecycle", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ActivityLifecycle", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityLifecycle", "onDestroy called")
    }
    //used an outside source to help with the date picker
    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%02d/%02d/%04d", selectedMonth + 1, selectedDay, selectedYear)
                dateEditText.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
