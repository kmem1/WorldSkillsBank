package com.example.worldskillsbank.data.repository


import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.worldskillsbank.common.State
import com.example.worldskillsbank.domain.model.Valute
import com.example.worldskillsbank.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.xml.parsers.DocumentBuilderFactory

class RepositoryImpl @Inject constructor(private val context: Context) : Repository {

    @ExperimentalCoroutinesApi
    override fun getValuteByCharCode(
        charCode: String,
        date: Date
    ): Flow<State<Valute?>> = callbackFlow {
        var name: String?
        var value: Float?
        val dateString = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)

        val queue = Volley.newRequestQueue(context)
        val url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=$dateString"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val documentBuilderFactory = DocumentBuilderFactory.newInstance()
                val documentBuilder = documentBuilderFactory.newDocumentBuilder()
                val doc = documentBuilder.parse(
                    response.byteInputStream(Charset.forName("windows-1251")))
                val element = doc.documentElement
                element.normalize()

                val nList = doc.getElementsByTagName(VALUTE_TAG)
                for (i in 0..nList.length) {
                    val node = nList.item(i)
                    if (node != null && node.nodeType == Node.ELEMENT_NODE) {
                        val elem = node as Element
                        if (getValueFromElement("CharCode", elem) == charCode) {
                            name = getValueFromElement("Name", elem)
                            value = getValueFromElement("Value", elem)
                                .replace(",", ".").toFloat()

                            val result = if (name == null || value == null)
                                State.success<Valute?>(null)
                            else
                                State.success<Valute?>(Valute(charCode, name!!, value!!))

                            sendBlocking(result)
                            break
                        }
                    }
                }
            }, { sendBlocking(State.failed("Error"))})

        queue.add(stringRequest)
        awaitClose {  }
    }

    private fun getValueFromElement(tag: String, element: Element): String {
        val nodeList = element.getElementsByTagName(tag).item(0).childNodes
        val node = nodeList.item(0) as Node
        return node.nodeValue
    }

    companion object {
        private const val VALUTE_TAG = "Valute"
    }
}