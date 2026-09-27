package org.example

import java.time.LocalDateTime

class Exotico(
    codigoAtencion: String,
    nombre: String,
    especie: String,
    fechaIngreso: LocalDateTime,
    tipoDuenio: TipoDuenio,
    val esSilvestre: Boolean
) : Paciente(codigoAtencion, nombre, especie, fechaIngreso, tipoDuenio) {

    override fun obtenerTarifaBase(minutosAtencion: Long): Double {
        val tarifaPorHora = 20000
        val horas = minutosAtencion.toDouble() / 60.0
        val costoNormal = horas * tarifaPorHora

        return if (esSilvestre) {
            costoNormal * 1.30
        } else {
            costoNormal
        }
    }
}