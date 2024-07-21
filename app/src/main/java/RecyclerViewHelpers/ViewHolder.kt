package RecyclerViewHelpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import claudia.ortega.proyectomodulo.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
      val textView: TextView = view.findViewById(R.id.txtNombreCard)
      val imgUpdate: ImageView = view.findViewById(R.id.imgUpdate)
      val imgDelete: ImageView = view.findViewById(R.id.imgDelete)


}