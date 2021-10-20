package ar.edu.unahur.obj2.servidorWeb.analizadores

import ar.edu.unahur.obj2.servidorWeb.integraciones.ClienteMail
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*

class AnalizadorIPsSospechosasTest : DescribeSpec({
  describe("Analizador IPs sospechosas") {
    // Creamos un mock y lo configuramos para todas las instancias del analizador
    val clienteMailMock = mockk<ClienteMail>()
    AnalizadorIPsSospechosas.clienteMail = clienteMailMock

    // Creamos un analizador
    val analizador = AnalizadorIPsSospechosas()

    // Configuramos qué va a hacer el mock cuando se llame a `enviar` (no va a hacer nada)
    every { clienteMailMock.enviar(any(), any(), any()) } just Runs

    it("envía mail de prueba") {
      analizador.enviarMailDePrueba()

      verify {
        clienteMailMock.enviar(
          "prueba@abcd.com.ar",
          "123 Probando",
          "Hola... sí, hola..."
        )
      }
    }
  }
})
