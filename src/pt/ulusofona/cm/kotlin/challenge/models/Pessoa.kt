package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.text.SimpleDateFormat
import java.util.*

class Pessoa(var nome: String, var dataDeNascimento: Date) : Movimentavel {
    val veiculos1 = mutableListOf<Veiculo>()
    val veiculos : MutableList<Veiculo> = mutableListOf()



    var posicao: Posicao = Posicao(0,0)
    lateinit var carta: Carta

    fun comprarVeiculo(veiculo: Veiculo) {
        veiculos.add(veiculo)
    }

    fun pesquisarVeiculo(identificador: String) : Veiculo {
        for(veiculo : Veiculo in veiculos) {
            if(veiculo.identificador.equals(identificador)) {
                return veiculo
            }
        }
        throw VeiculoNaoEncontradoException()
    }

    fun venderVeiculo(identificador: String, comprador : Pessoa) {
        var veiculo : Veiculo = pesquisarVeiculo(identificador)
        veiculo.setDataAquisicao()
        comprador.veiculos.add(veiculo)
        veiculos.remove(veiculo)
    }

    override fun moverPara(x: Int, y: Int) {
        if(posicao.x.equals(x) && posicao.y.equals(y)){
            throw AlterarPosicaoException()
        }

        posicao.changePosition(x,y)
    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        var veiculo : Veiculo = pesquisarVeiculo(identificador)
        if (veiculo.requerCarta()) {
            if (temCarta()){
                veiculo.moverPara(x,y)
                moverPara(x,y)
            } else {
                throw PessoaSemCartaException(nome)
            }
        } else {
            veiculo.moverPara(x,y)
            moverPara(x,y)
        }
    }


    fun temCarta() : Boolean{
        return this.carta != null
    }

    fun tirarCarta(){
        if(calculoDeIdade()){
            throw MenorDeIdadeException(nome)
        }
        this.carta = Carta()
    }

    fun calculoDeIdade(): Boolean{
        val dataAtual = Date().time
        val aniversario = this.dataDeNascimento.time
        val idadeEmMiliSegundos = dataAtual - aniversario
        val idadeEmAnos = idadeEmMiliSegundos / (1000 * 60 * 60 * 24 * 365.25)
        return idadeEmAnos < 18
    }

    fun dataFormatada() : String {
        val formato = SimpleDateFormat("dd-MM-yyyy")
        val dataModificada = formato.format(dataDeNascimento)
        return dataModificada.toString()
    }

    override fun toString(): String {
        return "Pessoa | $nome | ${dataFormatada()} | ${posicao.toString()}"
    }

}