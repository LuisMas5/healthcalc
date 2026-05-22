# language: es
Característica: Calcular VAI
    Como medico que utiliza la aplicacion
    Quiero introducir los datos de un paciente 
    Y obtener el VAI del paciente
    Para calcular el VAI del paciente

    Antecedentes:
        Dado que la calculadora de salud esta operativa

    @Exito
    Escenario: Calcular VAI para un hombre con datos validos
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el resultado del VAI deberia ser aproximadamente 1,98

    @Exito
    Escenario: Calcular VAI para una mujer con datos validos
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo femenino
        Cuando calculo el VAI del paciente
        Entonces el resultado del VAI deberia ser aproximadamente 3,02

    @ErrorSexo
    Escenario: Calcular VAI con sexo erroneo
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente tiene un sexo no valido
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

    @ErrorCero
    Escenario: Calcular VAI con trigliceridos nulos
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 0,0 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion
    @ErrorCero
    Escenario: Calcular VAI con circunferencia de cintura nula
        Dado que el paciente tiene una circunferencia de cintura de 0,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

    @ErrorCero
    Escenario: Calcular VAI con IMC nulo
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 0,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

    @ErrorCero
    Escenario: Calcular VAI con HDL nulo
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 0,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

    @ErrorNegativo
    Escenario: Calcular VAI con trigliceridos negativos
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de -1,0 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 25,0  
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

    @ErrorNegativo
    Escenario: Calcular VAI con circunferencia de cintura negativa
        Dado que el paciente tiene una circunferencia de cintura de -90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

    @ErrorNegativo
    Escenario: Calcular VAI con IMC negativo
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de -25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

    @ErrorNegativo
    Escenario: Calcular VAI con HDL negativo
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de -1,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

    @ErrorBiologico
    Esquema del escenario: Calcular VAI con trigliceridos biologicamente imposibles
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de <tg> mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

        Ejemplos:
            | tg   |
            | 20,0 |
            | 500,0  |
            | 15,1 |
            | 100,0 |

    @ErrorBiologico
    Esquema del escenario: Calcular VAI con circunferencia de cintura biologicamente imposible
        Dado que el paciente tiene una circunferencia de cintura de <cc> cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

        Ejemplos:
            | cc    |
            | 300,0  |
            | 500,0  |
            | 200,1 |
            | 3000  |

    @ErrorBiologico
    Esquema del escenario: Calcular VAI con IMC biologicamente imposible
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de 1,0 mmol/L
        Y el paciente tiene un IMC de <imc>
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

        Ejemplos:
            | imc   |
            | 200,0 |
            | 350,0 |
            | 150,1 |
            | 1000  |

    @ErrorBiologico
    Esquema del escenario: Calcular VAI con HDL biologicamente imposible
        Dado que el paciente tiene una circunferencia de cintura de 90,0 cm
        Y el paciente tiene un nivel de trigliceridos de 1,5 mmol/L
        Y el paciente tiene un nivel de HDL de <hdl> mmol/L
        Y el paciente tiene un IMC de 25,0
        Y el paciente es de sexo masculino
        Cuando calculo el VAI del paciente
        Entonces el sistema debe lanzar una excepcion

        Ejemplos:
            | hdl   |
            | 10,0  |
            | 7,0   |
            | 5,1   |
            | 100,0 |