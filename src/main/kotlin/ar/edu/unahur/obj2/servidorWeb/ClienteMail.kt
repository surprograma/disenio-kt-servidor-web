package ar.edu.unahur.obj2.servidorWeb

interface ClienteMail {
  fun enviar(destinatario: String, asunto: String, cuerpo: String)
}
