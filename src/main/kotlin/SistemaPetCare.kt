package org.example

import kotlinx.coroutines.delay

class SistemaPetCare {
    private val boxes = List(10) { Box(id = it + 1) }
    private val historial = mutableListOf<Ticket>()

    suspend fun registrarEntrada(paciente: Paciente): Boolean {
        val boxLibre = boxes.firstOrNull { it.estadoActual is EstadoBox.Libre } ?: run {
            println("No hay boxes libres")
            return false
        }

        boxLibre.comenzarTransicion("Registrando paciente")
        println("Registrando a ${paciente.nombre} en box ${boxLibre.id}")
        delay(3000)

        return boxLibre.registrarAtencion(paciente)
    }

    suspend fun registrarSalida(codigo: String, minutos: Long): Ticket? {
        val box = boxes.firstOrNull { b ->
            val estado = b.estadoActual
            estado is EstadoBox.EnAtencion && estado.paciente.codigoAtencion == codigo
        } ?: run {
            println("Paciente $codigo no encontrado")
            return null
        }

        val paciente = (box.estadoActual as EstadoBox.EnAtencion).paciente
        box.comenzarTransicion("Calculando salida")
        println("Procesando salida de ${paciente.nombre} box liberado ${box.id}")
        delay(6500)

        val total = paciente.obtenerMontoFinal(minutos)
        val ticket = Ticket(
            numeroTicket = historial.size + 1,
            paciente = paciente,
            minutosDuracion = minutos,
            totalMonto = total
        )

        historial.add(ticket)
        box.desocupar()
        return ticket
    }

    fun boxesDisponibles(): Int = boxes.count { it.estadoActual is EstadoBox.Libre }

    fun clientesConvenio(): List<Paciente> {
        return historial.map { it.paciente }.filter { it.tipoDuenio == TipoDuenio.CONVENIO }
    }

    fun ingresoPromedio(): Double {
        if (historial.isEmpty()) return 0.0
        return historial.sumOf { it.totalMonto } / historial.size
    }

    fun codigosFinalizados(): List<String> {
        return historial.map { it.paciente.codigoAtencion }
    }

    fun pacienteMayorTiempo(): String {
        val mayor = historial.maxByOrNull { it.minutosDuracion }
        return mayor?.let { "${it.paciente.nombre} (${it.minutosDuracion} min)" } ?: "Ninguno"
    }

    fun reporteCierre() {
        println("\n=== REPORTE DE CIERRE ===")
        historial.forEach { t ->
            println("Ticket #${t.numeroTicket} | ${t.paciente.codigoAtencion} | ${t.paciente.nombre} | Total: $${t.totalMonto.toInt()}")
        }
        println("Recaudación Total: $${historial.sumOf { it.totalMonto }.toInt()}")
        println("Pacientes aterndidos: ${historial.size}")
        println("Boxes Libres: ${boxesDisponibles()}")
    }
}