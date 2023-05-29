package uv.desarrolloaplicaciones.mirecetario

import AdminSQLite
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uv.desarrolloaplicaciones.mirecetario.databinding.ActivityRecyclerUsuarioBinding

class ActivityRecyclerUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_usuario)
        val bundle = intent.extras
        val categoriaReceta = bundle?.getString("titulo")
        val rvRecetas = findViewById<RecyclerView>(R.id.rvDatos)
        rvRecetas.setOnClickListener {
            val intento1 = Intent(this, DetalleRecetaUser::class.java)
            startActivity(intento1)
        }



        binding = ActivityRecyclerUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val admin = AdminSQLite (this, "administracion", null, 2)
        val db = admin.readableDatabase
        val cursor : Cursor = db.rawQuery("SELECT nombre, imagen FROM recetas WHERE categoria = '${categoriaReceta}'", null)

        val adaptador = RecyclerViewAdapterRecetasUsuario()
        adaptador.RecyclerViewAdapterRecetasUsuario(this, cursor)

        binding.rvDatos.setHasFixedSize(true)
        binding.rvDatos.layoutManager = LinearLayoutManager(this)
        binding.rvDatos.adapter = adaptador

        rvRecetas.setOnClickListener {
            val intento1 = Intent(this, DetalleRecetaUser::class.java)
            startActivity(intento1)
        }
    }
}