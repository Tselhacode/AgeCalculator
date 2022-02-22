package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var showDate : TextView? = null
    var minCountDisplay : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //declaration and connection
        val dateBtn = findViewById<Button>(R.id.selectDateBtn)
        showDate = findViewById(R.id.dateTextView)
        minCountDisplay = findViewById(R.id.noMinsTextView)

        //action when date is selected
        dateBtn.setOnClickListener() {
            clickDatePicker()
        }
    }

  private fun clickDatePicker() {
      val calendar: Calendar = Calendar.getInstance()
      val year = calendar.get(Calendar.YEAR)
      val month = calendar.get(Calendar.MONTH)
      val day = calendar.get(Calendar.DAY_OF_MONTH)
      DatePickerDialog(this,DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

          Toast.makeText(this,"$dayOfMonth/${month+1}/$year selected" , Toast.LENGTH_LONG).show()
          var selectedDate = "$dayOfMonth/${month+1}/$year"

          val dateFormat = SimpleDateFormat("dd/mm/yy",Locale.ENGLISH)
          showDate?.setText(selectedDate)
          val theDate = dateFormat.parse(selectedDate)
          theDate?.let {
              val selectedDateInDays = theDate.time/86400000
              val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
              currentDate?.let{
                  val currentDateInMinutes = currentDate.time/86400000
                  val differenceInMinutes = currentDateInMinutes - selectedDateInDays
                  minCountDisplay?.text = differenceInMinutes.toString()
              }

          }

      },
          year,
          month,
          day
      ).show();

  }

    }





