# Registro de Aprendizaje y Ciclo TDD

## V1.0

### 1. Sesión 1: Entidad de Dominio `Task` y Persistencia

#### Ciclo TDD
* **Rojo:** Se creó la prueba unitaria `newTaskShouldNotBeCompleted`. La ejecución falló (`Compilation Error`) debido a que la clase `Task` aún no existía en el proyecto.
* **Verde:** Se implementó la clase `Task` en el paquete `entities` incluyendo:
    * Anotaciones JPA (`@Entity`, `@Id`, `@GeneratedValue(strategy = GenerationType.IDENTITY)`).
    * Campos `id`, `title` y `completed`, inicializando `completed = false` por defecto en los constructores y atributos.
    * Encapsulamiento con visibilidad `private` y anotaciones Lombok (`@Getter`, `@Setter`, `@AllArgsConstructor`, `@NoArgsConstructor`, `@Builder`).
* **Refactor / Infraestructura:** Se creó la primera migración de Flyway en `src/main/resources/db/migration/V1__create_tasks_table.sql` para mapear la tabla `tasks` con sus restricciones correspondientes (`NOT NULL`, `PRIMARY KEY`, `DEFAULT FALSE`).

---

### V2.0

## 2. Sesión 2: Endpoints Web en `TaskControllerV1`

### Ciclo TDD: Búsqueda y Creación de Tareas
* **Rojo:** Se escribió el test de integración `getsExistingTaskById` utilizando `MockMvc **Error obtenido:** `Status expected:<201> but was:<404>`.
    * **Causa del fallo:** La petición `POST /v1/tasks` devolvió un error HTTP `404` debido a que no existía el mapeo del controlador ni el endpoint de creación/búsqueda por ID (`GET /v1/tasks/{id}`).
* **Análisis de "Falso Verde":** El test `missingTaskReturns404` devolvió un código `404` antes de la implementación, pero no por lógica de negocio, sino porque Spring Boot captura las rutas no mapeadas por defecto.
* **Verde:** Se añadió la implementación en `TaskControllerV1`:
    * Mapeo `@PostMapping` para crear tareas y almacenarlas en memoria, devolviendo el estado HTTP `201 Created`.
    * Mapeo `@GetMapping("/{id}")` que busca la tarea por ID en la colección y retorna `ResponseEntity.ok(task)` si existe, o `ResponseEntity.notFound().build()` si el ID no se encuentra.

---
