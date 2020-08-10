package com.example.postintegrationcopy

import retrofit2.Call
import retrofit2.http.*


interface JsonPlaceHolderApi {
    @GET("posts")
    fun getPosts(
        @Query("userId") userId: Array<Int?>?,
        @Query("_sort") sort: String?,
        @Query("_order") order: String?
    ): Call<List<Post?>?>?

    @GET("posts")
    fun getPosts(@QueryMap parameters: Map<String?, String?>?): Call<List<Post?>?>?

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Call<List<Comment?>?>?

    @GET
    fun getComments(@Url url: String?): Call<List<Comment?>?>?

    @POST("posts")
    fun createPost(@Body post: Post?): Call<Post?>?

    @PUT("posts/{id}")
    fun putPost(
        @Path("id") id: Int,
        @Body post: Post?
    ): Call<Post?>?

    @PATCH("posts/{id}")
    fun patchPost(
        @Path("id") id: Int,
        @Body post: Post?
    ): Call<Post?>?

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Void?>? /*  @FormUrlEncoded
    @POST("posts")
    retrofit2.Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );
    @FormUrlEncoded
    @POST("posts")
    retrofit2.Call<Post> createPost(@FieldMap Map<String, String> fields);*/
}
