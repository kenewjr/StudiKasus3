package and5.abrar.studikasus3.network

import and5.abrar.studikasus3.model.GetDataUserItem
import and5.abrar.studikasus3.model.PostNewUser
import and5.abrar.studikasus3.model.RespondNewsItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("news")
    fun getAllNews(): Call<List<RespondNewsItem>>

    @POST("user")
    fun postDataUser(@Body reqUser: PostNewUser) : Call<GetDataUserItem>

    @GET("user")
    fun allUser(): Call<List<GetDataUserItem>>
}