package com.primer.secomputadora.models




class DiagnosticEngine {

    private val symptoms = listOf(
        Symptom(
            id = "no_power",
            title = "No enciende para nada",
            description = "La computadora no muestra signos de vida",
            icon = "power_off",
            severity = SeverityLevel.HIGH
        ),
        Symptom(
            id = "no_display",
            title = "Enciende pero no muestra imagen",
            description = "Se escucha funcionar pero la pantalla está en negro",
            icon = "monitor",
            severity = SeverityLevel.MEDIUM
        ),
        Symptom(
            id = "slow_performance",
            title = "Funciona muy lento",
            description = "Todo tarda mucho en cargar y responder",
            icon = "slow",
            severity = SeverityLevel.LOW
        ),
        Symptom(
            id = "blue_screen",
            title = "Pantallas azules o negras con texto",
            description = "Aparecen pantallas de error",
            icon = "error",
            severity = SeverityLevel.HIGH
        ),
        Symptom(
            id = "strange_sounds",
            title = "Hace ruidos extraños",
            description = "Sonidos que no hacía antes",
            icon = "sound",
            severity = SeverityLevel.MEDIUM
        ),
        // Síntomas adicionales
        Symptom(
            id = "internet_problems",
            title = "No se conecta a internet",
            description = "No puede navegar aunque todo parece estar conectado",
            icon = "wifi_off",
            severity = SeverityLevel.MEDIUM
        ),
        Symptom(
            id = "freezing",
            title = "Se congela o traba",
            description = "Se queda pegada y no responde a nada",
            icon = "freeze",
            severity = SeverityLevel.MEDIUM
        ),
        Symptom(
            id = "overheating",
            title = "Se calienta mucho",
            description = "Está muy caliente al tocarla",
            icon = "temperature",
            severity = SeverityLevel.MEDIUM
        ),
        Symptom(
            id = "programs_not_opening",
            title = "Los programas no abren",
            description = "Hago click pero no pasa nada",
            icon = "app_error",
            severity = SeverityLevel.LOW
        ),
        Symptom(
            id = "random_shutdown",
            title = "Se apaga sola",
            description = "Se apaga sin que yo haga nada",
            icon = "power_off_auto",
            severity = SeverityLevel.HIGH
        )

    )

// Síntomas adicionales que puedes agregar a tu lista existente

    private val additionalSymptoms = listOf(
        Symptom(
            id = "internet_problems",
            title = "No se conecta a internet",
            description = "No puede navegar aunque todo parece estar conectado",
            icon = "wifi_off",
            severity = SeverityLevel.MEDIUM
        ),
        Symptom(
            id = "freezing",
            title = "Se congela o traba",
            description = "Se queda pegada y no responde a nada",
            icon = "freeze",
            severity = SeverityLevel.MEDIUM
        ),
        Symptom(
            id = "overheating",
            title = "Se calienta mucho",
            description = "Está muy caliente al tocarla",
            icon = "temperature",
            severity = SeverityLevel.MEDIUM
        ),
        Symptom(
            id = "programs_not_opening",
            title = "Los programas no abren",
            description = "Hago click pero no pasa nada",
            icon = "app_error",
            severity = SeverityLevel.LOW
        ),
        Symptom(
            id = "random_shutdown",
            title = "Se apaga sola",
            description = "Se apaga sin que yo haga nada",
            icon = "power_off_auto",
            severity = SeverityLevel.HIGH
        )
    )

// Preguntas para los nuevos síntomas

