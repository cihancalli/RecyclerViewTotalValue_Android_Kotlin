package com.zerdasoftware.recyclerviewtotalvalue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private lateinit var data: ArrayList<ItemDatamodel>
    private lateinit var itemDatamodel:ItemDatamodel
    private lateinit var adapter: RVTotalAdapter

    var Expensesname = arrayOf(
        "Rent 1", "Coffee 1", "Lunch 1", "Dinner 1", "Transport 1", "Other 1", "Home 1", "PC 1", "Phone 1", "IPad 1", "Book 1", "Tea 1",
        "Rent 2", "Coffee 2", "Lunch 2", "Dinner 2", "Transport 2", "Other 2", "Home 2", "PC 2", "Phone 2", "IPad 2", "Book 2", "Tea 2",
        "Rent 3", "Coffee 3", "Lunch 3", "Dinner 3", "Transport 3", "Other 3", "Home 3", "PC 3", "Phone 3", "IPad 3", "Book 3", "Tea 3"
    )


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_recycler_view_expenses.setHasFixedSize(true)
        my_recycler_view_expenses.layoutManager = LinearLayoutManager(this)

        data = ArrayList()

        for (i in 0..(Expensesname.size -1)){
            itemDatamodel = ItemDatamodel(i, Expensesname[i])
            data.add(itemDatamodel)
        }

        adapter = RVTotalAdapter(data)
        my_recycler_view_expenses.adapter = adapter
        adapter.notifyDataSetChanged()




    }
}