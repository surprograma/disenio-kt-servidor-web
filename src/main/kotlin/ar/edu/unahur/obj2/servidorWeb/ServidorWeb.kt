package ar.edu.unahur.obj2.servidorWeb

import java.time.LocalDateTime

enum class CodigoHttp(val codigo: Int) {
  OK(200),
  NOT_IMPLEMENTED(501),
  NOT_FOUND(404),
}

class ServidorWeb {
  val modulos = mutableListOf<Modulo>()

  fun realizarPedido(ip: String, url: String, fechaHora: LocalDateTime): Respuesta {
    if (!url.startsWith("http:")) {
      return Respuesta(codigo = CodigoHttp.NOT_IMPLEMENTED, body = "", tiempo = 10)
    }

    if (this.algunModuloSoporta(url)) {
      val moduloSeleccionado = this.modulos.find { it.puedeTrabajarCon(url) }!!
      return Respuesta(CodigoHttp.OK, moduloSeleccionado.body, moduloSeleccionado.tiempoRespuesta)
    }

    return Respuesta(codigo = CodigoHttp.NOT_FOUND, body = "", tiempo = 10)
  }

  fun algunModuloSoporta(url: String) = this.modulos.any { it.puedeTrabajarCon(url) }

  fun agregarModulo(modulo: Modulo) {
    this.modulos.add(modulo)
  }
}

class Respuesta(val codigo: CodigoHttp, val body: String, val tiempo: Int)
