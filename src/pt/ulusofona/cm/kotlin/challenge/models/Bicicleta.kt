package pt.ulusofona.cm.kotlin.challenge.models

import java.text.SimpleDateFormat
import java.util.*

class Bicicleta(identificador:String, override var dataDeAquisicao: Date) : Veiculo(identificador) {
    override fun requerCarta(): Boolean {
        return false
    }

    fun dataFormatada() : String {
        val formato = SimpleDateFormat("dd-MM-yyyy")
        val dataModificada = formato.format(dataDeAquisicao)
        return dataModificada.toString()
    }

    override fun toString(): String {
        return "Bicicleta | $identificador | ${dataFormatada()} | $posicao"
    }

    override fun moverPara(x: Int, y: Int) {
        posicao.changePosition(x,y)
    }

}