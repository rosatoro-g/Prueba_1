package org.example

import java.time.LocalDateTime

class Felino(
    codigoAtencion: String,
    nombre: String,
    especie: String,
    fechaIngreso: LocalDateTime,
    tipoDuenio: TipoDuenio
) : Paciente(codigoAtencion, nombre, especie, fechaIngreso, tipoDuenio) {
    override fun obtenerTarifaBase(minutosAtencion: Long): Double {
        if (minutosAtencion < 20) {
            return 0.0
        }
        val tarifaPorHora = 9000
        val horas = minutosAtencion.toDouble() / 60.0
        return horas * tarifaPorHora
    }
}