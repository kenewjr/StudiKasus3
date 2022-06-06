package and5.abrar.studikasus3.network

import and5.abrar.studikasus3.model.RespondNewsItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("news")
    fun getAllNews(): Call<List<RespondNewsItem>>
}