package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Servidor web") {
    describe("responde a un pedido HTTP") {
      it("con código 200 OK") {
        val pedido = PedidoHttp(
          "127.0.0.1",
          "http://surprograma.com/ideas.pdf",
          LocalDateTime.of(2020, 9, 12, 11, 50, 12)
        )
        ServidorWeb.procesar(pedido).shouldBe(
          RespuestaHttp(CodigoHttp.OK, "cualquier cosa", 200, pedido)
        )
      }
    }
  }
})
