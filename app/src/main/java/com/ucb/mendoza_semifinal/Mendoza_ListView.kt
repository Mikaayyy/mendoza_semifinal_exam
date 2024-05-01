package com.ucb.mendoza_semifinal

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class Mendoza_ListView : AppCompatActivity() {
    private lateinit var studentCoursesListView: ListView
    private lateinit var addIcon: ImageView
    private lateinit var courseListAdapter: CourseListAdapter
    private val courses = mutableListOf<Course>(
        Course(1, "78991", "IMDBSYS", 2.7),
        Course(2, "89123", "TECHNO", 1.7),
        Course(3, "901234", "INTPROG", 3.2),
        Course(4, "981234", "SYSARCH", 1.2),
        Course(5, "911234", "SYSAD", 2.2)
    )

    companion object {
        const val ADD_COURSE_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mendoza_list_view)

        studentCoursesListView = findViewById(R.id.studentCoursesListView)
        addIcon = findViewById(R.id.addIcon)

        // Initialize adapter
        courseListAdapter = CourseListAdapter(this, courses)
        studentCoursesListView.adapter = courseListAdapter

        // Set item click listener
        studentCoursesListView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val course = parent.getItemAtPosition(position) as Course
                showEditDeleteDialog(course)
            }

        addIcon.setOnClickListener {
            // Start the AddCourseActivity when addIcon is clicked
            val intent = Intent(this@Mendoza_ListView, Mendoza_AddList::class.java)
            intent.putExtra("courses_list", ArrayList(courses)) // Pass the list as serializable extra
            startActivityForResult(intent, ADD_COURSE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_COURSE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Retrieve the added course from the intent data
            val addedCourse = data?.getSerializableExtra("added_course") as? Course
            addedCourse?.let {
                // Add the added course to the list and update the adapter
                courses.add(it)
                courseListAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun showEditDeleteDialog(course: Course) {
        // Inflate the custom dialog layout
        val dialogView = LayoutInflater.from(this).inflate(R.layout.mendoza_dialog_view, null)

        // Find the buttons in the custom dialog layout
        val editButton = dialogView.findViewById<TextView>(R.id.editButton)
        val deleteButton = dialogView.findViewById<TextView>(R.id.deleteButton)

        // Create the AlertDialog instance
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Edit or Delete?")
            .setView(dialogView) // Set the custom dialog layout
            .setNegativeButton("Cancel", null)
            .create()

        // Set click listeners for the buttons
        editButton.setOnClickListener {
            // Handle edit action
            // You can implement the edit logic here
            // For example, navigate to an edit activity
            val intent = Intent(this@Mendoza_ListView, Mendoza_Edit_Form::class.java)
            // Pass any necessary data to the edit activity, such as the course ID or other details
            intent.putExtra("course_id", course.id) // Example: Pass the course ID
            startActivity(intent)
            alertDialog.dismiss() // Dismiss the dialog
        }
        deleteButton.setOnClickListener {
            // Handle delete action
            courses.remove(course)
            // Update the adapter to reflect the changes
            courseListAdapter.notifyDataSetChanged()
            alertDialog.dismiss() // Dismiss the dialog
        }
        alertDialog.show()
    }
}
