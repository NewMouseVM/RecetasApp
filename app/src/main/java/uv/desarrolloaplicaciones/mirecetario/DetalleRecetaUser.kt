package uv.desarrolloaplicaciones.mirecetario

import AdminSQLite
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detalles_receta.*
import java.lang.Exception
import java.lang.NumberFormatException

class DetalleRecetaUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_receta_user)
        val etReceta = findViewById <TextView> (R.id.etReceta)
        val etIngredientes = findViewById <TextView> (R.id.etIngredientes)
        val etInstrucciones = findViewById <TextView> (R.id.etPreparacion)
        val imgReceta = findViewById <ImageView> (R.id.imgReceta)
        val txtPorciones = findViewById <TextView> (R.id.txtPorciones)
        val btnCalcularPorciones = findViewById <Button> (R.id.btnCalcularPorciones)
        val txtCalcularPorciones = findViewById <EditText> (R.id.txtCalcularPorciones)
        val txtURLImagen = findViewById<TextView>(R.id.txtURLimagen)
        val bundle = intent.extras
        val tituloReceta = bundle?.getString("receta")
        var codigo = 0
        val lstSitios = findViewById<ListView>(R.id.lstSitios)

        val sitiosInternet = arrayOf("Youtube")
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sitiosInternet)

        lstSitios.adapter = adaptador1
        lstSitios.setOnItemClickListener { adapterView, view, i, l ->

            when (sitiosInternet[i]){
                "Youtube" -> { Toast.makeText(this, "${sitiosInternet[i]}", Toast.LENGTH_SHORT).show()
                    val intento1 = Intent(this, Navegacion::class.java)
                    val busquedaYT = "www.youtube.com/results?search_query="+"${etReceta.text.toString().replace(" ", "+")}"
                    intento1.putExtra("direccion", busquedaYT) //pasar parámetros a otro activity
                    startActivity(intento1)}
            }


        }

        etReceta.setText(tituloReceta.toString().replaceFirstChar {
            it.uppercase()
        })

        val admin = AdminSQLite(this, "administracion", null, 2)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("SELECT porciones, ingredientes, instrucciones, sitios, imagen, codigo FROM recetas WHERE nombre = '${etReceta.text.toString().lowercase()}'", null)

        if (fila.moveToFirst()) {
            txtPorciones.text = fila.getString(0).toString()
            etIngredientes.text = fila.getString(1).replaceFirstChar {
                it.uppercase()
            }
            etInstrucciones.text = (fila.getString(2).replaceFirstChar {
                it.uppercase()
            })
            try {
                Glide.with(this)
                    .load(fila.getString(4))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(imgReceta)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            txtURLImagen.text = (fila.getString(4).replaceFirstChar {
                it.uppercase()
            })

            codigo = fila.getString(5).toInt()

            btnCalcularPorciones.setOnClickListener {
                try {
                    if (txtPorciones.text.toString().toFloat() > txtCalcularPorciones.text.toString().toFloat()) {
                        val cantidadIngredientes = etIngredientes.text.toString()
                        val arrayIngredientes = cantidadIngredientes.split(" ").toMutableList()
                        for(element in arrayIngredientes){
                            val indice = arrayIngredientes.indexOf(element)
                            if ( element.toFloatOrNull() != null){
                                arrayIngredientes[indice] = arrayIngredientes[indice].replace(element, (element.toFloat()/(txtPorciones.text.toString().toFloat() / txtCalcularPorciones.text.toString().toFloat() )).toString())
                            }
                        }
                        etIngredientes.text = arrayIngredientes.joinToString().replace(",", "")
                        txtPorciones.text = txtCalcularPorciones.text.toString()
                    }

                    if (txtPorciones.text.toString().toFloat() < txtCalcularPorciones.text.toString().toFloat()) {
                        val cantidadIngredientes = etIngredientes.text.toString()
                        val arrayIngredientes = cantidadIngredientes.split(" ").toMutableList()
                        for(element in arrayIngredientes){
                            val indice = arrayIngredientes.indexOf(element)
                            if ( element.toFloatOrNull() != null){
                                arrayIngredientes[indice] = arrayIngredientes[indice].replace(element, (element.toFloat()*(txtCalcularPorciones.text.toString().toFloat() / txtPorciones.text.toString().toFloat() )).toString())
                            }
                        }
                        etIngredientes.setText(arrayIngredientes.joinToString().replace(",", ""))
                        txtPorciones.setText(txtCalcularPorciones.text.toString())
                    }
                }catch (numException : NumberFormatException){
                    numException.printStackTrace()
                }

            }

            btnSalirDetalles.setOnClickListener{
                finish()
            }
        }
    }
}