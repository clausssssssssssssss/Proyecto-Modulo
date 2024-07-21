package claudia.ortega.proyectomodulo

import Modelo.ClaseConexion
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class agregarpacientes : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregarpacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtNombre = findViewById<EditText>(R.id.txtNombreP)
        val txtApellidos = findViewById<EditText>(R.id.txtApellidoP)
        val txtEdad = findViewById<EditText>(R.id.txtEdadP)
        val txtEnfermedad = findViewById<EditText>(R.id.txtEnfermedadP)
        val txtHabitacion = findViewById<EditText>(R.id.txtNHabitacionP)
        val txtCama = findViewById<EditText>(R.id.txtNCamaP)
        val txtMedicamentos = findViewById<EditText>(R.id.txtNMedicamentosP)
        val txtAplicacion = findViewById<EditText>(R.id.txtHoraMedicamentoP)
        val btnagregarpacientes = findViewById<Button>(R.id.btnAgregar)

        btnagregarpacientes.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
               val objconexion = ClaseConexion().cadenaConexion()
                val addPaciente = objconexion?.prepareStatement("INSERT INTO Pacientes (nombre, apellido, edad, enfermedad, habitacion_numero, cama_numero, medicamento, horaAplicacion) values(?,?,?,?,?,?,?,?,?)")!!
                addPaciente.setString(1, UUID.randomUUID().toString())
                addPaciente.setString(2, txtNombre.text.toString())
                addPaciente.setString(3, txtApellidos.text.toString())
                addPaciente.setString(4, txtEdad.text.toString().toInt().toString())
                addPaciente.setString(5, txtHabitacion.text.toString().toInt().toString())
                addPaciente.setString(6, txtCama.text.toString())
                addPaciente.setString(7, txtMedicamentos.text.toString())
                addPaciente.setString(8, txtAplicacion.text.toString())
                addPaciente.setString(9, txtEnfermedad.text.toString())
                addPaciente.executeUpdate()

            }
        }
    }
}