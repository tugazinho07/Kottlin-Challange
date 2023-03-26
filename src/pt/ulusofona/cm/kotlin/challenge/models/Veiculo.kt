package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.util.Date

abstract class Veiculo (open var identificador : String) : Movimentavel {
    var posicao = Posicao(0,0)
    abstract var dataDeAquisicao : Date
    fun setDataAquisicao() {
        dataDeAquisicao = Date()
    }
    abstract fun requerCarta() : Boolean
}