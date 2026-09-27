package org.example

class Box(
    val id: Int
) {
    var estadoActual: EstadoBox = EstadoBox.Libre
        private set
    fun comenzarTransicion(descripcion: String): Boolean {
        return when (estadoActual) {
            EstadoBox.Libre, is EstadoBox.EnAtencion -> {
                estadoActual = EstadoBox.EnProceso(descripcion)
                true
            }
            is EstadoBox.EnProceso, is EstadoBox.FueraDeServicio -> false
        }
    }
    fun registrarAtencion(paciente: Paciente): Boolean {
        if (estadoActual is EstadoBox.EnProceso) {
            estadoActual = EstadoBox.EnAtencion(paciente)
            return true
        }
        return false
    }
    fun desocupar(): Boolean {
        if (estadoActual is EstadoBox.EnProceso) {
            estadoActual = EstadoBox.Libre
            return true
        }
        return false
    }
    fun inhabilitar(motivo: String): Boolean {
        if (estadoActual is EstadoBox.Libre) {
            estadoActual = EstadoBox.FueraDeServicio(detalle = motivo)
            return true
        }
        return false
    }
    fun imprimirDetalle() {
        when (val estado = estadoActual) {
            EstadoBox.Libre -> {
                println("Box #$id: Disponible para atención")
            }
            is EstadoBox.EnAtencion -> {
                println("Box #$id: Atendiendo a ${estado.paciente.nombre} (Código: ${estado.paciente.codigoAtencion})")
            }
            is EstadoBox.EnProceso -> {
                println("Box #$id: En preparación (${estado.detalle})")
            }
            is EstadoBox.FueraDeServicio -> {
                println("Box #$id: Inhabilitado (${estado.detalle})")
            }
        }
    }
}