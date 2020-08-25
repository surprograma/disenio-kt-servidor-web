package ar.edu.unahur.obj2.servidorWeb

class Modulo(val extensionesSoportadas: List<String>, val body: String, val tiempoRespuesta: Int) {
  fun puedeTrabajarCon(url: String) = extensionesSoportadas.any { ext -> url.endsWith(ext) }
}
