package com.example.colabriomar

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IdentificationActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_identification)

        val imageView = findViewById<ImageView>(R.id.capturedImageView)
        val imageBitmap = intent.getParcelableExtra<Bitmap>("imageBitmap")
        imageView.setImageBitmap(imageBitmap)

        // Aplicação de WindowInsets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuração do botão "salvar"
        val salvar = findViewById<Button>(R.id.salvar)
        salvar.setOnClickListener {
            val intent = Intent(this, finalActivity::class.java)
            intent.putExtra("imageBitmap", imageBitmap)
            startActivity(intent)
        }
    }
}
