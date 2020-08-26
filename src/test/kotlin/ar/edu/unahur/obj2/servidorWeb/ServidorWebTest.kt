package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
    val servidor = ServidorWeb()
    servidor.agregarModulo(
      Modulo(listOf("txt"), "todo bien", 100)
    )
    servidor.agregarModulo(
      Modulo(listOf("jpg", "gif"), "qué linda foto", 100)
    )

    it("devuelve 501 si recibe un pedido que no es HTTP") {
      val respuesta = servidor.realizarPedido("207.46.13.5", "https://pepito.com.ar/hola.txt", LocalDateTime.now())
      respuesta.codigo.shouldBe(CodigoHttp.NOT_IMPLEMENTED)
      respuesta.body.shouldBe("")
    }

    it("devuelve 200 si algún módulo puede trabajar con el pedido") {
      val respuesta = servidor.realizarPedido("207.46.13.5", "http://pepito.com.ar/hola.txt", LocalDateTime.now())
      respuesta.codigo.shouldBe(CodigoHttp.OK)
      respuesta.body.shouldBe("todo bien")
    }

    it("devuelve 404 si ningún módulo puede trabajar con el pedido") {
      val respuesta = servidor.realizarPedido("207.46.13.5", "http://pepito.com.ar/playa.png", LocalDateTime.now())
      respuesta.codigo.shouldBe(CodigoHttp.NOT_FOUND)
      respuesta.body.shouldBe("")
    }
  }
})
