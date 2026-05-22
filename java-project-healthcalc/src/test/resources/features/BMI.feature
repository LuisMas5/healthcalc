# language: es
Característica: Calcular y Clasificar BMI
    Como profesional de la salud que utiliza la aplicacion
    Quiero introducir el peso y la altura de un paciente
    Y obtener su valor numerico de BMI y su clasificacion
    Para evaluar el estado nutricional del paciente

    Antecedentes:
        Dado que la calculadora de salud esta operativa

    @Exito
    Escenario: Calcular BMI con datos validos
        Dado que el paciente tiene un peso de 70,0 kg
        Y el paciente tiene una altura de 1,75 m
        Cuando calculo el BMI del paciente
        Entonces el resultado del BMI deberia ser aproximadamente 22,85

    @ErrorCero
    Escenario: Calcular BMI con altura nula
        Dado que el paciente tiene un peso de 70,0 kg
        Y el paciente tiene una altura de 0,0 m
        Cuando calculo el BMI del paciente
        Entonces el sistema debe lanzar una excepcion de datos invalidos

    @ErrorNegativo
    Escenario: Calcular BMI con peso negativo
        Dado que el paciente tiene un peso de -10,0 kg
        Y el paciente tiene una altura de 1,75 m
        Cuando calculo el BMI del paciente
        Entonces el sistema debe lanzar una excepcion de datos invalidos

    @ErrorBiologico
    Escenario: Calcular BMI con peso biologicamente imposible
        Dado que el paciente tiene un peso de 800,0 kg
        Y el paciente tiene una altura de 1,75 m
        Cuando calculo el BMI del paciente
        Entonces el sistema debe lanzar una excepcion de datos invalidos

    @Exito
    Esquema del escenario: Clasificacion completa del BMI
        Dado que el paciente tiene un valor de BMI de <bmi>
        Cuando clasifico el BMI del paciente
        Entonces el resultado de la clasificacion deberia ser "<categoria>"

        Ejemplos:
            | bmi  | categoria         |
            | 15,0 | Severe thinness   |
            | 16,5 | Moderate thinness |
            | 18,0 | Mild thinness     |
            | 22,0 | Normal weight     |
            | 27,0 | Overweight        |
            | 32,0 | Obese Class I     |
            | 37,0 | Obese Class II    |
            | 45,0 | Obese Class III   |