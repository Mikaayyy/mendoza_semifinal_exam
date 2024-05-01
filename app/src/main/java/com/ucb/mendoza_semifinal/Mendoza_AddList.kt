package com.ucb.mendoza_semifinal

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
class Mendoza_AddList : AppCompatActivity() {
    private lateinit var edpCodeEditText: EditText
    private lateinit var courseNameEditText: EditText
    private lateinit var gradeEditText: EditText
    private lateinit var addCourseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mendoza_add_list)

        edpCodeEditText = findViewById(R.id.edpCodeEditText)
        courseNameEditText = findViewById(R.id.courseNameEditText)
        gradeEditText = findViewById(R.id.gradeEditText)
        addCourseButton = findViewById(R.id.addCourseButton)

        addCourseButton.setOnClickListener {
            // Get the text entered in the EditText fields
            val edpCode = edpCodeEditText.text.toString()
            val courseName = courseNameEditText.text.toString()
            val gradeStr = gradeEditText.text.toString()

            // Retrieve the list of courses from the intent extras
            val coursesList =
                intent.getSerializableExtra("courses_list") as? ArrayList<Course> ?: ArrayList()

            // Validate the input fields
            if (edpCode.isEmpty() || courseName.isEmpty() || gradeStr.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Convert the grade string to double
            val grade = gradeStr.toDouble()

            // Create a new Course object
            val course = Course(coursesList.size + 1, edpCode, courseName, grade)

            // Pass the new course back to the calling activity
            val resultIntent = Intent()
            resultIntent.putExtra("added_course", course)
            setResult(Activity.RESULT_OK, resultIntent)

            // Finish the activity
            finish()
        }

    }
}
