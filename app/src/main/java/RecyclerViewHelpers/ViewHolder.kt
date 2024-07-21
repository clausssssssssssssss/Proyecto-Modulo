package RecyclerViewHelpers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import claudia.ortega.proyectomodulo.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
      val txtNombreCard = view.findViewById<TextView>(R.id.txtNombreCard)

}