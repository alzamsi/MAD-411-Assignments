package com.zybooks.assignment6

import android.os.Bundle
import android.widget.AutoCompleteTextView.Validator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.yourappname.ExpenseAdapter

class MainActivity : AppCompatActivity() {
    private val expense = mutableListOf<Expense>()
    private lateinit var expenseAdapter: ExpenseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val expenseEditText = findViewById<EditText>(R.id.expenseEditTextText)
        val amountEditText = findViewById<EditText>(R.id.amountEditTextNumberDecimal)
        val dateEditText = findViewById<EditText>(R.id.dateEditTextDate)
        val addExpenseButton = findViewById<Button>(R.id.addExpenseButton)



       addExpenseButton.setOnClickListener{
           val expenseName = expenseEditText.text.toString()
           val amount = amountEditText.text.toString().toDoubleOrNull()?:0.0
           val date = dateEditText.text.toString()


           if (expenseName.isNotEmpty() && amount.toString().isNotEmpty() && date.isNotEmpty()){
               Toast.makeText(this,"Expense Added: $expenseName, $amount on $date",Toast.LENGTH_LONG).show()

               expenseEditText.text.clear()
               amountEditText.text.clear()
               dateEditText.text.clear()
           }else {
               Toast.makeText(this, "Please Fill all fields", Toast.LENGTH_LONG).show()

           }

           expense.add(Expense(expenseName,amount,date))
           val expenseTextView = findViewById<TextView>(R.id.expenseRecycleView)
           expenseTextView.text = expense.toString()

           expenseAdapter = ExpenseAdapter(expense)
           expenseAdapter.notifyItemInserted(expense.size - 1)


       }
    }
}
