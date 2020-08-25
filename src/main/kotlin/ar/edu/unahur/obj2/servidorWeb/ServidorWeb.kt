package ar.edu.unahur.obj2.servidorWeb

import java.time.LocalDateTime

enum class CodigoHttp(val codigo: Int) {
  OK(200),
  NOT_IMPLEMENTED(501),
  NOT_FOUND(404),
}

class ServidorWeb {
  var modulo: Modulo? = null

  fun realizarPedido(ip: String, url: String, fechaHora: LocalDateTime): Respuesta {
    if (!url.startsWith("http:")) {
      return Respuesta(codigo = CodigoHttp.NOT_IMPLEMENTED, body = "", tiempo = 10)
    }

    if (this.modulo!!.puedeTrabajarCon(url)) {
      return Respuesta(CodigoHttp.OK, this.modulo!!.body, this.modulo!!.tiempoRespuesta)
    }

    return Respuesta(codigo = CodigoHttp.NOT_FOUND, body = "", tiempo = 10)
  }

  fun agregarModulo(modulo: Modulo) {
    this.modulo = modulo
  }
}

class Respuesta(val codigo: CodigoHttp, val body: String, val tiempo: Int)
