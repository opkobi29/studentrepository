package com.example.studentrepository

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.LayoutInflater
import android.widget.TextView


class StudentsListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listView: ListView? = findViewById(R.id.students_list_view)
        listView!!.adapter = StudentAdapter()
    }
    class StudentAdapter(): BaseAdapter() {
        override fun getCount(): Int =10



        override fun getItem(position: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(position: Int): Long {
            TODO("Not yet implemented")
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(parent?.context)
            val view = convertView ?: inflater.inflate(R.layout.list_row, parent, false)

            val nameTextView : TextView = view.findViewById(R.id.list_row_username)
            val idTextView : TextView = view.findViewById(R.id.list_row_userid)
            val checkbox = view.findViewById<View>(R.id.list_row_checkbox)

            nameTextView.text = "Amit Unger"
            idTextView.text = "12345"


            return view
        }
    }
}