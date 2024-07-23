package claudia.ortega.proyectomodulo

import Modelo.ClaseConexion
import Modelo.tbPacientes
import RecyclerViewHelpers.Adaptador
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.ResultSet
import java.sql.Statement
import java.util.UUID

class agregarpacientes : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregarpacientes)

        val txtNombre = findViewById<EditText>(R.id.txtNombreP)
        val txtApellidos = findViewById<EditText>(R.id.txtApellidoP)
        val txtEdad = findViewById<EditText>(R.id.txtEdadP)
        val txtEnfermedad = findViewById<EditText>(R.id.txtEnfermedadP)
        val txtHabitacion = findViewById<EditText>(R.id.txtNHabitacionP)
        val txtCama = findViewById<EditText>(R.id.txtNCamaP)
        val txtMedicamentos = findViewById<EditText>(R.id.txtNMedicamentosP)
        val txtAplicacion = findViewById<EditText>(R.id.txtHoraMedicamentoP)
        val btnagregarpacientes = findViewById<Button>(R.id.btnAgregar)
        val rcvPaciente = findViewById<RecyclerView>(R.id.rcvPacientes)

        rcvPaciente.layoutManager = LinearLayoutManager(this)

        fun obtenerPacientes(): List<tbPacientes>{

            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("select * from Pacientes")!!

            val listPacientes = mutableListOf<tbPacientes>()

            while (resultSet.next()){
                val uuid = resultSet.getString("uuid")
                val nombre = resultSet.getString("nombre")
                val apellido = resultSet.getString("apellido")
                val edad = resultSet.getInt("edad")
                val enfermedad = resultSet.getString("enfermedad")
                val habitacion_numero = resultSet.getInt("habitacion_numero")
                val cama_numero = resultSet.getInt("cama_numero")
                val medicamento = resultSet.getString("medicamento")
                val horaAplicacion = resultSet.getString("horaAplicacion")

                val valoresJuntos = tbPacientes(uuid, nombre, apellido, edad, enfermedad, habitacion_numero, cama_numero, medicamento, horaAplicacion)

                listPacientes.add(valoresJuntos)
            }
            return listPacientes
        }
        CoroutineScope(Dispatchers.IO).launch {
            val PacientesDB = obtenerPacientes()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(PacientesDB)
                rcvPaciente.adapter = adapter
            }
        }

        btnagregarpacientes.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
               val objconexion = ClaseConexion().cadenaConexion()
                val addPaciente = objconexion?.prepareStatement("INSERT INTO Pacientes (nombre, apellido, edad, enfermedad, habitacion_numero, cama_numero, medicamento, horaAplicacion) values(?,?,?,?,?,?,?,?,?)")!!
                addPaciente.setString(1, UUID.randomUUID().toString())
                addPaciente.setString(2, txtNombre.text.toString())
                addPaciente.setString(3, txtApellidos.text.toString())
                addPaciente.setString(4, txtEdad.text.toString().toInt().toString())
                addPaciente.setString(5, txtHabitacion.text.toString().toInt().toString())
                addPaciente.setString(6, txtCama.text.toString().toInt().toString())
                addPaciente.setString(7, txtMedicamentos.text.toString())
                addPaciente.setString(8, txtAplicacion.text.toString())
                addPaciente.setString(9, txtEnfermedad.text.toString())
                addPaciente.executeUpdate()

                val nuevosPacientes = obtenerPacientes()
                withContext(Dispatchers.Main){
                    (rcvPaciente.adapter as? Adaptador)?.actualizarLista(nuevosPacientes)
                }

            }
        }
    }
}