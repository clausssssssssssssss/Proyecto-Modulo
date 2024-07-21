package RecyclerViewHelpers

import Modelo.tbPacientes
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import claudia.ortega.proyectomodulo.R

class Adaptador(var Datos: List<tbPacientes>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_paciente, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = Datos[position]
        holder.txtNombreCard.text = item.nombre
        holder.txtNombreCard.text = item.enfermedad

    }


}