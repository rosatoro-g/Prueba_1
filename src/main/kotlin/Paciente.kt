package org.example

import java.time.LocalDateTime

open class Paciente(
    val codigoAtencion: String,
    val nombre: String,
    val especie: String,
    val fechaIngreso: LocalDateTime,
    val tipoDuenio: TipoDuenio
) {
    init {
        require(codigoAtencion.matches(Regex("^[A-Za-z]{2}\\d{2}[A-Za-z]{2}$"))) {
            "Codigo de atencion invalido:$codigoAtencion. Rechazado"
        }
    }
    protected open fun obtenerTarifaBase(minutosAtencion: Long): Double {
        return 0.0
    }
    fun obtenerMontoFinal(minutosAtencion: Long): Double {
        val subtotalBase = obtenerTarifaBase(minutosAtencion)
        val valorConIVA = subtotalBase * 1.19

        return if (tipoDuenio == TipoDuenio.MUNICIPAL) {
            valorConIVA * 0.50
        } else {
            valorConIVA
        }
    }
}