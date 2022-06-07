package and5.abrar.studikasus3.view

import and5.abrar.studikasus3.model.GetDataUserItem
import and5.abrar.studikasus3.model.PostNewUser
import and5.abrar.studikasus3.R
import and5.abrar.studikasus3.network.ApiClient
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register_acrtvty.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@DelicateCoroutinesApi
class RegisterAcrtvty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_acrtvty)
        btnLogRegister.setOnClickListener {
            onBackPressed()
        }
        btnregis.setOnClickListener {
            GlobalScope.launch {
                register()
            }
            overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
            startActivity(Intent(this, LoginActvty::class.java))
        }
    }

    private fun register(){
        val nama = et_nama.text.toString()
        val pass = et_pass.text.toString()
        val user = et_username.text.toString()
        val alamat = et_address.text.toString()
        val umur = et_umur.text.toString().toInt()
        val image =  "http://loremflickr.com/640/480"
        ApiClient.instance.postDataUser(PostNewUser(alamat,image,nama,pass,umur,user))
            .enqueue(object : Callback<GetDataUserItem>{
                override fun onResponse(
                    call: Call<GetDataUserItem>,
                    response: Response<GetDataUserItem>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@RegisterAcrtvty,response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<GetDataUserItem>, t: Throwable) {
                    Toast.makeText(this@RegisterAcrtvty,t.message, Toast.LENGTH_LONG).show()
                }

            })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }
}