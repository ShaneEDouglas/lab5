package com.example.lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers



class MainActivity : AppCompatActivity() {
    var petImageURL = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageview = findViewById<ImageView>(R.id.petimg)
       val petbtn = findViewById<Button>(R.id.Chooseimg)


        petbtn.setOnClickListener { view ->
            getDogImageurl()
            Log.d("petImageURL", "pet image URL set")
        }

        getNextImage(petbtn,imageview)
    }


    private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            getDogImageurl()

            Glide.with(this)
                . load(petImageURL)
                .fitCenter()
                .into(imageView)
        }
    }


    private fun getDogImageurl(){
        val client =  AsyncHttpClient()
        client["https://dog.ceo/api/breeds/image/random",object: JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Dog Error","errorResponse")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.d("Dog","successful$json")
                if (json != null) {
                    petImageURL = json.jsonObject.getString("message")
                }
            }
        }]
    }
}