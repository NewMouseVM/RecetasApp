package uv.desarrolloaplicaciones.mirecetario

import AdminSQLite
import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

import uv.desarrolloaplicaciones.mirecetario.databinding.ActivityMain3Binding
import uv.desarrolloaplicaciones.mirecetario.databinding.ActivityMainBinding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //val context = applicationContext
        //context.deleteDatabase("administracion")
        initClass()
    }

    private fun initClass() {
        val txt = binding.txtUser
        binding.buttonLogin.setOnClickListener {
            val txtUser = binding.txtUser.text.toString()
            val txtPass = binding.txtPass.text.toString()
            val admin = AdminSQLite (this, "administracion", null, 2)
            val bd = admin.writableDatabase
            val fila : Cursor = bd.rawQuery("SELECT username, password, rol FROM usuarios WHERE username = '${txtUser.lowercase()}'", null)
            if(fila.moveToFirst()) {
                if (fila.getString(2) == "administrador" && txtUser == fila.getString(0) && txtPass == fila.getString(
                        1
                    )
                ) {
                    // Se pasa a la vista de administrador
                    var intent = Intent(this, MainActivity::class.java)
                    //intent.putExtra(Variables.nombreUsuario, "Bienvenido")
                    startActivity(intent)
                    return@setOnClickListener
                }
                if (fila.getString(2) == "usuario" && txtUser == fila.getString(0) && txtPass == fila.getString(
                        1
                    )
                ) {
                    // Se pasa a la vista de usuario
                    var intent = Intent(this, MainActivityUser::class.java)
                    //intent.putExtra(Variables.estadoUsuario, "Bienvenido")
                    startActivity(intent)
                    return@setOnClickListener
                }
                Toast.makeText(applicationContext,"Password Incorrecta",
                    Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"No se pudo hacer login, por favor verifique sus credenciales.",
                    Toast.LENGTH_LONG).show()
            }
        }
        binding.btnRegis.setOnClickListener{
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


    object Variables {
        const val nombreUsuario = "Saludo"
        const val estadoUsuario = "Usuario Activo"

    }
}