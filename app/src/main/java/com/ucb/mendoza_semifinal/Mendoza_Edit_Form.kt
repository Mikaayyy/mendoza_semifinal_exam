package com.ucb.mendoza_semifinal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Mendoza_Edit_Form : AppCompatActivity() {
    private lateinit var edpCodeEditText: EditText
    private lateinit var courseNameEditText: EditText
    private lateinit var gradeEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mendoza_edit_form)

        edpCodeEditText = findViewById(R.id.editEdpCode)
        courseNameEditText = findViewById(R.id.editCourseName)
        gradeEditText = findViewById(R.id.editGrade)
        saveButton = findViewById(R.id.saveButton)

        // Retrieve the course ID from the intent extras
        val courseId = intent.getStringExtra("course_id")

        // Fetch the course details from the database or any other source based on the courseId

        // Set the fetched data to the EditText fields for editing
        // Example:
        // edpCodeEditText.setText(fetchedCourse.edpCode)
        // courseNameEditText.setText(fetchedCourse.courseName)
        // gradeEditText.setText(fetchedCourse.grade.toString())

        // Set a click listener for the save button
        saveButton.setOnClickListener {
            // Handle save action
            // You can retrieve the updated values from the EditText fields and save them
            // to the database or any other source
        }
    }
}