package com.example.colabriomar

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ImageDisplayActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_display)

        val imageView = findViewById<ImageView>(R.id.capturedImageView)
        val imageBitmap = intent.getParcelableExtra<Bitmap>("imageBitmap")
        imageView.setImageBitmap(imageBitmap)

        val btnNewPhoto = findViewById<Button>(R.id.btnNewPhoto)
        btnNewPhoto.setOnClickListener {
            openCamera()
        }

    }

    // Método para abrir a câmera novamente
    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    // Recebe a imagem capturada e a envia de volta
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // Envia a nova imagem para a tela de exibição
            val intent = Intent(this, ImageDisplayActivity::class.java)
            intent.putExtra("imageBitmap", imageBitmap)
            startActivity(intent)
        }
    }
}