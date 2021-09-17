package ar.edu.unahur.obj2.servidorWeb

object ServidorWeb {
  fun procesar(pedido: PedidoHttp) =
    RespuestaHttp(CodigoHttp.OK, "cualquier cosa", 200, pedido)
}
