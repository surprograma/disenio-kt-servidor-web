package ar.edu.unahur.obj2.servidorWeb.analizadores

import ar.edu.unahur.obj2.servidorWeb.integraciones.ClienteMail
import ar.edu.unahur.obj2.servidorWeb.integraciones.DiscordMail

class AnalizadorIPsSospechosas {
  // Lo ponemos en un companion object para que todas las instancias usen el mismo
  companion object {
    var clienteMail: ClienteMail = DiscordMail(
      "prueba",
      "asdf123",
      "xxxx"
    );
  }

  // TODO: este método está solo para mostrar cómo hacer un test con mocks,
  // borrarlo cuando haya métodos de verdad...
  fun enviarMailDePrueba() {
    clienteMail.enviar(
      "prueba@abcd.com.ar",
      "123 Probando",
      "Hola... sí, hola..."
    );
  }
}

