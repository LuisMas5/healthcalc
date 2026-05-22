# language: es
Característica: Calcular IW
    Como medico que utiliza la aplicacion
    Quiero introducir los datos de un paciente
    Y obtener el IW del paciente
    Para calcular el peso ideal del paciente

    Antecedentes:
        Dado que la calculadora de salud esta operativa

    @Exito
    Escenario: Calcular IW para un hombre con datos validos
        Dado que el paciente tiene una altura de 168,0 cm
        Y en el IW el paciente es de sexo masculino
        Cuando calculo el IW del paciente
        Entonces el resultado del IW deberia ser aproximadamente 63,5

    @Exito
    Escenario: Calcular IW para una mujer con datos validos
        Dado que el paciente tiene una altura de 168,0 cm
        Y en el IW el paciente es de sexo femenino
        Cuando calculo el IW del paciente
        Entonces el resultado del IW deberia ser aproximadamente 59,0

    @ErrorSexo
    Escenario: Calcular IW con sexo erroneo
        Dado que el paciente tiene una altura de 168,0 cm
        Y en el IW el paciente tiene un sexo no valido
        Cuando calculo el IW del paciente
        Entonces en el IW el sistema debe lanzar una excepcion

    @ErrorNegativo
    Escenario: Calcular IW con altura negativa
        Dado que el paciente tiene una altura de -5,0 cm
        Y en el IW el paciente es de sexo masculino
        Cuando calculo el IW del paciente
        Entonces en el IW el sistema debe lanzar una excepcion

    @ErrorBiologico
    Escenario: Calcular IW con altura menor de la esperada
        Dado que el paciente tiene una altura de 20,0 cm
        Y en el IW el paciente es de sexo masculino
        Cuando calculo el IW del paciente
        Entonces en el IW el sistema debe lanzar una excepcion

    @ErrorBiologico
    Esquema del escenario: Calcular IW con altura problematica para la formula
        Dado que el paciente tiene una altura de <altura> cm
        Y en el IW el paciente es de sexo <sexo>
        Cuando calculo el IW del paciente
        Entonces en el IW el sistema debe lanzar una excepcion

        Ejemplos:
            | altura | sexo      |
            | 29,0   | masculino |
            | 29,0   | femenino  |

    @ErrorBiologico
    Escenario: Calcular IW con altura mayor de la esperada
        Dado que el paciente tiene una altura de 301,0 cm
        Y en el IW el paciente es de sexo masculino
        Cuando calculo el IW del paciente
        Entonces en el IW el sistema debe lanzar una excepcion

    @Exito
    Esquema del escenario: Calcular IW para hombres con alturas validas
        Dado que el paciente tiene una altura de <altura> cm
        Y en el IW el paciente es de sexo masculino
        Cuando calculo el IW del paciente
        Entonces el resultado del IW deberia ser aproximadamente <iw>

        Ejemplos:
            | altura | iw    |
            | 110,0  | 20,0  |
            | 168,0  | 63,5  |
            | 250,0  | 125,0 |

    @Exito
    Esquema del escenario: Calcular IW para mujeres con alturas validas
        Dado que el paciente tiene una altura de <altura> cm
        Y en el IW el paciente es de sexo femenino
        Cuando calculo el IW del paciente
        Entonces el resultado del IW deberia ser aproximadamente <iw>

        Ejemplos:
            | altura | iw    |
            | 100,0  | 25,0  |
            | 168,0  | 59,0  |
            | 250,0  | 100,0 |