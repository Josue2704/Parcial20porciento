package josue.hernandez.parcial20porciento

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var permissionStatus: TextView

    // Registradores de permisos
    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            permissionStatus.text = "Permiso de Cámara concedido."
        } else {
            permissionStatus.text = "Permiso de Cámara denegado."
        }
    }

    private val requestLocationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            permissionStatus.text = "Permiso de Ubicación concedido."
        } else {
            permissionStatus.text = "Permiso de Ubicación denegado."
        }
    }

    private val requestStoragePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            permissionStatus.text = "Permiso de Almacenamiento concedido."
        } else {
            permissionStatus.text = "Permiso de Almacenamiento denegado."
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajuste de los insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar los botones y el TextView
        val buttonCamera = findViewById<Button>(R.id.button_camera)
        val buttonLocation = findViewById<Button>(R.id.button_location)
        val buttonStorage = findViewById<Button>(R.id.button_storage)
        permissionStatus = findViewById(R.id.permission_status)

        // Solicitar permiso de cámara
        buttonCamera.setOnClickListener {
            requestPermission(Manifest.permission.CAMERA, "Cámara", requestCameraPermissionLauncher)
        }

        // Solicitar permiso de ubicación
        buttonLocation.setOnClickListener {
            requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, "Ubicación", requestLocationPermissionLauncher)
        }

        // Solicitar permiso de almacenamiento o multimedia según la versión de Android
        buttonStorage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // Para Android 13+ solicitar permisos específicos para multimedia
                requestPermission(Manifest.permission.READ_MEDIA_IMAGES, "Imágenes", requestStoragePermissionLauncher)
                requestPermission(Manifest.permission.READ_MEDIA_VIDEO, "Videos", requestStoragePermissionLauncher)
                requestPermission(Manifest.permission.READ_MEDIA_AUDIO, "Audio", requestStoragePermissionLauncher)
            } else {
                // Para versiones anteriores a Android 13, solicitar READ_EXTERNAL_STORAGE
                requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, "Almacenamiento", requestStoragePermissionLauncher)
            }
        }
    }

    private fun requestPermission(permission: String, permissionName: String, launcher: androidx.activity.result.ActivityResultLauncher<String>) {
        when {
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                // Permiso ya concedido
                permissionStatus.text = "Permiso de $permissionName ya concedido."
            }
            shouldShowRequestPermissionRationale(permission) -> {
                // Muestra una explicación al usuario si es necesario
                permissionStatus.text = "Permiso de $permissionName denegado previamente. Por favor habilítalo en configuración."
            }
            else -> {
                // Solicitar el permiso
                launcher.launch(permission)
            }
        }
    }
}
