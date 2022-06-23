package com.zerdasoftware.recyclerviewtotalvalue

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVTotalAdapter(var dataSet:ArrayList<ItemDatamodel>): RecyclerView.Adapter<RVTotalAdapter.MyViewHolder>()
{
    private lateinit var rootView:View
    private lateinit var context: Context

    private var ExpAmtArray:ArrayList<String> = ArrayList()
    private var isOnTextChanged:Boolean = false
    private var ExpenseFinalTotal:Int = 0
    private lateinit var textviewTotalExpense:TextView



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var expensesName:TextView
        var expHeld:EditText
        var imageButton:Button

        init
        {
            expensesName = itemView.findViewById(R.id.textViewExpName)
            expHeld = itemView.findViewById(R.id.ExpHeld)
            imageButton = itemView.findViewById(R.id.ExpBimageSelect)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        context = parent.context
        rootView = (context as Activity).window.decorView.findViewById(android.R.id.content)
        textviewTotalExpense = rootView.findViewById(R.id.totalExpense)

        val view:View = LayoutInflater.from(context).inflate(R.layout.card_prod_list,parent,false)
        val myViewHolder = MyViewHolder(view)

        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int)
    {
        var expensesName:TextView = holder.expensesName
        var expHeld:EditText = holder.expHeld
        var imageButton:Button = holder.imageButton

        expensesName.setText(dataSet.get(position).ExpenseName)

        expHeld.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
                isOnTextChanged = true
            }

            override fun afterTextChanged(editable: Editable?)
            {
                ExpenseFinalTotal = 0
                if (isOnTextChanged)
                {
                    isOnTextChanged = false

                    try
                    {
                        ExpenseFinalTotal = 0

                        for (i in 0..position)
                        {
                            val inposition1:Int = position

                            if (i != position)
                            {
                                ExpAmtArray.add("0")
                            }

                            else
                            {
                                ExpAmtArray.add("0")
                                ExpAmtArray[inposition1] = editable.toString()
                                break
                            }
                        }

                        for (i in 0..(ExpAmtArray.size-1))
                        {
                            val tempTotalExpenase:Int = ExpAmtArray[i].toInt()
                            ExpenseFinalTotal += tempTotalExpenase
                        }

                        textviewTotalExpense.text = "Total Expenses: $ $ExpenseFinalTotal"
                    }

                    catch (e:NumberFormatException)
                    {
                        ExpenseFinalTotal = 0

                        for (i in 0..position)
                        {
                            Log.d("TimesRemoved", " : $i")
                            val newposition:Int = position

                            if (i == newposition)
                            {
                                ExpAmtArray[newposition] = "0"
                            }
                        }

                        for (i in 0..(ExpAmtArray.size-1))
                        {
                            val tempTotalExpenase:Int = ExpAmtArray[i].toInt()

                            ExpenseFinalTotal += tempTotalExpenase
                        }

                        textviewTotalExpense.setText("Total Expenses: $ $ExpenseFinalTotal")
                    }

                }
            }

        })

    }

    override fun getItemCount(): Int
    {
        return dataSet.size
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int
    {
        return position
    }
}