package ar.edu.unahur.obj2.servidorWeb

import java.time.LocalDateTime

class ServidorWeb {
  fun realizarPedido(ip: String, url: String, fechaHora: LocalDateTime) =
    Respuesta(codigo = 501, body = "")
}

class Respuesta(val codigo: Int, val body: String)
