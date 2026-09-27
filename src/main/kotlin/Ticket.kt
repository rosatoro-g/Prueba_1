package org.example

data class Ticket(
    val numeroTicket: Int,
    val paciente: Paciente,
    val minutosDuracion: Long,
    val totalMonto: Double
)