    private fun getInternetProblemsQuestions(): List<Question> = listOf(
        Question(
            id = "wifi_connected",
            text = "¿Aparece el símbolo de WiFi en la pantalla?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Sí, aparece normal",
                "Aparece con una X o signo de exclamación",
                "No aparece nada",
                "No sé dónde verlo"
            )
        ),
        Question(
            id = "other_devices",
            text = "¿Otros dispositivos se conectan bien a internet? (celular, TV, etc.)",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "cable_connection_internet",
            text = "¿Está conectada por cable de red o por WiFi?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Solo WiFi",
                "Cable de red conectado",
                "No estoy seguro/a"
            )
        ),
        Question(
            id = "router_restart",
        text = "¿Has intentado apagar y encender el router/módem?",
        type = QuestionType.YES_NO
    )
    )

    private fun getFreezingQuestions(): List<Question> = listOf(
        Question(
            id = "freeze_frequency",
            text = "¿Con qué frecuencia se congela?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Varias veces al día",
                "Una vez al día",
                "Pocas veces a la semana",
                "Solo ocasionalmente"
            )
        ),
        Question(
            id = "freeze_pattern",
            text = "¿Cuándo se congela generalmente?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Al abrir programas específicos",
                "Después de un rato de uso",
                "Al hacer varias cosas a la vez",
                "En cualquier momento sin patrón"
            )
        ),
        Question(
            id = "mouse_keyboard_response",
            text = "¿Cuando se congela, el mouse y teclado no responden para nada?",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "force_restart",
            text = "¿Tienes que apagar la computadora manteniendo presionado el botón de encendido?",
            type = QuestionType.YES_NO
        ),
        // 🔹 Preguntas nuevas sugeridas

        // Temperatura
        Question(
            id = "overheating",
            text = "¿Has notado que la computadora se calienta mucho o el ventilador suena muy fuerte?",
            type = QuestionType.YES_NO
        ),

        // Actualizaciones
        Question(
            id = "recent_updates",
            text = "¿Tu computadora se actualizó recientemente o instalaste nuevos programas?",
            type = QuestionType.YES_NO
        ),

        // Espacio en disco
        Question(
            id = "low_disk_space",
            text = "¿Tu disco duro tiene poco espacio libre?",
            type = QuestionType.YES_NO
        ),

        // Virus o malware
        Question(
            id = "antivirus_scan",
            text = "¿Has pasado un antivirus recientemente?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Sí, y no encontró nada",
                "Sí, encontró virus y los eliminé",
                "No he pasado antivirus"
            )
        ),

        // Errores recientes
        Question(
            id = "blue_screen",
            text = "¿Alguna vez ha aparecido una pantalla azul con un mensaje de error?",
            type = QuestionType.YES_NO
        ),

        // Fuente de energía
        Question(
            id = "power_source",
            text = "¿Usas laptop con batería o siempre conectada al cargador?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Siempre con batería",
                "Siempre conectada al cargador",
                "Ambas opciones"
            )
        ),

        // Ruido inusual
        Question(
            id = "unusual_noises",
            text = "¿Escuchas ruidos extraños (clics, zumbidos) dentro de la computadora?",
            type = QuestionType.YES_NO
        )
    )

    private fun getOverheatingQuestions(): List<Question> = listOf(
        Question(
            id = "heat_location",
            text = "¿Dónde sientes que se calienta más?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "En la parte de abajo (laptop)",
                "En los lados donde salen aires",
                "En toda la computadora",
                "Solo en ciertas partes"
            )
        ),
        Question(
            id = "heat_timing",
            text = "¿Cuándo se calienta más?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Desde que la enciendo",
                "Después de usarla un rato",
                "Solo cuando hago tareas pesadas",
                "Todo el tiempo que está encendida"
            )
        ),
        Question(
            id = "shutdown_due_heat",
            text = "¿Se ha apagado sola por el calor?",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "ventilation_blocked",
            text = "¿Las salidas de aire están bloqueadas o tienen polvo visible?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Sí, tienen mucho polvo",
                "Un poco de polvo",
                "No, están limpias",
                "No sé dónde están las salidas de aire"
            )
        )
    )

    private fun getProgramsNotOpeningQuestions(): List<Question> = listOf(
        Question(
            id = "all_programs",
            text = "¿Ningún programa abre o solo algunos específicos?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Ningún programa abre",
                "Solo algunos programas específicos",
                "Algunos abren, otros no"
            )
        ),
        Question(
            id = "error_messages",
            text = "¿Aparece algún mensaje de error cuando intentas abrir programas?",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "double_click",
            text = "¿Estás haciendo doble click en los íconos?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Sí, doble click rápido",
                "Solo un click",
                "No estoy seguro/a cómo se hace"
            )
        ),
        Question(
            id = "icon_location",
            text = "¿Los íconos están en el escritorio o en otro lugar?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "En el escritorio",
                "En la barra de tareas (abajo)",
                "En el menú de inicio",
                "No estoy seguro/a"
            )
        )
    )

    private fun getRandomShutdownQuestions(): List<Question> = listOf(
        Question(
            id = "shutdown_warning",
            text = "¿Se apaga de repente o aparece algún mensaje antes?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Se apaga de repente sin avisar",
                "Aparece mensaje de que se va a apagar",
                "La pantalla se pone azul primero",
                "Se reinicia sola"
            )
        ),
        Question(
            id = "activity_during_shutdown",
            text = "¿Qué estabas haciendo cuando se apagó?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Trabajando normalmente",
                "Jugando o viendo videos",
                "No estaba haciendo nada especial",
                "Usando muchos programas a la vez"
            )
        ),
        Question(
            id = "power_supply_check",
            text = "¿La computadora está conectada a un regulador de voltaje o directamente al enchufe?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Directamente al enchufe de la pared",
                "A un regulador o UPS",
                "A una extensión eléctrica",
                "No estoy seguro/a"
            )
        ),
        Question(
            id = "shutdown_pattern",
            text = "¿Hay algún patrón en los apagados?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Siempre después de cierto tiempo de uso",
                "Solo cuando hace calor en el ambiente",
                "Completamente al azar",
                "Solo cuando uso ciertos programas"
            )
        )
    )









    fun diagnose(symptomId: String, answers: Map<String, String>): DiagnosticResult {
        return when (symptomId) {
            "no_power" -> diagnoseNoPower(answers)
            "no_display" -> diagnoseNoDisplay(answers)
            "slow_performance" -> diagnoseSlowPerformance(answers)
            "blue_screen" -> diagnoseBlueScreen(answers)
            "strange_sounds" -> diagnoseStrangeSounds(answers)
            "internet_problems" -> diagnoseInternetProblems(answers)
            "freezing" -> diagnoseFreezing(answers)
            "overheating" -> diagnoseOverheating(answers)
            "programs_not_opening" -> diagnoseProgramsNotOpening(answers)
            "random_shutdown" -> diagnoseRandomShutdown(answers)
            else -> DiagnosticResult(
                title = "Diagnóstico no disponible",
                description = "Por favor contacta con un técnico",
                severity = SeverityLevel.MEDIUM,
                solutions = emptyList(),
                probability = 0
            )
        }
    }



    fun getSymptoms(): List<Symptom> = symptoms

    fun getQuestionsForSymptom(symptomId: String): List<Question> {
        return when (symptomId) {
            "no_power" -> getNoPowerQuestions()
            "no_display" -> getNoDisplayQuestions()
            "slow_performance" -> getSlowPerformanceQuestions()
            "blue_screen" -> getBlueScreenQuestions()
            "strange_sounds" -> getStrangeSoundsQuestions()
            "internet_problems" -> getInternetProblemsQuestions()
            "freezing" -> getFreezingQuestions()
            "overheating" -> getOverheatingQuestions()
            "programs_not_opening" -> getProgramsNotOpeningQuestions()
            "random_shutdown" -> getRandomShutdownQuestions()
            else -> emptyList()
        }
    }

    private fun getNoPowerQuestions(): List<Question> = listOf(
        Question(
            id = "power_cable",
            text = "¿Está conectado el cable de electricidad? (como el cargador de tu celular)",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "power_button",
            text = "¿Al presionar el botón de encendido, se enciende alguna luz o se escucha algún sonido?",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "recent_changes",
            text = "¿Pasó algo especial antes de que dejara de funcionar?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Tormenta eléctrica",
                "Se fue la luz",
                "La moví de lugar",
                "Instalé algo nuevo",
                "Nada especial"
            )
        )
    )

    private fun getSlowPerformanceQuestions(): List<Question> = listOf(
        Question(
            id = "gradual_slowness",
            text = "¿La lentitud apareció de repente o fue gradual (como cuando se llena el celular)?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf("De repente", "Gradualmente", "No estoy seguro/a")
        ),
        Question(
            id = "multiple_programs",
            text = "¿Tienes muchos programas abiertos al mismo tiempo? (como muchas pestañas del navegador)",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "fan_noise",
            text = "¿El ventilador (como un pequeño abanico dentro) suena muy fuerte?",
            type = QuestionType.YES_NO
        ),

                Question(
                id = "gradual_slowness",
        text = "¿La lentitud apareció de repente o fue gradual (como cuando se llena el celular)?",
        type = QuestionType.MULTIPLE_CHOICE,
        options = listOf("De repente", "Gradualmente", "No estoy seguro/a")
    ),
    Question(
    id = "multiple_programs",
    text = "¿Tienes muchos programas abiertos al mismo tiempo? (como muchas pestañas del navegador)",
    type = QuestionType.YES_NO
    ),
    Question(
    id = "fan_noise",
    text = "¿El ventilador (como un pequeño abanico dentro) suena muy fuerte?",
    type = QuestionType.YES_NO
    ),
    Question(
    id = "startup_time",
    text = "¿Cuánto tiempo tarda en encender completamente?",
    type = QuestionType.MULTIPLE_CHOICE,
    options = listOf(
    "Menos de 2 minutos (normal)",
    "Entre 2 y 5 minutos",
    "Más de 5 minutos",
    "No logra encender completamente"
    )
    ),
    Question(
    id = "internet_usage",
    text = "¿Está lenta solo en internet o en todo?",
    type = QuestionType.MULTIPLE_CHOICE,
    options = listOf(
    "Solo en internet",
    "En todo",
    "Principalmente al abrir programas",
    "Solo con archivos grandes"
    )
    ),
    Question(
    id = "storage_full",
    text = "¿Te ha aparecido algún mensaje sobre 'espacio en disco' o 'memoria llena'?",
    type = QuestionType.YES_NO
    ),
    Question(
    id = "antivirus_scan",
    text = "¿Tienes antivirus y cuándo fue la última vez que hizo una revisión completa?",
    type = QuestionType.MULTIPLE_CHOICE,
    options = listOf(
    "Sí tengo, revisó recientemente",
    "Sí tengo, pero no sé cuándo revisó",
    "No tengo antivirus",
    "No sé si tengo antivirus"
    )
    )


    )


    private fun diagnoseNoPower(answers: Map<String, String>): DiagnosticResult {
        val powerConnected = answers["power_cable"] == "Sí"
        val lightsOrSounds = answers["power_button"] == "Sí"

        return when {
            !powerConnected -> DiagnosticResult(
                title = "Problema de conexión eléctrica",
                description = "Es como cuando tu celular no carga porque no está conectado correctamente",
                severity = SeverityLevel.LOW,
                solutions = listOf(
                    Solution(1, "Verifica que el cable esté bien conectado a la computadora"),
                    Solution(2, "Revisa que el cable esté conectado a un enchufe que funcione"),
                    Solution(3, "Prueba con otro enchufe de la casa")
                ),
                probability = 85
            )
            !lightsOrSounds -> DiagnosticResult(
                title = "Posible problema de fuente de poder",
                description = "La 'fuente de poder' es como el corazón que da energía a todo",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Verifica que el interruptor de la fuente esté encendido (en la parte trasera)"),
                    Solution(2, "Es recomendable que un técnico revise la fuente de poder")
                ),
                probability = 70
            )
            else -> DiagnosticResult(
                title = "Problema complejo de hardware",
                description = "Necesitas ayuda de un técnico especializado",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Contacta con un técnico de confianza")
                ),
                probability = 60
            )
        }
    }

    private fun diagnoseSlowPerformance(answers: Map<String, String>): DiagnosticResult {
        val gradualSlowness = answers["gradual_slowness"] == "Gradualmente"
        val multiplePrograms = answers["multiple_programs"] == "Sí"
        val fanNoise = answers["fan_noise"] == "Sí"

        return when {
            multiplePrograms -> DiagnosticResult(
                title = "Sobrecarga de programas",
                description = "Tu computadora está trabajando con demasiadas cosas a la vez (como tener muchas apps abiertas en el celular)",
                severity = SeverityLevel.LOW,
                solutions = listOf(
                    Solution(1, "Cierra los programas que no estés usando"),
                    Solution(2, "Reinicia la computadora (como reiniciar el celular)"),
                    Solution(3, "Evita tener muchos programas abiertos al mismo tiempo")
                ),
                probability = 80
            )
            fanNoise && gradualSlowness -> DiagnosticResult(
                title = "Posible sobrecalentamiento",
                description = "Tu computadora está como con fiebre, necesita refrescarse",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Apaga la computadora y deja que se enfríe por 30 minutos"),
                    Solution(2, "Verifica que los ventiladores no estén bloqueados por polvo"),
                    Solution(3, "Mantén la computadora en un lugar con buena ventilación")
                ),
                probability = 70
            )
            else -> DiagnosticResult(
                title = "Lentitud general del sistema",
                description = "Puede ser falta de mantenimiento o programas innecesarios",
                severity = SeverityLevel.LOW,
                solutions = listOf(
                    Solution(1, "Reinicia la computadora regularmente"),
                    Solution(2, "Considera hacer una limpieza de archivos temporales"),
                    Solution(3, "Si persiste, consulta con un técnico para mantenimiento")
                ),
                probability = 60
            )
        }
    }

    // Agregar estas funciones a tu clase DiagnosticEngine

    private fun getNoDisplayQuestions(): List<Question> = listOf(
        Question(
            id = "monitor_power",
            text = "¿La pantalla/monitor está encendida? (debería tener una luz que indique que está prendida)",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "cable_connection",
            text = "¿Está bien conectado el cable entre la computadora y la pantalla?",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "computer_sounds",
            text = "¿Escuchas que la computadora está funcionando? (ventiladores, discos duros)",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "led_lights",
            text = "¿Se encienden luces en la computadora cuando la prendes?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Sí, se encienden luces",
                "No se enciende nada",
                "Parpadean luces",
                "No estoy seguro/a"
            )
        ),
        Question(
            id = "multiple_monitors",
            text = "¿Tienes otra pantalla o TV donde puedas probar conectar la computadora?",
            type = QuestionType.YES_NO
        )
    )

    private fun getBlueScreenQuestions(): List<Question> = listOf(
        Question(
            id = "error_frequency",
            text = "¿Con qué frecuencia aparecen estas pantallas de error?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Cada vez que enciendo la computadora",
                "De vez en cuando, sin patrón claro",
                "Solo cuando uso ciertos programas",
                "Después de instalar algo nuevo",
                "Solo una vez hasta ahora"
            )
        ),
        Question(
            id = "error_timing",
            text = "¿Cuándo aparecen generalmente estos errores?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Al encender la computadora",
                "Mientras estoy trabajando",
                "Al apagar la computadora",
                "En cualquier momento",
                "Solo con programas específicos"
            )
        ),
        Question(
            id = "recent_software",
            text = "¿Instalaste algún programa nuevo recientemente?",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "memory_activities",
            text = "¿Estabas haciendo algo específico cuando apareció el error?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Navegando en internet",
                "Jugando",
                "Viendo videos",
                "Usando programas de oficina",
                "Nada especial, solo estaba encendida"
            )
        )
    )

    private fun getStrangeSoundsQuestions(): List<Question> = listOf(
        Question(
            id = "sound_type",
            text = "¿Qué tipo de sonido escuchas?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Ruido fuerte como un helicóptero",
                "Clicks o chasquidos repetitivos",
                "Silbidos o chirridos",
                "Como si algo estuviera rayando",
                "Pitidos o beeps al encender"
            )
        ),
        Question(
            id = "sound_timing",
            text = "¿Cuándo escuchas estos sonidos?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Solo al encender la computadora",
                "Todo el tiempo que está encendida",
                "Solo cuando trabajo mucho con ella",
                "De vez en cuando, sin patrón",
                "Solo al apagar"
            )
        ),
        Question(
            id = "sound_location",
            text = "¿De dónde parece venir el sonido?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "De adentro de la computadora",
                "Del monitor/pantalla",
                "No estoy seguro/a",
                "Parece venir de los parlantes"
            )
        ),
        Question(
            id = "performance_with_sound",
            text = "¿Cuando hace estos ruidos, la computadora funciona normal?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Sí, funciona normal",
                "Se pone más lenta",
                "A veces se congela",
                "Se apaga sola"
            )
        )
    )

    // Preguntas adicionales para "No Power" (expandir las existentes)
    private fun getNoPowerQuestionsExpanded(): List<Question> = listOf(
        Question(
            id = "power_cable",
            text = "¿Está conectado el cable de electricidad? (como el cargador de tu celular)",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "power_outlet_test",
            text = "¿Probaste conectar otra cosa en el mismo enchufe? (como una lámpara)",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "power_button",
            text = "¿Al presionar el botón de encendido, se enciende alguna luz o se escucha algún sonido?",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "power_button_location",
            text = "¿Estás seguro/a de cuál es el botón de encendido?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Sí, es el botón más grande",
                "Creo que sí, pero no estoy seguro/a",
                "No estoy seguro/a cuál es"
            )
        ),
        Question(
            id = "recent_changes",
            text = "¿Pasó algo especial antes de que dejara de funcionar?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Tormenta eléctrica",
                "Se fue la luz",
                "La moví de lugar",
                "Instalé algo nuevo",
                "Se cayó o golpeó",
                "Derramé líquido cerca",
                "Nada especial"
            )
        ),
        Question(
            id = "last_working",
            text = "¿Cuándo fue la última vez que funcionó bien?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Ayer",
                "Hace unos días",
                "Hace una semana",
                "Hace más de una semana",
                "No recuerdo"
            )
        )
    )

    // Preguntas adicionales para "Slow Performance" (expandir las existentes)
    private fun getSlowPerformanceQuestionsExpanded(): List<Question> = listOf(
        Question(
            id = "gradual_slowness",
            text = "¿La lentitud apareció de repente o fue gradual (como cuando se llena el celular)?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf("De repente", "Gradualmente", "No estoy seguro/a")
        ),
        Question(
            id = "multiple_programs",
            text = "¿Tienes muchos programas abiertos al mismo tiempo? (como muchas pestañas del navegador)",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "fan_noise",
            text = "¿El ventilador (como un pequeño abanico dentro) suena muy fuerte?",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "startup_time",
            text = "¿Cuánto tiempo tarda en encender completamente?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Menos de 2 minutos (normal)",
                "Entre 2 y 5 minutos",
                "Más de 5 minutos",
                "No logra encender completamente"
            )
        ),
        Question(
            id = "internet_usage",
            text = "¿Está lenta solo en internet o en todo?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Solo en internet",
                "En todo",
                "Principalmente al abrir programas",
                "Solo con archivos grandes"
            )
        ),
        Question(
            id = "storage_full",
            text = "¿Te ha aparecido algún mensaje sobre 'espacio en disco' o 'memoria llena'?",
            type = QuestionType.YES_NO
        ),
        Question(
            id = "antivirus_scan",
            text = "¿Tienes antivirus y cuándo fue la última vez que hizo una revisión completa?",
            type = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Sí tengo, revisó recientemente",
                "Sí tengo, pero no sé cuándo revisó",
                "No tengo antivirus",
                "No sé si tengo antivirus"
            )
        )
    )




    private fun diagnoseNoDisplay(answers: Map<String, String>): DiagnosticResult {
        val monitorPower = answers["monitor_power"] == "Sí"
        val cableConnection = answers["cable_connection"] == "Sí"
        val computerSounds = answers["computer_sounds"] == "Sí"
        val ledLights = answers["led_lights"]

        return when {
            !monitorPower -> DiagnosticResult(
                title = "Problema con la pantalla/monitor",
                description = "Tu pantalla no está recibiendo energía, como cuando tu TV está apagada",
                severity = SeverityLevel.LOW,
                solutions = listOf(
                    Solution(1, "Verifica que la pantalla esté conectada a la electricidad"),
                    Solution(2, "Presiona el botón de encendido de la pantalla"),
                    Solution(3, "Revisa que el cable de poder de la pantalla esté bien conectado")
                ),
                probability = 85
            )
            !cableConnection -> DiagnosticResult(
                title = "Problema de conexión de video",
                description = "Es como cuando el cable del TV está suelto",
                severity = SeverityLevel.LOW,
                solutions = listOf(
                    Solution(1, "Desconecta y vuelve a conectar el cable entre computadora y pantalla"),
                    Solution(2, "Verifica que esté conectado firmemente en ambos extremos"),
                    Solution(3, "Si tienes otro cable similar, pruébalo")
                ),
                probability = 75
            )
            !computerSounds && ledLights == "No se enciende nada" -> DiagnosticResult(
                title = "La computadora no está encendiendo",
                description = "Este es realmente un problema de 'no enciende', no de pantalla",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Verifica la conexión eléctrica de la computadora"),
                    Solution(2, "Revisa el botón de encendido de la computadora"),
                    Solution(3, "Consulta las soluciones para 'No enciende para nada'")
                ),
                probability = 90
            )
            computerSounds && ledLights == "Sí, se encienden luces" -> DiagnosticResult(
                title = "Problema de tarjeta gráfica o memoria RAM",
                description = "La computadora funciona pero no puede mostrar imagen. Es un problema más técnico",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Apaga la computadora y desconéctala por 5 minutos"),
                    Solution(2, "Si tienes otra pantalla o TV, prueba conectarla ahí"),
                    Solution(3, "Este problema necesita un técnico especializado", isAdvanced = true)
                ),
                probability = 70
            )
            else -> DiagnosticResult(
                title = "Problema de video complejo",
                description = "Necesitas ayuda técnica especializada",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Prueba con otra pantalla si tienes disponible"),
                    Solution(2, "Contacta con un técnico de confianza")
                ),
                probability = 60
            )
        }
    }

    private fun diagnoseBlueScreen(answers: Map<String, String>): DiagnosticResult {
        val errorFrequency = answers["error_frequency"]
        val recentSoftware = answers["recent_software"] == "Sí"
        val errorTiming = answers["error_timing"]

        return when {
            errorFrequency == "Cada vez que enciendo la computadora" -> DiagnosticResult(
                title = "Error crítico del sistema",
                description = "Tu computadora tiene un problema serio que no la deja funcionar bien",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Intenta encender en 'Modo Seguro' (F8 al encender)", isAdvanced = true),
                    Solution(2, "Si funciona en modo seguro, desinstala programas recientes"),
                    Solution(3, "Necesitas ayuda de un técnico urgentemente")
                ),
                probability = 85
            )
            recentSoftware && errorFrequency == "Después de instalar algo nuevo" -> DiagnosticResult(
                title = "Conflicto de software",
                description = "El programa nuevo que instalaste no es compatible con tu computadora",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Desinstala el último programa que instalaste"),
                    Solution(2, "Reinicia la computadora después de desinstalar"),
                    Solution(3, "Si no mejora, restaura el sistema a un punto anterior", isAdvanced = true)
                ),
                probability = 80
            )
            errorTiming == "Solo con programas específicos" -> DiagnosticResult(
                title = "Problema con programas específicos",
                description = "Algunos programas están causando conflictos",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Identifica qué programas causan el error"),
                    Solution(2, "Actualiza o reinstala esos programas"),
                    Solution(3, "Evita usar esos programas hasta solucionarlo")
                ),
                probability = 70
            )
            else -> DiagnosticResult(
                title = "Error intermitente del sistema",
                description = "Pueden ser problemas de memoria, disco duro o sobrecalentamiento",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Apaga la computadora y déjala descansar 30 minutos"),
                    Solution(2, "Verifica que no esté acumulando polvo"),
                    Solution(3, "Haz un respaldo de tus archivos importantes"),
                    Solution(4, "Consulta con un técnico para revisar memoria y disco duro")
                ),
                probability = 60
            )
        }
    }

    private fun diagnoseStrangeSounds(answers: Map<String, String>): DiagnosticResult {
        val soundType = answers["sound_type"]
        val soundTiming = answers["sound_timing"]
        val performanceWithSound = answers["performance_with_sound"]

        return when {
            soundType == "Ruido fuerte como un helicóptero" -> DiagnosticResult(
                title = "Ventilador trabajando excesivamente",
                description = "Los ventiladores están trabajando muy fuerte, como cuando tienes mucha fiebre",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Apaga la computadora y limpia el polvo de los ventiladores"),
                    Solution(2, "Verifica que las salidas de aire no estén bloqueadas"),
                    Solution(3, "Usa la computadora en un lugar más fresco"),
                    Solution(4, "Si persiste, puede necesitar mantenimiento técnico")
                ),
                probability = 80
            )
            soundType == "Clicks o chasquidos repetitivos" -> DiagnosticResult(
                title = "Posible problema de disco duro",
                description = "El disco duro (donde se guardan tus archivos) puede estar fallando",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "HAZ RESPALDO DE TUS ARCHIVOS IMPORTANTES INMEDIATAMENTE"),
                    Solution(2, "Evita usar la computadora para tareas pesadas"),
                    Solution(3, "Contacta un técnico urgentemente para revisar el disco duro")
                ),
                probability = 85
            )
            soundType == "Pitidos o beeps al encender" -> DiagnosticResult(
                title = "Códigos de error de la computadora",
                description = "La computadora está tratando de decirte qué problema tiene",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Cuenta cuántos pitidos hace y si son largos o cortos"),
                    Solution(2, "Anota el patrón de pitidos"),
                    Solution(3, "Consulta con un técnico y comparte esta información")
                ),
                probability = 75
            )
            performanceWithSound == "Se apaga sola" -> DiagnosticResult(
                title = "Sobrecalentamiento crítico",
                description = "Tu computadora se está protegiendo del calor excesivo",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Deja de usar la computadora inmediatamente"),
                    Solution(2, "Apágala y déjala enfriar por varias horas"),
                    Solution(3, "Verifica que todos los ventiladores funcionen"),
                    Solution(4, "Necesita limpieza y mantenimiento técnico urgente")
                ),
                probability = 90
            )
            else -> DiagnosticResult(
                title = "Ruidos anormales del sistema",
                description = "Tu computadora está haciendo sonidos que no debería",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Apaga la computadora y verifica que no haya objetos sueltos"),
                    Solution(2, "Escucha cuidadosamente de dónde viene el ruido"),
                    Solution(3, "Si el ruido persiste, consulta con un técnico")
                ),
                probability = 65
            )
        }
    }

    // Funciones de diagnóstico para nuevos síntomas
    private fun diagnoseInternetProblems(answers: Map<String, String>): DiagnosticResult {
        val wifiConnected = answers["wifi_connected"]
        val otherDevices = answers["other_devices"] == "Sí"
        val routerRestart = answers["router_restart"] == "Sí"

        return when {
            !otherDevices -> DiagnosticResult(
                title = "Problema con tu internet",
                description = "El problema no es tu computadora, es tu conexión a internet",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Contacta a tu proveedor de internet"),
                    Solution(2, "Apaga y enciende tu módem/router"),
                    Solution(3, "Verifica que todas las conexiones del módem estén bien")
                ),
                probability = 85
            )
            wifiConnected == "Aparece con una X o signo de exclamación" -> DiagnosticResult(
                title = "Problema de configuración WiFi",
                description = "Tu computadora detecta la red pero no se puede conectar correctamente",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Olvida la red WiFi y conéctate de nuevo"),
                    Solution(2, "Verifica que tengas la contraseña correcta"),
                    Solution(3, "Reinicia tu computadora y el router"),
                    Solution(4, "Acércate más al router")
                ),
                probability = 75
            )
            wifiConnected == "No aparece nada" -> DiagnosticResult(
                title = "Problema con adaptador WiFi",
                description = "Tu computadora no puede detectar redes WiFi",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Verifica que el WiFi esté activado en tu computadora"),
                    Solution(2, "Busca un botón físico de WiFi en tu laptop"),
                    Solution(3, "Puede ser un problema de drivers - consulta un técnico")
                ),
                probability = 70
            )
            else -> DiagnosticResult(
                title = "Problema de red intermitente",
                description = "La conexión funciona pero no de manera estable",
                severity = SeverityLevel.LOW,
                solutions = listOf(
                    Solution(1, "Reinicia tu computadora"),
                    Solution(2, "Acércate más al router"),
                    Solution(3, "Evita interferencias (microondas, otros dispositivos)")
                ),
                probability = 60
            )
        }
    }

    private fun diagnoseFreezing(answers: Map<String, String>): DiagnosticResult {
        val frequency = answers["freeze_frequency"]
        val pattern = answers["freeze_pattern"]
        val forceRestart = answers["force_restart"] == "Sí"
        val overheating = answers["overheating"] == "Sí"
        val lowDiskSpace = answers["low_disk_space"] == "Sí"
        val antivirusScan = answers["antivirus_scan"]
        val blueScreen = answers["blue_screen"] == "Sí"
        val unusualNoises = answers["unusual_noises"] == "Sí"
        val recentUpdates = answers["recent_updates"] == "Sí"

        return when {
            // Caso 1: Congelamiento grave y requiere técnico
            frequency == "Varias veces al día" && forceRestart -> DiagnosticResult(
                title = "Problema crítico del sistema",
                description = "Tu computadora tiene un problema serio que requiere atención técnica.",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Haz respaldo de tus archivos importantes."),
                    Solution(2, "Verifica que no esté sobrecalentándose."),
                    Solution(3, "Consulta con un técnico - puede ser la memoria RAM o el disco duro.")
                ),
                probability = 85
            )

            // Caso 2: Congelamiento por temperatura alta
            overheating -> DiagnosticResult(
                title = "Sobrecalentamiento",
                description = "Tu computadora se calienta demasiado y se congela para protegerse.",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Limpia el ventilador y las rejillas de ventilación."),
                    Solution(2, "Colócala en un lugar ventilado y evita tapar la salida de aire."),
                    Solution(3, "Usa una base de enfriamiento si es una laptop.")
                ),
                probability = 90
            )

            // Caso 3: Congelamiento por espacio en disco
            lowDiskSpace -> DiagnosticResult(
                title = "Espacio en disco insuficiente",
                description = "Tu computadora tiene poco espacio libre, lo que puede causar congelamientos.",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Elimina archivos que no necesites."),
                    Solution(2, "Desinstala programas que no uses."),
                    Solution(3, "Vacía la papelera de reciclaje y limpia archivos temporales.")
                ),
                probability = 80
            )

            // Caso 4: Posible infección de virus o malware
            antivirusScan == "No he pasado antivirus" -> DiagnosticResult(
                title = "Posible virus o malware",
                description = "No has pasado antivirus y esto podría causar bloqueos y congelamientos.",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Instala y ejecuta un antivirus de confianza."),
                    Solution(2, "Elimina archivos sospechosos detectados."),
                    Solution(3, "Mantén actualizado tu antivirus.")
                ),
                probability = 75
            )

            // Caso 5: Error grave del sistema con pantalla azul
            blueScreen -> DiagnosticResult(
                title = "Error crítico (pantalla azul)",
                description = "Una pantalla azul indica un fallo grave de hardware o sistema.",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Anota el código de error de la pantalla azul."),
                    Solution(2, "Busca en internet soluciones para ese código."),
                    Solution(3, "Consulta a un técnico si sigue ocurriendo.")
                ),
                probability = 90
            )

            // Caso 6: Posible problema físico del disco duro
            unusualNoises -> DiagnosticResult(
                title = "Posible daño físico del disco duro",
                description = "Escuchar ruidos extraños puede indicar que el disco duro está fallando.",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Haz una copia de seguridad de tus archivos importantes de inmediato."),
                    Solution(2, "Evita mover la computadora bruscamente."),
                    Solution(3, "Consulta a un técnico para revisar el disco duro.")
                ),
                probability = 85
            )

            // Caso 7: Problema después de actualización reciente
            recentUpdates -> DiagnosticResult(
                title = "Problema después de actualización",
                description = "Es posible que una actualización reciente esté causando conflictos.",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Revisa si hay actualizaciones pendientes para completarlas."),
                    Solution(2, "Considera desinstalar la última actualización si empezó después de ella."),
                    Solution(3, "Reinicia la computadora después de aplicar cambios.")
                ),
                probability = 70
            )

            // Caso 8: Congelamiento por programa específico
            pattern == "Al abrir programas específicos" -> DiagnosticResult(
                title = "Problema con programas específicos",
                description = "Algunos programas están causando conflictos o errores.",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Identifica cuáles programas causan el problema."),
                    Solution(2, "Reinstala esos programas."),
                    Solution(3, "Verifica si tienes suficiente memoria RAM."),
                    Solution(4, "Actualiza esos programas a la última versión.")
                ),
                probability = 75
            )

            // Caso 9: Congelamiento por múltiples tareas
            pattern == "Al hacer varias cosas a la vez" -> DiagnosticResult(
                title = "Falta de recursos del sistema",
                description = "Tu computadora no tiene suficiente memoria o recursos para tantas tareas a la vez.",
                severity = SeverityLevel.LOW,
                solutions = listOf(
                    Solution(1, "Cierra programas que no estés usando."),
                    Solution(2, "Evita tener muchas pestañas o aplicaciones abiertas."),
                    Solution(3, "Reinicia la computadora con frecuencia."),
                    Solution(4, "Considera aumentar la memoria RAM si es posible.")
                ),
                probability = 85
            )

            // Caso general
            else -> DiagnosticResult(
                title = "Congelamiento general",
                description = "El congelamiento puede deberse a varias causas posibles.",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Verifica si hay actualizaciones pendientes."),
                    Solution(2, "Ejecuta un antivirus completo."),
                    Solution(3, "Limpia archivos temporales y optimiza el disco."),
                    Solution(4, "Consulta a un técnico si el problema continúa.")
                ),
                probability = 65
            )
        }
    }


    private fun diagnoseOverheating(answers: Map<String, String>): DiagnosticResult {
        val heatLocation = answers["heat_location"]
        val shutdownDueHeat = answers["shutdown_due_heat"] == "Sí"
        val ventilationBlocked = answers["ventilation_blocked"]

        return when {
            shutdownDueHeat -> DiagnosticResult(
                title = "Sobrecalentamiento crítico",
                description = "Tu computadora se está protegiendo del calor excesivo apagándose",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "DEJA DE USAR LA COMPUTADORA inmediatamente"),
                    Solution(2, "Apágala y déjala enfriar por varias horas"),
                    Solution(3, "Limpia los ventiladores y salidas de aire"),
                    Solution(4, "Necesita limpieza técnica profesional URGENTE")
                ),
                probability = 95
            )
            ventilationBlocked == "Sí, tienen mucho polvo" -> DiagnosticResult(
                title = "Ventilación obstruida",
                description = "El polvo está impidiendo que tu computadora se enfríe correctamente",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Apaga la computadora completamente"),
                    Solution(2, "Limpia cuidadosamente las salidas de aire con aire comprimido"),
                    Solution(3, "Usa la computadora en lugares con mejor ventilación"),
                    Solution(4, "Considera una limpieza técnica profesional")
                ),
                probability = 85
            )
            heatLocation == "En la parte de abajo (laptop)" -> DiagnosticResult(
                title = "Problema de ventilación en laptop",
                description = "Tu laptop no puede respirar bien por donde está colocada",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "No uses la laptop sobre camas, sofás o superficies blandas"),
                    Solution(2, "Usa una base con ventiladores para laptop"),
                    Solution(3, "Eleva la laptop para mejor circulación de aire"),
                    Solution(4, "Limpia los ventiladores internos")
                ),
                probability = 80
            )
            else -> DiagnosticResult(
                title = "Calentamiento anormal",
                description = "Tu computadora está trabajando más de lo normal",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Verifica qué programas están usando mucho procesador"),
                    Solution(2, "Cierra programas innecesarios"),
                    Solution(3, "Mantén la computadora en lugar fresco"),
                    Solution(4, "Si persiste, puede necesitar mantenimiento técnico")
                ),
                probability = 70
            )
        }
    }

    private fun diagnoseProgramsNotOpening(answers: Map<String, String>): DiagnosticResult {
        val allPrograms = answers["all_programs"]
        val doubleClick = answers["double_click"]
        val errorMessages = answers["error_messages"] == "Sí"

        return when {
            doubleClick == "Solo un click" -> DiagnosticResult(
                title = "Problema de uso - necesitas doble click",
                description = "Para abrir programas necesitas hacer doble click rápido, no solo uno",
                severity = SeverityLevel.LOW,
                solutions = listOf(
                    Solution(1, "Haz doble click rápido en los íconos"),
                    Solution(2, "Asegúrate de hacer los dos clicks muy rápido"),
                    Solution(3, "Practica con diferentes íconos")
                ),
                probability = 90
            )
            allPrograms == "Ningún programa abre" && errorMessages -> DiagnosticResult(
                title = "Problema crítico del sistema",
                description = "Hay un problema serio con tu sistema operativo",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Reinicia la computadora"),
                    Solution(2, "Si persiste, puede necesitar reparación del sistema"),
                    Solution(3, "Haz respaldo de tus archivos"),
                    Solution(4, "Consulta con un técnico urgentemente")
                ),
                probability = 85
            )
            allPrograms == "Solo algunos programas específicos" -> DiagnosticResult(
                title = "Programas dañados o incompatibles",
                description = "Algunos programas específicos tienen problemas",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Identifica qué programas no funcionan"),
                    Solution(2, "Reinstala esos programas específicos"),
                    Solution(3, "Verifica que sean compatibles con tu sistema"),
                    Solution(4, "Busca actualizaciones de esos programas")
                ),
                probability = 75
            )
            else -> DiagnosticResult(
                title = "Problema general de programas",
                description = "Puede ser un problema de configuración o sistema",
                severity = SeverityLevel.MEDIUM,
                solutions = listOf(
                    Solution(1, "Reinicia la computadora"),
                    Solution(2, "Verifica que tengas permisos de administrador"),
                    Solution(3, "Ejecuta un antivirus completo"),
                    Solution(4, "Si persiste, consulta un técnico")
                ),
                probability = 65
            )
        }
    }

    private fun diagnoseRandomShutdown(answers: Map<String, String>): DiagnosticResult {
        val shutdownWarning = answers["shutdown_warning"]
        val powerSupplyCheck = answers["power_supply_check"]
        val shutdownPattern = answers["shutdown_pattern"]

        return when {
            shutdownWarning == "Se apaga de repente sin avisar" && powerSupplyCheck == "Directamente al enchufe de la pared" -> DiagnosticResult(
                title = "Problema eléctrico",
                description = "Posible problema con la energía eléctrica o fuente de poder",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Conecta la computadora a un regulador de voltaje"),
                    Solution(2, "Verifica que el enchufe funcione bien"),
                    Solution(3, "Puede ser problema de la fuente de poder - consulta técnico"),
                    Solution(4, "Evita enchufes con problemas eléctricos")
                ),
                probability = 80
            )
            shutdownPattern == "Solo cuando hace calor en el ambiente" || shutdownPattern == "Siempre después de cierto tiempo de uso" -> DiagnosticResult(
                title = "Sobrecalentamiento progresivo",
                description = "Tu computadora se apaga para protegerse del calor",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Limpia los ventiladores y salidas de aire"),
                    Solution(2, "Usa la computadora en lugares más frescos"),
                    Solution(3, "Reduce el tiempo de uso continuo"),
                    Solution(4, "Necesita limpieza técnica profesional")
                ),
                probability = 85
            )
            shutdownWarning == "La pantalla se pone azul primero" -> DiagnosticResult(
                title = "Error crítico del sistema",
                description = "Problema serio de software o hardware que causa pantallas azules",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Anota el código de error de la pantalla azul"),
                    Solution(2, "Haz respaldo de archivos importantes"),
                    Solution(3, "Puede ser problema de memoria RAM o disco duro"),
                    Solution(4, "Consulta técnico con el código de error")
                ),
                probability = 75
            )
            else -> DiagnosticResult(
                title = "Apagado anormal del sistema",
                description = "Problema que puede tener múltiples causas",
                severity = SeverityLevel.HIGH,
                solutions = listOf(
                    Solution(1, "Verifica la temperatura de la computadora"),
                    Solution(2, "Usa un regulador de voltaje"),
                    Solution(3, "Ejecuta diagnósticos de memoria y disco duro"),
                    Solution(4, "Consulta con un técnico para diagnóstico completo")
                ),
                probability = 65
            )
        }
    }


}