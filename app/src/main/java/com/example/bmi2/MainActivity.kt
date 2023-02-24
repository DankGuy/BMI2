package com.example.bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Linking of UI anc code
        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)
        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)
        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener {
            if(editTextWeight.text.isEmpty()) {
                editTextWeight.setError(getString(R.string.value_required))
            }
            if(editTextHeight.text.isEmpty()) {
                editTextHeight.setError(getString(R.string.value_required))
            }

            //BMI = weight / height in meters power 2
            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()
            var bmi = weight / (height/100).pow(2)

            if(bmi < 18.5) { //Underweight
                imageViewBMI.setImageResource(R.drawable.under)
                //Body Mass Index : 17.52
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                //Status : Underweight
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.under))
            }
            else if(bmi in 18.5..24.9) {
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                //Status : normal weight
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
            }
            else {
                imageViewBMI.setImageResource(R.drawable.over)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                //Status : over weight
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.over))
            }
        }

        buttonReset.setOnClickListener {
            imageViewBMI.setImageResource(R.drawable.empty)
            textViewBMI.text = String.format("%s : %s", getString(R.string.bmi), "")
            textViewStatus.text = String.format("%s : %s", getString(R.string.status), "")
            editTextHeight.text.clear()
            editTextWeight.text.clear()
        }
    }
}