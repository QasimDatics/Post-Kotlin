package com.example.postintegrationcopy

import com.google.gson.annotations.SerializedName

class Post(
    val userId: Int,
    val title: String, @field:SerializedName("body") val text: String
) {
    private val Id: Int? = null

}


