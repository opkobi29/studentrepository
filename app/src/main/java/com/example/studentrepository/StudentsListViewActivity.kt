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
import android.widget.CheckBox
import android.widget.TextView
import com.example.studentrepository.model.Model
import com.example.studentrepository.model.Student



class StudentsListViewActivity : AppCompatActivity() {
    var students: MutableList<Student>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        students = Model.shared.students
        val listView: ListView? = findViewById(R.id.students_list_view)
        listView!!.adapter = StudentAdapter()
    }

    inner class StudentAdapter() : BaseAdapter() {
        override fun getCount(): Int = students?.size ?: 0


        override fun getItem(position: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(position: Int): Long {
            TODO("Not yet implemented")
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(parent?.context)

            //val view = convertView ?: inflater.inflate(R.layout.list_row, parent, false)
            var view = convertView
            if (view == null) {
                view = inflater.inflate(R.layout.list_row, parent, false)
                val checkbox: CheckBox = view.findViewById(R.id.list_row_checkbox)

                checkbox?.apply {
                    setOnClickListener {
                        (tag as? Int)?.let { tag ->
                            val student = students?.get(tag)
                            student?.isChecked = (view as? CheckBox)?.isChecked ?: false
                        }
                    }
                }
            }
                val student = students?.get(position)
                val nameTextView: TextView? = view?.findViewById(R.id.list_row_username)
                val idTextView: TextView? = view?.findViewById(R.id.list_row_userid)
                val checkbox: CheckBox? = view?.findViewById(R.id.list_row_checkbox)


                nameTextView?.text = student?.name
                idTextView?.text = student?.id
                checkbox?.isChecked = student?.isChecked ?: false

                checkbox?.apply {
                    isChecked = student?.isChecked ?: false
                    tag = position
                }


                return view!!
            }


    }
}
