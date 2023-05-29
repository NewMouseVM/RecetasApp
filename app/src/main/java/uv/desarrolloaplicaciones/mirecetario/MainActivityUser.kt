package uv.desarrolloaplicaciones.mirecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivityUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user)
        val imgEnsaladas = findViewById<ImageView>(R.id.imgEnsalada)
        val txtEnsaladas = findViewById<TextView>(R.id.txtEnsalada)
        val imgPastas = findViewById<ImageView>(R.id.imgPasta)
        val txtPastas = findViewById<TextView>(R.id.txtPasta)
        val imgSopas = findViewById<ImageView>(R.id.imgPlatos)
        val txtSopas = findViewById<TextView>(R.id.txtPlatos)
        val imgPostres = findViewById<ImageView>(R.id.imgPostres)
        val txtPostres = findViewById<TextView>(R.id.txtPostres)
        imgEnsaladas.setOnClickListener{
            val intento1 = Intent(this, ActivityRecyclerUsuario::class.java)
            intento1.putExtra("titulo", txtEnsaladas.text.toString().lowercase())
            startActivity(intento1)
        }

        imgPastas.setOnClickListener{
            val intento1 = Intent(this, ActivityRecyclerUsuario::class.java)
            intento1.putExtra("titulo", txtPastas.text.toString().lowercase())
            startActivity(intento1)
        }

        imgSopas.setOnClickListener{
            val intento1 = Intent(this, ActivityRecyclerUsuario::class.java)
            intento1.putExtra("titulo", txtSopas.text.toString().lowercase())
            startActivity(intento1)
        }

        imgPostres.setOnClickListener{
            val intento1 = Intent(this, ActivityRecyclerUsuario::class.java)
            intento1.putExtra("titulo", txtPostres.text.toString().lowercase())
            startActivity(intento1)
        }

        txtEnsaladas.setOnClickListener{
            val intento1 = Intent(this, ActivityRecyclerUsuario::class.java)
            intento1.putExtra("titulo", txtEnsaladas.text.toString().lowercase())
            startActivity(intento1)
        }

        txtPastas.setOnClickListener{
            val intento1 = Intent(this, ActivityRecyclerUsuario::class.java)
            intento1.putExtra("titulo", txtPastas.text.toString().lowercase())
            startActivity(intento1)
        }

        txtSopas.setOnClickListener{
            val intento1 = Intent(this, ActivityRecyclerUsuario::class.java)
            intento1.putExtra("titulo", txtSopas.text.toString().lowercase())
            startActivity(intento1)
        }

        txtPostres.setOnClickListener{
            val intento1 = Intent(this, ActivityRecyclerUsuario::class.java)
            intento1.putExtra("titulo", txtPostres.text.toString().lowercase())
            startActivity(intento1)
        }
    }
}