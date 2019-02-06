package com.example.calc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var ex = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nums: Array<Button> = arrayOf(zero, one, two, three, four, five, six, seven, eight, nine)

        for (i in 0..9)
            nums[i].setOnClickListener {
                setText(i.toString())
            }
        divide.setOnClickListener { setText("/") }
        multiply.setOnClickListener { setText("*") }
        sub.setOnClickListener { setText("-") }
        add.setOnClickListener { setText("+") }
        dot.setOnClickListener { setText(".") }

        del.setOnClickListener {
            ex.substring(0, ex.length - 1)
            textView.text = ex
        }

        equal.setOnClickListener {
            if (ex.matches(Regex("""\d+(\.\d+)?[-+*/]\d+(\.\d+)?"""))) {
                textView.text = getResult(ex)
            }
             else {
                textView.text = "error"
            }
            ex = ""
        }
    }

    private fun getResult(st: String): String {
        val d = Regex("""[-+*/]""").split(st).toList()
        val v = Regex("""[-+*/]""").findAll(st).toList()
        var res = 0.0
        when(v[0].value){
            "+" -> res = d[0].toDouble() + d[1].toDouble()
            "-" -> res = d[0].toDouble() - d[1].toDouble()
            "/" -> res = d[0].toDouble() / d[1].toDouble()
            "*" -> res = d[0].toDouble() * d[1].toDouble()
        }
        return res.toString()
    }

    private fun setText(s: String) {
        ex += s
        textView.text = ex
    }
}
