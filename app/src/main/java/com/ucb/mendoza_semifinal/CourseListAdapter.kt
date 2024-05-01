package com.ucb.mendoza_semifinal
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
class CourseListAdapter(private val context: Context, private val courses: List<Course>) : BaseAdapter() {

    override fun getCount(): Int {
        return courses.size
    }

    override fun getItem(position: Int): Any {
        return courses[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.mendoza_custom_list, parent, false)
        }

        val currentCourse = courses[position]

        val edpCodeTextView = listItemView!!.findViewById<TextView>(R.id.edpCode)
        edpCodeTextView.text = currentCourse.edpCode

        val courseNameTextView = listItemView.findViewById<TextView>(R.id.courseName)
        courseNameTextView.text = currentCourse.courseName

        val gradeTextView = listItemView.findViewById<TextView>(R.id.grade)
        gradeTextView.text = currentCourse.grade.toString()

        // Set background color based on grade
        val backgroundColor = if (currentCourse.grade > 3.0) Color.RED else Color.GREEN
        listItemView.setBackgroundColor(backgroundColor)

        return listItemView
    }
}


