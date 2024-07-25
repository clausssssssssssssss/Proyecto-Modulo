package claudia.ortega.proyectomodulo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detalles_paciente : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_paciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val PacienteId = intent.getStringExtra("uuid")
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad",0)
        val enfermedad = intent.getStringExtra("enfermedad")
        val habitacion_numero = intent.getIntExtra("habitacion_numero",0)
        val cama_numero = intent.getIntExtra("cama_numero",0)
        val medicamento = intent.getStringExtra("medicamento")
        val horaAplicacion = intent.getStringExtra("horaAplicacion")

        val txtUUIDetalle = findViewById<EditText>(R.id.txtUUIDetalle)
        val txtNombreDetalle = findViewById<EditText>(R.id.txtNombreDetalle)
        val txtApellidoDetalle = findViewById<EditText>(R.id.txtApellidoDetalle)
        val txtEdadDetalle = findViewById<EditText>(R.id.txtEdadDetalle)
        val txtenfermedadDetalle = findViewById<EditText>(R.id.txtenfermedadDetalle)
        val txthabitacion_numeroDetalle = findViewById<EditText>(R.id.txthabitacion_numeroDetalle)
        val txtcama_numeroDetalle = findViewById<EditText>(R.id.txtcama_numeroDetalle)
        val txtmedicamentoDetalle = findViewById<EditText>(R.id.txtmedicamentoDetalle)
        val txthoraAplicacionDetalle = findViewById<EditText>(R.id.txthoraAplicacionDetalle)

        txtUUIDetalle.text = PacienteId
        txtNombreDetalle.text = nombre
        txtApellidoDetalle.text = apellido
        txtEdadDetalle.text = edad
        txtenfermedadDetalle.text = enfermedad
        txthabitacion_numeroDetalle.text = habitacion_numero
        txtcama_numeroDetalle.text = cama_numero
        txtmedicamentoDetalle.text = medicamento
        txthoraAplicacionDetalle.text = horaAplicacion

        }
}