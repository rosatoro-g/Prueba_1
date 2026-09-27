package org.example

import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

fun main() = runBlocking {
    val sistema = SistemaPetCare()

    val pacientes = listOf(
        Canino("CA12CD", "Max", "Golden Retriever", LocalDateTime.now(), TipoDuenio.CONVENIO),
        Canino("CA99ZA", "Luna", "Labrador", LocalDateTime.now(), TipoDuenio.PARTICULAR),
        Felino("FE22TO", "Misi", "Siames", LocalDateTime.now(), TipoDuenio.PARTICULAR),
        Exotico("EX44RG", "Loro", "Amazonico", LocalDateTime.now(), TipoDuenio.MUNICIPAL, true),
        Exotico("EX77RG", "Iguana", "Verde", LocalDateTime.now(), TipoDuenio.PARTICULAR, false)
    )

    pacientes.forEach { paciente ->
        sistema.registrarEntrada(paciente)
    }

    sistema.registrarSalida("CA12CD", 75)
    sistema.registrarSalida("CA99ZA", 180)
    sistema.registrarSalida("FE22TO", 18)
    sistema.registrarSalida("EX44RG", 120)
    sistema.registrarSalida("EX77RG", 45)

    println("\n=== CONSULTAS ===")

    println("Boxes disponibles: ${sistema.boxesDisponibles()}")

    println("Clientes convenio:")
    sistema.clientesConvenio().forEach { paciente ->
        println("- ${paciente.nombre} (${paciente.codigoAtencion})")
    }

    println("Ingreso promedio: $${sistema.ingresoPromedio().toInt()}")

    println("Codigos finalizados: ${sistema.codigosFinalizados()}")

    println("Paciente con mayor tiempo: ${sistema.pacienteMayorTiempo()}")

    try {
        Canino("123ABC", "Error", "Felino", LocalDateTime.now(), TipoDuenio.PARTICULAR)
    } catch (e: IllegalArgumentException) {
        println("Error controlado: ${e.message}")
    }

    sistema.registrarSalida("75BFB64", 30)

    sistema.reporteCierre()
}