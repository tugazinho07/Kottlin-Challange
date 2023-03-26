package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoDesligadoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoLigadoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel
import java.text.SimpleDateFormat

    class Carro(override var identificador:String, var motor:Motor) : Veiculo(identificador), Ligavel{

    fun dataFormatada() : String {
        val formato = SimpleDateFormat("dd-MM-yyyy")
        val dataModificada = formato.format(dataDeAquisicao)
        return dataModificada.toString()
    }

        override fun requerCarta(): Boolean {
            return true
        }

    override fun moverPara(x: Int, y: Int) {

        if (posicao.x == x && posicao.y == y) {
            throw AlterarPosicaoException()
        }

        if (motor.estaLigado()){
            posicao.changePosition(x, y)
            desligar()
        }
    }

    override fun ligar() {
        if(estaLigado()){
            throw  VeiculoLigadoException()
        }
        motor.ligar()
    }

    override fun desligar() {
        if(!estaLigado()){
            throw VeiculoDesligadoException()
        }
        motor.desligar()
    }

    override fun estaLigado(): Boolean {
        return motor.estaLigado()
    }

    override fun toString(): String {
        return "Carro | $identificador | ${dataFormatada()} | ${posicao.toString()}"
    }

}