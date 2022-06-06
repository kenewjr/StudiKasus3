package and5.abrar.studikasus3.view

import and5.abrar.studikasus3.R
import and5.abrar.studikasus3.adapter.AdapterNews
import and5.abrar.studikasus3.model.RespondNewsItem
import and5.abrar.studikasus3.network.ApiClient
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class NewsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"Selamat Datang Di Indomaret", Toast.LENGTH_LONG).show()
        getdataNews()
    }

    private fun getdataNews(){
        ApiClient.instance.getAllNews()
            .enqueue(object : retrofit2.Callback<List<RespondNewsItem>>
            {
                override fun onResponse(
                    call: Call<List<RespondNewsItem>>,
                    response: Response<List<RespondNewsItem>>
                ) {
                    if (response.isSuccessful){
                        val dataNews = response.body()
                        val adapterNews = AdapterNews(dataNews!!){
                            val pindah = Intent(applicationContext, DetailNews::class.java)
                            pindah.putExtra("detailnews",it)
                            startActivity(pindah)
                        }
                        val lm = LinearLayoutManager(applicationContext,
                            LinearLayoutManager.VERTICAL,false)
                        rvNews.layoutManager=lm
                        rvNews.adapter = adapterNews

                    }else{
                        Toast.makeText(this@NewsList,response.message(), Toast.LENGTH_LONG).show()
                    }


                }

                override fun onFailure(call: Call<List<RespondNewsItem>>, t: Throwable) {
                    Toast.makeText(this@NewsList,t.message, Toast.LENGTH_LONG).show()
                }
            }

            )
    }

}