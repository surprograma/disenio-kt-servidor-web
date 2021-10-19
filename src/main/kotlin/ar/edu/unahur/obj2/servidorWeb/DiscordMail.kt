package ar.edu.unahur.obj2.servidorWeb

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class DiscordMail(val nombreGrupo: String, webhookId: String, webhookToken: String) : ClienteMail {
  private val JSON = "application/json; charset=utf-8".toMediaType()
  private val urlBase = "https://discord.com/api/webhooks/$webhookId/$webhookToken"
  private val client = OkHttpClient()

  override fun enviar(destinatario: String, asunto: String, cuerpo: String) {
    val request = crearRequest(destinatario, asunto, cuerpo)
    client.newCall(request).execute().use { response ->
      if (!response.isSuccessful) throw IOException("""
        Falló la request a Discord... ¿habrás copiado mal las credenciales?
        Codigo: ${response.code}
        Error: ${response.body!!.string()}
      """.trimIndent())
    }
  }

  private fun crearRequest(destinatario: String, asunto: String, cuerpo: String): Request {
    val json = """ 
      {
        "embeds": [{
          "title": ":e_mail: ¡Nuevo mensaje recibido!",      
          "description": "_Parece que el grupo **${this.nombreGrupo}** está haciendo algunas pruebas..._",
          "fields": [
            { "name": "Destinatario/a", "value": "$destinatario", "inline": true },
            { "name": "Asunto", "value": "$asunto", "inline": true },
            { "name": "Cuerpo", "value": "$cuerpo", "inline": false }
          ]
        }]
      }
    """
    return Request.Builder()
      .url(urlBase)
      .header("content-type", "application/json")
      .post(json.toRequestBody(JSON))
      .build()
  }
}
