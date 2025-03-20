package com.zybooks.assignment6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExpenseDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_details)

        val nameTextView: TextView = findViewById(R.id.textExpenseName)
        val amountTextView: TextView = findViewById(R.id.textExpenseAmount)
        val dateTextView: TextView = findViewById(R.id.textExpenseDate)
        val tipsButton: Button = findViewById(R.id.buttonFinancialTips)

        // Get data from Intent Extras
        val name = intent.getStringExtra("expense_name") ?: "Unknown"
        val amount = intent.getDoubleExtra("expense_amount", 0.0)
        val date = intent.getStringExtra("expense_date") ?: "No Date"

        // Set data to TextViews
        nameTextView.text = "Name: $name"
        amountTextView.text = "Amount: $$amount"
        dateTextView.text = "Date: $date"

        // Implicit Intent: Open a financial tips website
        tipsButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.investopedia.com/"))
            startActivity(intent)
        }
    }
}
