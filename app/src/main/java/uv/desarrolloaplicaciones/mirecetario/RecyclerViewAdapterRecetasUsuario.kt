package uv.desarrolloaplicaciones.mirecetario

import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uv.desarrolloaplicaciones.mirecetario.databinding.ItemRecyclerviewBinding

class RecyclerViewAdapterRecetasUsuario
    : RecyclerView.Adapter<RecyclerViewAdapterRecetasUsuario.ViewHolder>(){

    lateinit var context : Context
    lateinit var cursor : Cursor

    fun RecyclerViewAdapterRecetasUsuario(context : Context, cursor : Cursor) {
        this.context = context
        this.cursor = cursor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_recyclerview,
            parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)

        holder.txtNombreRV.text = cursor.getString(0)
        Glide.with(holder.imgRecetaRV.getContext())
            .load(cursor.getString(1))
            .into(holder.imgRecetaRV)
    }

    override fun getItemCount(): Int {
        if (cursor == null)
            return 0
        else
            return cursor.count
    }

    inner class ViewHolder: RecyclerView.ViewHolder {

        val txtNombreRV: TextView
        val imgRecetaRV: ImageView

        constructor(view: View) : super(view){
            val bindingItemsRV = ItemRecyclerviewBinding.bind(view)
            txtNombreRV = bindingItemsRV.txtNombreRV
            imgRecetaRV = bindingItemsRV.imgRecetaRV


            txtNombreRV.setOnClickListener {
                val intento1 = Intent(context, DetalleRecetaUser::class.java)
                val nombreReceta = txtNombreRV.text
                intento1.putExtra("receta", nombreReceta)
                context.startActivity(intento1)
            }

            imgRecetaRV.setOnClickListener {
                val intento1 = Intent(context, DetalleRecetaUser::class.java)
                val nombreReceta = txtNombreRV.text
                intento1.putExtra("receta", nombreReceta)
                context.startActivity(intento1)
            }

        }


    }

}