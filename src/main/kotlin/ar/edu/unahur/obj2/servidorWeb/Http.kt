package ar.edu.unahur.obj2.servidorWeb

import java.time.LocalDateTime

// Para no tener los códigos "tirados por ahí", usamos un enum que le da el nombre que corresponde a cada código
// La idea de las clases enumeradas es usar directamente sus objetos: CodigoHTTP.OK, CodigoHTTP.NOT_IMPLEMENTED, etc
enum class CodigoHttp(val codigo: Int) {
  OK(200),
  NOT_IMPLEMENTED(501),
  NOT_FOUND(404),
}

// Usamos data class para que las comparaciones en los tests sean por valor y no por referencia
data class PedidoHttp(val ip: String, val url: String, val fechaHora: LocalDateTime)
data class RespuestaHttp(val codigo: CodigoHttp, val body: String, val tiempo: Int, val pedido: PedidoHttp)
