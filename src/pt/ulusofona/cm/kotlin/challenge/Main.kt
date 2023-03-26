package pt.ulusofona.cm.kotlin.challenge

import pt.ulusofona.cm.kotlin.challenge.models.Carro
import pt.ulusofona.cm.kotlin.challenge.models.Motor


fun main() {
    // aqui escreves o código do programa
    val car = Carro("Fusca", Motor(550,400))
    car.moverPara(120,500)
    println(car)
}