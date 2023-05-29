package uv.desarrolloaplicaciones.mirecetario

import AdminSQLite
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nueva_receta.*
import uv.desarrolloaplicaciones.mirecetario.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClass()
    }

    private fun initClass() {
        binding.btnRegister.setOnClickListener{
            val admin = AdminSQLite (this, "administracion", null, 2)
            val bd = admin.writableDatabase
            val txtUser = binding.txtUser.text.toString().lowercase()
            val txtPass = binding.txtPass.text.toString().lowercase()
            val selectedOption : Int = binding.groupRol.checkedRadioButtonId
            if(txtUser.isEmpty() || txtPass.isEmpty() || selectedOption == -1){
                Toast.makeText(applicationContext,"No puede dejar ningun espacio en blanco, por favor rellene la informacion.",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val fila : Cursor = bd.rawQuery("SELECT username, password FROM usuarios WHERE username = '${txtUser.lowercase()}'", null)
            if(fila.count == 0){
                val radioButton : RadioButton = findViewById(selectedOption)
                val registro = ContentValues()
                registro.put("username", txtUser.lowercase())
                registro.put("password", txtPass.lowercase())
                registro.put("rol", radioButton.text.toString().lowercase())
                bd.insert("usuarios", null, registro)
                bd.close()
                Toast.makeText(applicationContext,"Registro de usuario exitoso! ",
                    Toast.LENGTH_LONG).show()
            }else{
                if(fila.moveToFirst()) {
                    Toast.makeText(
                        applicationContext,
                        "Ya existe un usuario con ese mismo username, por favor cambielo.",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
            }
        }
    }
}