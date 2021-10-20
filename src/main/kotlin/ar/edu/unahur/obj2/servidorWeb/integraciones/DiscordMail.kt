package ar.edu.unahur.obj2.servidorWeb.integraciones

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

/*
👀 ¡¡ATENCIÓN!!
El código de este archivo *funciona* tal cual está y no debe realizarse ninguna modificación.
Lo incluimos en el proyecto únicamente con fines didácticos, para quienes quieran ver cómo
está hecho. El ejercicio se tiene que resolver sin alterar para nada este archivo.
 */

private data class DiscordMessage(val embeds: List<DiscordEmbed>)
private data class DiscordEmbed(val title: String, val description: String, val fields: List<DiscordEmbedField>)
private data class DiscordEmbedField(val name: String, val value: String, val inline: Boolean = false)

class DiscordMail(private val nombreGrupo: String, webhookId: String, webhookToken: String) : ClienteMail {
  private val JSON = "application/json; charset=utf-8".toMediaType()
  private val urlBase = "https://discord.com/api/webhooks/$webhookId/$webhookToken"
  private val client = OkHttpClient()
  private val jsonAdapter =
    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter(DiscordMessage::class.java)

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
    val message = DiscordMessage(listOf(
      DiscordEmbed(
        title = ":e_mail: ¡Nuevo mensaje recibido!",
        description = "_Parece que el grupo **${this.nombreGrupo}** está haciendo algunas pruebas..._", 
        fields = listOf(
          DiscordEmbedField(name = "Destinatario/a", value = destinatario, inline = true),
          DiscordEmbedField(name = "Asunto", value = asunto, inline = true),
          DiscordEmbedField(name = "Cuerpo", value = cuerpo)
        )
      )
    ))

    return Request.Builder()
      .url(urlBase)
      .header("content-type", "application/json")
      .post(jsonAdapter.toJson(message).toRequestBody(JSON))
      .build()
  }
}
