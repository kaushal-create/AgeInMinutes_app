package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById(R.id.btnDatePicker) as Button
        btn.setOnClickListener{view ->
            clickDatePicker(view)
        }
    }

    //function for opening calendar
    fun clickDatePicker(view : View){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                    "The choosen year is ${selectedYear}, month is ${selectedMonth +1} and the day is ${selectedDayOfMonth}", Toast.LENGTH_LONG).show()

                val selectedDate = "${selectedDayOfMonth}/${selectedMonth+1}/${selectedYear}"

                val showSelectedDate = findViewById(R.id.tvSelectedDate) as TextView
                showSelectedDate.text = selectedDate.toString()

                val sdf = SimpleDateFormat( "dd/MM/yyyy",Locale.ENGLISH)

                val thedate = sdf.parse(selectedDate)

                val selectedDateToMinutes = thedate!!.time/60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMniutes  = currentDate!!.time/60000

                val differenceInMinutes = currentDateToMniutes - selectedDateToMinutes

                val showDOBInMinute = findViewById(R.id.tvSelectedDateInMinutes) as TextView
                showDOBInMinute.text = differenceInMinutes.toString()
            },
            year, month, day).show()

    }
}


