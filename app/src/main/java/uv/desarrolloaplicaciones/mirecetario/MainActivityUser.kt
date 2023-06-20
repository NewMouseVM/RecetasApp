package uv.desarrolloaplicaciones.mirecetario

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivityUser : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var userName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)



        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val headerView = navigationView.getHeaderView(0)
        val userName: TextView = headerView.findViewById(R.id.nav_header_textView)
        userName.text = intent.getStringExtra("usuario")

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
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_one -> {
                //Agregar intent para vista de las clases
                Toast.makeText(this, "Clases", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_item_two -> {
                //LOGOUT
                val logout = Intent(this, MainActivity3::class.java)
                startActivity(logout)
            }
        }
        Toast.makeText(this, "Salgo", Toast.LENGTH_SHORT).show()

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?){
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}