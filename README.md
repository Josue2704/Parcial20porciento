Documentación del Proyecto: Solicitud de Permisos en Android
Esta app te permite pedir permisos de cámara, ubicación, y almacenamiento de forma simple y directa. Cada permiso tiene su propio botón, y el estado (concedido o denegado) se muestra justo debajo.

1. ¿Qué hace esta app?
Cámara: Permite acceder a la cámara para tomar fotos o videos.
Ubicación: Pide acceso a la ubicación precisa del dispositivo.
Almacenamiento: Te deja acceder a archivos multimedia en el almacenamiento del dispositivo (como fotos, videos o música).
El diseño está optimizado para que sea fácil de usar, con botones grandes y claros, además de un cuadro de texto que te muestra el estado de cada permiso.

2. Estructura del Código
MainActivity.kt: Contiene toda la lógica para pedir los permisos y mostrar el estado.
Registro de permisos: Los permisos se registran antes de que la actividad esté en estado RESUMED para evitar errores. Se usan ActivityResultLauncher para manejar cada tipo de permiso.
Botones: Tres botones principales (button_camera, button_location, button_storage) permiten solicitar los permisos de cámara, ubicación, y almacenamiento respectivamente.
TextView: Un cuadro de texto (permission_status) te dice si el permiso fue concedido o denegado.
3. Diseño de la Interfaz
El archivo activity_main.xml tiene un diseño sencillo, con botones de estilo Material Design y un TextView para mostrar el estado de los permisos. Los botones están bien espaciados y tienen íconos asociados (si decides usarlos).

MaterialButton: Botones con un diseño moderno y limpio.
TextView: Cuadro que muestra el estado de los permisos de una manera clara y centrada.
4. Permisos en Diferentes Versiones de Android
En Android 13 (API 33) o superior, los permisos de almacenamiento han cambiado. Para gestionar archivos multimedia se usan permisos específicos como READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, y READ_MEDIA_AUDIO.
Para versiones anteriores, se sigue usando READ_EXTERNAL_STORAGE.
5. Cómo Funciona el Flujo de Permisos
Presiona el botón de permiso (cámara, ubicación, o almacenamiento).
La app verifica si el permiso ya fue concedido.
Si no fue concedido, se muestra una explicación (si es necesario), o se solicita el permiso directamente.
El resultado (permitido o denegado) se muestra en el TextView de la pantalla.
