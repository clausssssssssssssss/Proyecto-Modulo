package RecyclerViewHelpers

import Modelo.ClaseConexion
import Modelo.tbPacientes
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import claudia.ortega.proyectomodulo.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Adaptador(private var Datos: List<tbPacientes>) : RecyclerView.Adapter<ViewHolder>() {
    fun actualizarLista(nuevaLista: List<tbPacientes>) {
        Datos = nuevaLista
        notifyDataSetChanged()
    }


    fun actualizarItem(uuid: String, nombre: String, apellido: String, edad: Int, enfermedad: String, habitacion_numero: Int, cama_numero: Int, medicamento: String, horaAplicacion: String) {
        val index = Datos.indexOfFirst { it.uuid == uuid }
        Datos[index].nombre = nombre
        notifyItemChanged(index)
    }

    ///////////////////TODO: Eliminar datos////////////////////////
    fun eliminarRegistroDeBaseDeDatos(nombre: String, posicion: Int) {

        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(posicion)

        GlobalScope.launch(Dispatchers.IO) {

            val claseC = ClaseConexion().cadenaConexion()

            val addProducto =
                claseC?.prepareStatement("delete Pacientes where nombre = ?")!!
            addProducto.setString(1, nombre)
            addProducto.executeUpdate()

            val commit = claseC.prepareStatement("commit")!!
            commit.executeUpdate()
        }
        Datos = listaDatos.toList()
        notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }
    /////////////////TODO: Editar datos////////////////////////////////
    fun actualizarRegistro(uuid: String, nombre: String, apellido: String, edad: Int, enfermedad: String, habitacion_numero: Int, cama_numero: Int, medicamento: String, horaAplicacion: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val claseC = ClaseConexion().cadenaConexion()
            val actualizarPaciente = claseC?.prepareStatement("UPDATE Pacientes SET nombre = ?, apellido = ?, edad = ?, enfermedad = ?, habitacion_numero = ?, cama_numero = ?, medicamento = ?, horaAplicacion = ? WHERE uuid = ?")!!
            actualizarPaciente.setString(1, nombre)
            actualizarPaciente.setString(2, apellido)
            actualizarPaciente.setInt(3, edad)
            actualizarPaciente.setString(4, enfermedad)
            actualizarPaciente.setInt(5, habitacion_numero)
            actualizarPaciente.setInt(6, cama_numero)
            actualizarPaciente.setString(7, medicamento)
            actualizarPaciente.setString(8, horaAplicacion)
            actualizarPaciente.setString(9, uuid)
            actualizarPaciente.executeUpdate()

            val commit = claseC.prepareStatement("commit")!!
            commit.executeUpdate()

            withContext(Dispatchers.Main) {
                actualizarItem(uuid, nombre, apellido, edad, enfermedad, habitacion_numero, cama_numero, medicamento, horaAplicacion)
            }
        }
    }
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_paciente, parent, false)
    return ViewHolder(vista)
}


    override fun getItemCount() = Datos.size

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val item = Datos[position]
                holder.textView.text = item.nombre
                holder.textView.text = item.enfermedad


            //TODO: icono de Borrar
            holder.imgDelete.setOnClickListener {

                val context = holder.itemView.context

                val builder = AlertDialog.Builder(context)
                builder.setTitle("Editar")
                builder.setMessage("¿Quieres editar este elemento?")

                builder.setPositiveButton("Sí") { dialog, which ->
                    eliminarRegistroDeBaseDeDatos(item.nombre, position)
                }

                builder.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
    }
                //TODO: icono de Editar
                holder.imgUpdate.setOnClickListener {
                    val context = holder.itemView.context

                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Editar nombre")

                    val input = EditText(context)
                    input.setHint(item.nombre)
                    builder.setView(input)

                    builder.setPositiveButton("Actualizar") { dialog, which ->
                        actualizarRegistro( item.uuid, item.nombre, item.apellido, item.edad, item.enfermedad, item.habitacion_numero, item.cama_numero, item.medicamento, item.horaAplicacion)
                    }

                    builder.setNegativeButton("Cancelar") { dialog, which ->
                        dialog.dismiss()
                    }

                    val dialog = builder.create()
                    dialog.show()
                }

       }
}
