package com.example.postintegrationcopy

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class MainActivity : AppCompatActivity() {
    private var textViewResult: TextView? = null
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewResult = findViewById(R.id.text_view_result)
        val gson = GsonBuilder().serializeNulls().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        comments
        posts
        createPost()
        updatePost()
        deletePost()
    }

    /*
                    content += "ID: " + post.getId() + "\n";
*/
    private val posts: Unit
        private get() {
            val parameters: MutableMap<String?, String?> =
                HashMap()
            parameters["userId"] = "1"
            parameters["_sort"] = "id"
            parameters["_order"] = "desc"
            val call =
                jsonPlaceHolderApi!!.getPosts(parameters)
            call!!.enqueue(object : Callback<List<Post>> {
                override fun onResponse(
                    call: Call<List<Post>>,
                    response: Response<List<Post>>
                ) {
                    if (!response.isSuccessful) {
                        textViewResult!!.text = "Code: " + response.code()
                        return
                    }
                    val posts = response.body()
                    for (post in posts) {
                        var content = ""
                        /*
                        content += "ID: " + post.getId() + "\n";
    */content += """
    User Id: ${post.getUserId().toString()}
    
    """.trimIndent()
                        content += """
                            Title: ${post.getTitle().toString()}
                            
                            """.trimIndent()
                        content += """
                            Text: ${post.getText().toString()}
                            
                            """.trimIndent()
                        textViewResult!!.append(content)
                    }
                }

                override fun onFailure(
                    call: Call<List<Post>>,
                    t: Throwable
                ) {
                    textViewResult!!.text = t.message
                }
            })
        }

    private val comments: Unit
        private get() {
            val call =
                jsonPlaceHolderApi!!.getComments("https://jsonplaceholder.typicode.com/posts/3/comments")
            call!!.enqueue(object : Callback<List<Comment>> {
                override fun onResponse(
                    call: Call<List<Comment>>,
                    response: Response<List<Comment>>
                ) {
                    if (!response.isSuccessful) {
                        textViewResult!!.text = "Code: " + response.code()
                        return
                    }
                    val comments = response.body()
                    for (comment in comments) {
                        var content = ""
                        content += """
                            ID: ${comment.getId.toString()}
                            
                            """.trimIndent()
                        content += """
                            Post Id: ${comment.getPostId().toString()}
                            
                            """.trimIndent()
                        content += """
                            Name: ${comment.getName().toString()}
                            
                            """.trimIndent()
                        content += """
                            Email: ${comment.getEmail().toString()}
                            
                            """.trimIndent()
                        content += """
                            Text: ${comment.getText().toString()}
                            
                            
                            """.trimIndent()
                        textViewResult!!.append(content)
                    }
                }

                override fun onFailure(
                    call: Call<List<Comment>>,
                    t: Throwable
                ) {
                    textViewResult!!.text = t.message
                }
            })
        }

    private fun createPost() {
        val post = Post(23, "New Title", "New Text")

//        Map<String, String> fields = new HashMap<>();
//        fields.put("userId", "25");
//        fields.put("title", "New Title");
        val call = jsonPlaceHolderApi!!.createPost(post)
        call!!.enqueue(object : Callback<Post> {
            override fun onResponse(
                call: Call<Post>,
                response: Response<Post>
            ) {
                if (!response.isSuccessful) {
                    textViewResult!!.text = "Code: " + response.code()
                    return
                }
                val postResponce = response.body()
                var content = ""
                content += """
                    Code: ${response.code()}
                    
                    """.trimIndent()
                /*
                content += "ID: " + postResponce.getId() + "\n";
*/content += """
    User Id: ${postResponce.getUserId().toString()}
    
    """.trimIndent()
                content += """
                    Title: ${postResponce.getTitle().toString()}
                    
                    """.trimIndent()
                content += """
                    Text: ${postResponce.getText().toString()}
                    
                    """.trimIndent()
                textViewResult!!.text = content
            }

            override fun onFailure(
                call: Call<Post>,
                t: Throwable
            ) {
                textViewResult!!.text = t.message
            }
        })
    }

    private fun updatePost() {
        val post = Post(12, null, "New Text")
        val call = jsonPlaceHolderApi!!.patchPost(5, post)
        call!!.enqueue(object : Callback<Post> {
            override fun onResponse(
                call: Call<Post>,
                response: Response<Post>
            ) {
                if (!response.isSuccessful) {
                    textViewResult!!.text = "Code: " + response.code()
                    return
                }
                val postResponce = response.body()
                var content = ""
                content += """
                    Code: ${response.code()}
                    
                    """.trimIndent()
                /*
                content += "ID: " + postResponce.getId() + "\n";
*/content += """
    User Id: ${postResponce.getUserId().toString()}
    
    """.trimIndent()
                content += """
                    Title: ${postResponce.getTitle().toString()}
                    
                    """.trimIndent()
                content += """
                    Text: ${postResponce.getText().toString()}
                    
                    """.trimIndent()
                textViewResult!!.text = content
            }

            override fun onFailure(
                call: Call<Post>,
                t: Throwable
            ) {
                textViewResult!!.text = t.message
            }
        })
    }

    private fun deletePost() {
        val call = jsonPlaceHolderApi!!.deletePost(5)
        call!!.enqueue(object : Callback<Void?> {
            override fun onResponse(
                call: Call<Void?>,
                response: Response<Void?>
            ) {
                textViewResult!!.text = "code: " + response.code()
            }

            override fun onFailure(
                call: Call<Void?>,
                t: Throwable
            ) {
                textViewResult!!.text = t.message
            }
        })
    }
}
