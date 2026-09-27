package org.example

sealed class EstadoBox {
    object Libre : EstadoBox() {
        val descripcion = "Disponible para atención"
    }
    data class EnAtencion(
        val paciente: Paciente
    ) : EstadoBox()
    data class EnProceso(
        val detalle: String
    ) : EstadoBox()
    data class FueraDeServicio(
        val detalle: String
    ) : EstadoBox()
}