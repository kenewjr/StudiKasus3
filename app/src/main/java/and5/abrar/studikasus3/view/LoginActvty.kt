package and5.abrar.studikasus3.view

import and5.abrar.studikasus3.model.GetDataUserItem
import and5.abrar.studikasus3.R
import and5.abrar.studikasus3.network.ApiClient
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_actvty.*
import kotlinx.coroutines.DelicateCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@DelicateCoroutinesApi
class LoginActvty : AppCompatActivity() {
    lateinit var sf : SharedPreferences
    lateinit var dataUser : List<GetDataUserItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_actvty)
        val dataUser = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
        if (dataUser.contains("username")){
            startActivity(Intent(this, NewsList::class.java))
        }
        btnRegLogin.setOnClickListener {
            startActivity(Intent(this,RegisterAcrtvty::class.java))
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }
        btnLogin.setOnClickListener {
            login()
        }
        sf = this.getSharedPreferences("datauser",Context.MODE_PRIVATE)
    }
    private fun login(){
        ApiClient.instance.allUser()
            .enqueue(object : Callback<List<GetDataUserItem>>{
            override fun onResponse(
                call: Call<List<GetDataUserItem>>,
                response: Response<List<GetDataUserItem>>
            ) {
                val nama = login_nama.text.toString()
                val  password = login_pass.text.toString()

                if (response.isSuccessful){
                    dataUser = response.body()!!
                for(i in dataUser.indices){
                    if (nama == dataUser[i].username && password == dataUser[i].password) {
                        sf = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
                            val sfe = sf.edit()
                            sfe.putString("username", nama).apply()
                        Toast.makeText(this@LoginActvty, "Selamat Datang Di indomaret", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActvty, NewsList::class.java))
                    }
                }
                }else{
                    Toast.makeText(this@LoginActvty, "Data tidak ditemukan", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<GetDataUserItem>>, t: Throwable) {
                Toast.makeText(this@LoginActvty,t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}