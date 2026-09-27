package org.example
import java.time.LocalDateTime

class Canino(
    codigoAtencion: String,
    nombre: String,
    especie: String,
    fechaIngreso: LocalDateTime,
    tipoDuenio: TipoDuenio
) : Paciente(codigoAtencion, nombre, especie, fechaIngreso, tipoDuenio) {
    override fun obtenerTarifaBase(minutosAtencion: Long): Double {
        val tarifaPorHora = 12000
        var minutosEfectivos = minutosAtencion.toDouble()

        if (tipoDuenio == TipoDuenio.CONVENIO) {
            minutosEfectivos *= 0.80
        }
        val horas = minutosEfectivos / 60.0
        return horas * tarifaPorHora
    }
}