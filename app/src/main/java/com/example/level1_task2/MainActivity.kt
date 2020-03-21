package com.example.level1_task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var alreadyExecuted: Boolean = false
    var value1: Boolean = false
    var value2: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!alreadyExecuted) {
            GenerateQuestion()
            alreadyExecuted = true
        }
        submit.setOnClickListener {
            CheckAnswers()
        }
    }
    private fun GenerateQuestion() {
        var situationFields = arrayOf<TextView>(Situation11, Situation12, Situation21, Situation22, Situation31, Situation32, Situation41, Situation42)
        for (case in 1..4){
         var multiply: Int = case * 2

         value1 = Math.random() < 0.5
         value2 = Math.random() < 0.5

            if (value1){
                situationFields[multiply-2].text = getString(R.string.True)
            } else{
                situationFields[multiply-2].text = getString(R.string.False)
            }
            if (!value2){
                situationFields[multiply-1].text = getString(R.string.True)
            }else {
                situationFields[multiply-1].text = getString(R.string.False)
            }
     }
    }
    private fun CheckAnswers() {
        var situationFields = arrayOf<TextView>(Situation11, Situation12, Situation21, Situation22, Situation31, Situation32, Situation41, Situation42)
        var answerFields = arrayOf<TextView>(Answer1, Answer2, Answer3, Answer4)
        var value3: Boolean = false
        var value4: Boolean = false
        var answer: Boolean = false
        var correct = 0
        for (case in 1..4) {
            if (answerFields[case-1].getText().toString() == getString(R.string.True)){
               answer = true
            }
            else if (answerFields[case-1].getText().toString() == getString(R.string.False)){
                answer = false
            }

            var multiply: Int = case * 2
            if(situationFields[multiply-2].text == getString(R.string.True)){
                    value3 = true
                } else{
                value3 = false
            }
            if(situationFields[multiply-1].text == getString(R.string.True)){
                value4 = true
            } else{
                value4 = false
            }
            val result = value3 && value4
            if(result == answer){
                correct++
            }
            answerFields[case-1].text = ""
        }
        Toast.makeText(this, correct.toString() + " Answers Correct", Toast.LENGTH_LONG).show()
        GenerateQuestion()
    }
}
