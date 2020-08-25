package ar.edu.unahur.obj2.servidorWeb

import java.time.LocalDateTime

enum class CodigoHttp(val codigo: Int) {
  OK(200),
  NOT_IMPLEMENTED(501),
}

class ServidorWeb {
  fun realizarPedido(ip: String, url: String, fechaHora: LocalDateTime) =
    if (url.startsWith("http:"))
      Respuesta(codigo = CodigoHttp.OK, body = "todo bien")
    else
      Respuesta(codigo = CodigoHttp.NOT_IMPLEMENTED, body = "")
}

class Respuesta(val codigo: CodigoHttp, val body: String)
