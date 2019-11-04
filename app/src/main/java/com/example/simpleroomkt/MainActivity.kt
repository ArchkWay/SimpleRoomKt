package com.example.simpleroomkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.view.*

class MainActivity : AppCompatActivity(), CarAdapter.TouchEvent {
    private lateinit var carViewModel: CarViewModel
    private lateinit var carAdapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        carViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(CarViewModel::class.java)
        carAdapter = CarAdapter(this, this)
        rvCars.adapter = carAdapter
        rvCars.layoutManager = LinearLayoutManager(this)

        carViewModel.allCars.observe(this, Observer { cars ->
            if (cars.isEmpty()) {
                for (value in 1..3) {//generate three first cars
                    val car = CarNote(
                        0,
                        "2011 $value",
                        "Nexia $value",
                        "Daewoo $value",
                        "S $value",
                        "sedan $value"
                    )
                    carViewModel.insert(car)
                }
            }
            cars.let {
                carAdapter.setItems(it)
            }
        })
        btnAdd.setOnClickListener {
            create()
        }
    }


    override fun onClick(item: CarNote) {
        Toast.makeText(applicationContext, item.mark, LENGTH_SHORT).show()
    }

    override fun onHold(item: CarNote) {
        val items = arrayOf("Edit", "Delete")
        AlertDialog.Builder(this)
            .setTitle("What would you like to do?")
            .setItems(items) { p0, idx ->
                when (idx) {
                    0 -> {
                        edit(item)
                    }
                    1 -> {
                        carViewModel.delete(item)
                    }
                }
            }.show()
    }

    private fun create() {
        val layout = layoutInflater.inflate(R.layout.dialog, null)
        val customFragmentDialog = AlertDialog.Builder(this)
            .setTitle("Create a new note")
            .setView(layout)
            .setPositiveButton("Create") { p0, p1 ->
                val created = CarNote(
                    0,
                    layout.etYear.text.toString(),
                    layout.etMark.text.toString(),
                    layout.etProducer.text.toString(),
                    layout.etClassModel.text.toString(),
                    layout.etBody.text.toString()
                )

                carViewModel.insert(created)
            }
            .create()

        customFragmentDialog.show()
    }

    private fun edit(arg: CarNote) {
        val layout = layoutInflater.inflate(R.layout.dialog, null)

        val customFragmentDialog = AlertDialog.Builder(this)
            .setTitle("Edit this car")
            .setView(layout)
            .setPositiveButton("") { p0, p1 ->
                arg.year = layout.etYear.text.toString()
                arg.mark = layout.etMark.text.toString()
                arg.producer = layout.etProducer.text.toString()
                arg.classModel = layout.etClassModel.text.toString()
                arg.body = layout.etBody.text.toString()

                carViewModel.update(arg)
            }
            .create()
        customFragmentDialog.show()
    }
}
