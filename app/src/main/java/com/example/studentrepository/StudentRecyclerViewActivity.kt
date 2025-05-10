package com.example.studentrepository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.studentrepository.model.Model
import com.example.studentrepository.model.Student

class StudentRecyclerViewActivity : AppCompatActivity() {
    var students: MutableList<Student>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        students = Model.shared.students

        val recyclerView: RecyclerView? = findViewById(R.id.student_recycler_view)
        recyclerView?.setHasFixedSize(true)

        val LayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = LayoutManager

        val adapter = StudentViewHolder.StudentRecyclerAdapter(students!!)
        recyclerView?.adapter = adapter

    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTextView: TextView? = null
        var idTextView: TextView? = null
        var checkbox: CheckBox? = null
        var student: Student? = null

        init {
            nameTextView = itemView.findViewById(R.id.list_row_username)
            idTextView = itemView.findViewById(R.id.list_row_userid)
            checkbox = itemView.findViewById(R.id.list_row_checkbox)

            checkbox?.apply {
                setOnClickListener { view ->
                    (tag as? Int)?.let { tag ->
                        student?.isChecked = (view as? CheckBox)?.isChecked ?: false
                    }

                }
            }
        }

        fun bind(student: Student, position: Int) {
            this.student = student
            nameTextView?.text = student?.name
            idTextView?.text = student?.id
            checkbox?.isChecked = student?.isChecked ?: false

            checkbox?.apply {
                isChecked = student?.isChecked ?: false
                tag = position
            }
        }

        class StudentRecyclerAdapter(private val students: List<Student>) :
            RecyclerView.Adapter<StudentViewHolder>() {
            override fun getItemCount(): Int = students.size

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
                val inflater = LayoutInflater.from(parent?.context)
                val view = inflater.inflate(R.layout.list_row, parent, false).apply {
                }
                return StudentViewHolder(view)
            }

            override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
                holder.bind(students.get(position), position)
            }

        }
    }
}