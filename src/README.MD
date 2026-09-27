# PetCare — Sistema de Gestión de Boxes Veterinarios

Este proyecto es una aplicación de consola desarrollada en **Kotlin** como parte de la evaluación del módulo **DSY1105 (Desarrollo de Aplicaciones Móviles)**. Corresponde al núcleo de lógica de negocio para la administración de boxes en una clínica veterinaria.

---

## Características Principales

- **Gestión de Pacientes**: Modelado con Programación Orientada a Objetos para tres categorías (`Canino`, `Felino` y `Exotico`).
- **Validaciones de Negocio**: Validación de código de atención mediante expresiones regulares (Formato: `AA00AA`) y validaciones de datos comunes.
- **Manejo de Tarifas y Descuentos**:
    - **Canino**: $12.000/hr (Descuento del 20% en tiempo para clientes con convenio).
    - **Felino**: $9.000/hr (Cobro $0 si la atención dura menos de 20 min).
    - **Exótico**: $20.000/hr (Recargo del 30% si es animal silvestre).
    - **MUNICIPAL**: 50% de descuento sobre el monto final con IVA (19%).
- **Gestión Asíncrona de Boxes**: Simulación de comunicación con sensores mediante **Corrutinas** (`delay` de 3s para entrada y 6.5s para salida).
- **Manejo de Estados**: Definición de estados mediante `sealed class` (`Libre`, `EnAtencion`, `EnProceso` y `FueraDeServicio`).
- **Consultas de Negocio y Cierre**: Generación de métricas de recaudación, cálculo de ingresos promedio y conteo de boxes disponibles.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Kotlin 1.9+
- **Librería Asíncrona**: `kotlinx-coroutines-core`
- **IDE**: IntelliJ IDEA
- **Gestor de Dependencias**: Gradle (Kotlin DSL)

---

## Estructura del Proyecto

```text
src/main/kotlin/org/example/
├── Box.kt                  # Manejo de estado y asignación de boxes
├── Canino.kt               # Subclase con tarifas de caninos
├── EstadoBox.kt            # Sealed class para los 4 estados
├── Exotico.kt              # Subclase con tarifa y recargo silvestre
├── Felino.kt               # Subclase con regla de < 20 min
├── Main.kt                 # Punto de entrada ejecutable (runBlocking)
├── Paciente.kt             # Clase base con validación Regex y cálculo de IVA
├── SistemaPetCare.kt       # Servicio principal de la lógica de negocio
├── Ticket.kt               # Data class para emisión de comprobantes
└── TipoDuenio.kt           # Enum (PARTICULAR, CONVENIO, MUNICIPAL)