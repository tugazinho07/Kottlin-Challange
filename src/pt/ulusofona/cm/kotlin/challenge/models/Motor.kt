package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoDesligadoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoLigadoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel

class Motor(var cavalos: Int, var cilindrada: Int) : Ligavel {
    var estado: Boolean = false

    override fun ligar() {
        if(estado){
            throw  VeiculoLigadoException()
        }else{
            estado = true
        }
    }


    override fun desligar() {
        if(!estado){
            throw VeiculoDesligadoException()
        }
        estado = false
    }

    override fun estaLigado(): Boolean {
        return estado
    }

    override fun toString(): String {
        return "Motor | $cavalos | $cilindrada"
    }
}