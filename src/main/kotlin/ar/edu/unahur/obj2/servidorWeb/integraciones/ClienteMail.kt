package ar.edu.unahur.obj2.servidorWeb.integraciones

/*
👀 ¡¡ATENCIÓN!!
El código de este archivo *funciona* tal cual está y no debe realizarse ninguna modificación.
Lo incluimos en el proyecto únicamente con fines didácticos, para quienes quieran ver cómo
está hecho. El ejercicio se tiene que resolver sin alterar para nada este archivo.
 */

interface ClienteMail {
  fun enviar(destinatario: String, asunto: String, cuerpo: String)
}
