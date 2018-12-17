package com.example.candi.cuentacalorias

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_platos_datos.view.*


class AdapterDatosTres (val items : ArrayList<Calorias>, val context: Context) : RecyclerView.Adapter<ViewHolder2>(){

    private var sqlite = AdminSQlite(context, "administracion.db", null, 1)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        return ViewHolder2(LayoutInflater.from(context).inflate(R.layout.fragment_platos_datos, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        holder?.tvDatosA.text = items.get(position).alimento
        holder?.tvDatosB.text = items.get(position).caloria.toString()

        holder?.bcv.setOnClickListener {
            var caloria = items.get(position).caloria
            if ( sqlite.actualizaPersona(caloria) ) {
                Toast.makeText(context, "Calorias añadidas", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(context, "Error al añadir calorias", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

class ViewHolder2 (view: View) : RecyclerView.ViewHolder(view) {
    val tvDatosA = view.textViewAlimento
    val tvDatosB = view.textViewCaloria

    val bcv = view.cardView2
}