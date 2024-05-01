package com.ucb.mendoza_semifinal

import java.io.Serializable

data class Course(
    val id: Int,
    val edpCode: String,
    val courseName: String,
    val grade: Double
) : Serializable

