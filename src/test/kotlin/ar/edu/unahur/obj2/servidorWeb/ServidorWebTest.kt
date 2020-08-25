package ar.edu.unahur.obj2.servidorWeb

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.lang.ArithmeticException
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
//  Los servidores que modelemos van a aceptar solamente el protocolo HTTP.
//  Si el protocolo de la URL es distinto a "http" hay que devolver código de respuesta 501
//  (servicio no implementado) y body vacío.

  describe("Un servidor web") {
    it("devuelve 501 si recibe un pedido que no es HTTP") {
      val servidor = ServidorWeb()
      val respuesta = servidor.realizarPedido("207.46.13.5", "https://pepito.com.ar/hola.txt", LocalDateTime.now())
      respuesta.codigo.shouldBe(501)
      respuesta.body.shouldBe("")
    }
  }
})
