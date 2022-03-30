package com.binar.permissions

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var requestPermissionButton: Button
    private lateinit var loadImageButton: Button //initialize
    private lateinit var imageView: ImageView
    private lateinit var snackBarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissionButton = findViewById(R.id.request_permission_button)
        loadImageButton = findViewById(R.id.load_image_button) //casting
        imageView = findViewById(R.id.imageView)
        snackBarButton = findViewById(R.id.snackbar_button)

        loadImageButton.setOnClickListener {
            loadImage()
        }

        requestPermissionButton.setOnClickListener {
            requestPermissionLocation()
        }

        snackBarButton.setOnClickListener {
//            Snackbar.make(it,"This is long snackbar", Snackbar.LENGTH_LONG).show() //normal snackbar
//
            //Snackbar with Toast
//            Snackbar.make(it,"This is long snackbar with action", Snackbar.LENGTH_LONG).setAction("Text Action") {
//                Toast.makeText(this,"Toast from Action Snackbar",Toast.LENGTH_LONG).show()
//                }.show()

//            //Snackbar Dismiss
//            val snackBar = Snackbar.make(it,"This Snackbar is Indefinite. It won't go if you don't click dismiss", Snackbar.LENGTH_INDEFINITE)
//            snackBar.setAction("Dismiss"){
//                snackBar.dismiss()
//            }
//            snackBar.show()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            201 -> {
                if(grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Allowed has been picked",Toast.LENGTH_SHORT).show()
                    getLongLat()
                } else {
                    Toast.makeText(this,"Denied has been chosen", Toast.LENGTH_SHORT).show()
                }
            }

            else->{
                Toast.makeText(this,"Not Request that sent",Toast.LENGTH_SHORT).show()
            }
        }
    }

        private fun requestLocationPermission() {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 201)
        }

        private fun requestPermissionLocation() {
            val permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Location Accepted", Toast.LENGTH_SHORT).show()
                getLongLat()
            } else {
                Toast.makeText(this, "Permission Location Denied", Toast.LENGTH_SHORT).show()
                requestLocationPermission()
            }
        }

        @SuppressLint("MissingPermission")
        fun getLongLat(){
            val locationManager =
                applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            val location: Location? = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            Toast.makeText(this,"Lat: ${location?.latitude} Long: ${location?.latitude}", Toast.LENGTH_LONG).show()

        }

        private fun loadImage() {
            Glide.with(this)
                .load("https://img.icons8.com/plasticine/2x/flower.png")
                .circleCrop()
                .into(imageView)
        }
}