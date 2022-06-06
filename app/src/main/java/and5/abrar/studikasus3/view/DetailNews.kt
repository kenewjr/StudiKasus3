package and5.abrar.studikasus3.view

import and5.abrar.studikasus3.R
import and5.abrar.studikasus3.model.RespondNewsItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_news.*

class DetailNews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        Toast.makeText(this,"Halo kamu sedang berada di Detail news", Toast.LENGTH_SHORT).show()
        val detailnews = intent.getParcelableExtra("detailnews") as RespondNewsItem?
        tvJudul.text = detailnews?.title
        tvsutradara.text = detailnews?.author
        tvtglFilm.text = detailnews?.createdAt
        tvdesc.text = detailnews?.description
        Glide.with(this).load(detailnews?.image).into(imgdetail)
    }
}