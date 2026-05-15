# HealthCalc
Bienvenido al proyecto de la asignatura de **Ingeniería del Software Avanzada**.

El [Hospital Universitario Virgen de la Victoria (El Clínico)](https://www.sspa.juntadeandalucia.es/servicioandaluzdesalud/hospital/virgen-victoria/) de Málaga nos ha encargado el desarrollo de una **Calculadora de Salud** (**_HealthCalc_**) que permita calcular diferentes métricas de los pacientes.

![MOdelo de características de la calculadora de salud.](resources/images/healthcalc_fm.png)

## Interfaz de Usuario

A continuación se muestra el boceto de diseño de la interfaz gráfica de la **Calculadora de Salud**, que ilustra la disposición y organización de los componentes de la aplicación:

![Boceto de la Calculadora de Salud - HealthCalc](doc/P4/boceto.PNG)

**Descripción del boceto:** La interfaz está organizada en una ventana principal con tres pestañas (`JTabbedPane`) que permiten acceder a cada uno de los cálculos disponibles:

1. **Pestaña "Calcular BMI"**: Contiene campos de entrada para peso (kg) y altura (cm), un botón para ejecutar el cálculo, y etiquetas de resultado que muestran tanto el valor numérico del IMC como su clasificación clínica (Normal, Sobrepeso, Obesidad, etc.) con código de colores para facilitar la interpretación visual.

2. **Pestaña "Calcular IBW"**: Permite ingresar la altura en centímetros y seleccionar el sexo del paciente (Hombre/Mujer) mediante un selector, botón de cálculo y resultado del peso corporal ideal según la fórmula de Lorentz.

3. **Pestaña "Calcular VAI"**: Requiere múltiples métricas (IMC, circunferencia de cintura, triglicéridos, HDL) y sexo del paciente para calcular el Índice de Adiposidad Visceral, proporcionando un indicador de riesgo cardiometabólico.

La interfaz utiliza un diseño limpio y funcional con layouts en `GridBag` y `FormLayout` para una distribución óptima de componentes, facilitando la interacción y legibilidad de los resultados clínicos.

## Requisitos  

<details>
<summary><b>Requisitos Funcionales</b></summary>

- La calculadora debe dar soporte a al menos tres métricas.

</details>

<details>
<summary><b>Requisitos No Funcionales</b></summary>

Para que el proyecto cumpla con estándares de software médico, se deben incluir:
- **Gestión de Errores:** Manejo de excepciones en divisiones por cero (ej. altura 0 en IMC).
  1.  **Validación de Rangos (_Data Scrubbing_):**
      * *Hard Limits:* Bloquear entradas imposibles (ej. altura de 4 metros).
      * *Soft Limits:* Avisos ante valores inusuales pero posibles.
    
        > **Límites Biológicos Reales**:
            * **Altura:** El ser humano más alto registrado midió aproximadamente 272 cm. Un límite de 300 cm es un "Hard Limit" sensato.
            Un recién nacido puede medir 40cm. Un límite inferior sensato es de 30cm.
            * **Peso:** El peso máximo registrado ronda los 635 kg. Un límite de 700 kg sería el tope lógico.
            Un recién nacido puede pesar 2kg. Un límite inferior sensato es de 1kg.
  2.  **Soporte Multi-unidad:** Conversión automática entre sistema métrico (kg, cm) e imperial (lb, ft/in).
  3.  **Gestión de Errores:** Manejo de excepciones en divisiones por cero (ej. altura 0 en IMC).
- Todo el código de la aplicación (incluido los comentarios) deben estar en inglés.
- **Privacidad (_Compliance_):** Si el software almacena datos, debe considerar la anonimización de la Información Personal Identificable (PII) bajo normativas como GDPR o HIPAA.

</details>



## Métricas de HealthCalc

<details>
<summary><b>Métricas Antropométricas</b></summary>

* **M1: Índice de Masa Corporal (IMC) o _Body Mass Index (BMI)_:** El IMC es es un indicador estándar, adoptado por la [Organización Mundial de la Salud (OMS)](https://www.who.int/es), que evalúa la adecuación del peso de una persona en relación con su altura para estimar la grasa corporal.

    * **Fórmula:** $IMC = \frac{\text{peso (kg)}}{\text{altura (m)}^2}$

    El IMC nos permite clasificar el estado nutricional de una persona en categorías. La OMS ha definido la siguiente clasificación estándar del estado nutricional en adultos:

      - Bajo peso ($<18.5$)
      - Normal ($18.5-24.9$)
      - Sobrepeso ($25-29.9$)
      - Obesidad ($\ge 30$)

![Clasificación del estado nutricional de una persona.](resources/images/bmi.jpeg)

---

* **M2: Peso Corporal Ideal (PCI) o _Ideal Body Weight (IBW)_:** El PCI estima el peso teórico que se asocia con el menor riesgo de mortalidad y una mejor salud para un persona.

    Existen diferentes fórmulas para calcular el PCI:

    1. **Fórmula de Devine (1974)**
    Es la más extendida en entornos clínicos para ajustar dosis de medicamentos.

        - **Hombres:** 50 kg + [2.3 × (estatura en pulgadas - 60)]
        - **Mujeres:** 45.5 kg + [2.3 × (estatura en pulgadas - 60)]

    2. **Fórmula de Robinson (1983)**
    Es una variante de Devine más precisa, dando valores más bajos en mujeres y más altos en hombres. 

        - **Hombres:** 52 kg + [1.9 × (estatura en pulgadas - 60)]
        - **Mujeres:** 49 kg + [1.7 × (estatura en pulgadas - 60)]

    3. **Fórmula de Hamwi (1964)**
    Fórmula clásica utilizada por dietistas y nutricionistas debido a su sencillez.

        - **Hombres:** 48.1 kg + [2.7 × (estatura en pulgadas - 60)]
        - **Mujeres:** 45.4 kg + [2.2 × (estatura en pulgadas - 60)]

    4. **Fórmula de Lorentz (1929)**
    Es la fórmula más sencilla de aplicar manualmente ya que utiliza directamente la estatura en centímetros y no requiere conversiones a pulgadas.

        - **Hombres:** $PCI = (Estatura en cm - 100) - \frac{Estatura - 150}{4}$
        - **Mujeres:** $PCI = (Estatura en cm - 100) - \frac{Estatura - 150}{2}$

    **Nota:** Para convertir la estatura de **cm a pulgadas**, hay que dividir los centímetros entre **2.54**.

---

* **M3: Área de Superficie Corporal (ASC) o _Body Surface Area (BSA)_:** El ASC es una medida clínica utilizada para calcular dosis precisas de medicamentos, especialmente en quimioterapia y fluidos intravenosos, y para evaluar la severidad de quemaduras.

    La fórmula más común es la de **Mosteller**:

    * **Fórmula (Mosteller):** $BSA = \sqrt{\frac{\text{altura (cm)} \times \text{peso (kg)}}{3600}}$    

---

* **M4: Perímetro Abdominal (PA) o _Waist Circumference_ (WC):** Es la medición lineal de la circunferencia de la cintura. Se considera el indicador clínico directo de grasa visceral más sencillo y aceptado para predecir obesidad abdominal.
  
    * **Valores de Referencia (Riesgo Elevado):**  
      - **Hombres:** $\ge 94\text{ - }102 \text{ cm}$  
      - **Mujeres:** $\ge 80\text{ - }88 \text{ cm}$

---

* **M5: Índice de Cintura-Cadera (ICC) o _Waist-to-Hip Ratio_ (WHR):** Es ICC la relación entre el perímetro de la cintura y el de la cadera. Se utiliza para identificar la distribución de la grasa (cuerpo tipo "manzana" o "pera") y estimar el riesgo de enfermedades cardiovasculares.
  
    * **Fórmula:** $ICC = \frac{\text{Circunferencia de cintura (cm)}}{\text{Circunferencia de cadera (cm)}}$
    * **Valores de Riesgo (OMS):**  
        - **Hombres:** $> 0.90$  
        - **Mujeres:** $> 0.85$

    Tipos de Morfología:

    1.  **Cuerpo en forma de Manzana (Androide):**
        * **Definición:** La grasa se acumula principalmente en la zona abdominal (tronco).
        * **Implicación Clínica:** Mayor riesgo de hipertensión, diabetes tipo 2 y enfermedades cardíacas debido a la cercanía de la grasa a los órganos vitales (grasa visceral).
        * **Criterio:** Se asigna si el ICC supera los límites de la OMS (>0.90 en hombres, >0.85 en mujeres).

    2.  **Cuerpo en forma de Pera (Ginoide):**
        * **Definición:** La grasa se almacena mayoritariamente en la cadera, glúteos y muslos.
        * **Implicación Clínica:** Generalmente asociada a un menor riesgo metabólico que la forma de manzana, aunque puede relacionarse con problemas articulares o varices.
        * **Criterio:** Se asigna si el ICC está dentro de los rangos normales o bajos.

    | Sexo | Rango ICC | Categoría Morfológica | Riesgo de Salud |
    | :--- | :--- | :--- | :--- |
    | **Hombre** | $\le 0.90$ | Pera (Ginoide) | Bajo / Moderado |
    | **Hombre** | $> 0.90$ | **Manzana (Androide)** | **Alto** |
    | **Mujer** | $\le 0.85$ | Pera (Ginoide) | Bajo / Moderado |
    | **Mujer** | $> 0.85$ | **Manzana (Androide)** | **Alto** |

</details>

<details>
<summary><b>Métricas Metabólicas y Nutricionales</b></summary>

* **M6: Tasa Metabólica Basal (TMB) o _Basal Metabolic Rate (BMR)_:** El TMB calcula la cantidad mínima de energía (calorías) que el cuerpo necesita en reposo absoluto. 

    Existen diferentes fórmulas para calcular el PCI:

    1. **Ecuación de Mifflin-St Jeor**
    Es actualmente la más precisa para la población general y la que utilizan la mayoría de calculadoras modernas. 

        - **Hombres:**  `TMB = (10 × peso en kg) + (6.25 × altura en cm) - (5 × edad en años) + 5`
        - **Mujeres:**  `TMB = (10 × peso en kg) + (6.25 × altura en cm) - (5 × edad en años) - 161`

    2. **Ecuación de Harris-Benedict (revisada)**
    Es el método clásico. La versión original de 1919 fue revisada en 1984 por Roza y Shizgal para mejorar su exactitud.

        - **Hombres:**  `TMB = 88.362 + (13.397 × peso en kg) + (4.799 × altura en cm) - (5.677 × edad en años)`
        - **Mujeres:**  `TMB = 447.593 + (9.247 × peso en kg) + (3.098 × altura en cm) - (4.330 × edad en años)`

    3. **Ecuación de Katch-McArdle**
    A diferencia de las anteriores, esta fórmula no distingue entre sexos, sino que utiliza la Masa Corporal Magra (peso sin grasa). Es ideal si conoces tu porcentaje de grasa corporal.
        - `TMB = 370 + (21.6 × Masa Corporal Magra en kg)`
            > **Nota:** Masa Magra = Peso total × (1 - % de grasa decimal)

    4. **Ecuación de la OMS (FAO/WHO/UNU)**
    Utilizada a menudo en estudios de salud pública, divide el cálculo por rangos de edad específicos: 

        | Edad (Años) | Hombres | Mujeres |
        | :--- | :--- | :--- |
        | **18 – 30** | `(15.057 × peso) + 692.2` | `(14.818 × peso) + 486.6` |
        | **30 – 60** | `(11.472 × peso) + 873.1` | `(8.126 × peso) + 845.6` |
        | **> 60** | `(11.711 × peso) + 587.7` | `(9.082 × peso) + 658.5` |

---

* **M7: Gasto Energético Diario Total (GEDT) o _Total Daily Energy Expenditure (TDEE)_:** El TDEE es la cantidad total de calorías que el cuerpo quema en 24 horas. Suma el metabolismo basal (funciones vitales en reposo), la actividad física, la digestión y el movimiento cotidiano. Es esencial para ajustar la nutrición (perder, ganar o mantener peso).

    Para obtener las calorías totales que quemas al día, multiplica tu **TMB** por tu nivel de actividad:

    - **Sedentario** (poco/nada de ejercicio): `TMB × 1.2`
    - **Ligero** (ejercicio 1-3 días/semanas): `TMB × 1.375`
    - **Moderado** (ejercicio 3-5 días/semana): `TMB × 1.55`
    - **Fuerte** (ejercicio 6-7 días/semana): `TMB × 1.725`
    - **Muy fuerte** (atleta o trabajo físico pesado): `TMB × 1.9`

</details>

<details>
<summary><b>Métricas Clínicas, Cardiovasculares, y de Función Orgánica</b></summary>

Estas métricas requieren datos de signos vitales o resultados de laboratorio.

* **M8: Presión Arterial Media (PAM) o _Mean Arterial Pressure_ (MAP):** Representa la presión promedio en las arterias de un paciente durante un ciclo cardíaco completo. Se considera un mejor indicador de la perfusión (entrega de sangre) a los órganos vitales que la presión sistólica por sí sola. Un valor mínimo de 60-65 mmHg es necesario para mantener los órganos sanos.
  
    **Fórmula:** $PAM = \frac{PAS + 2(PAD)}{3}$  
    *(Donde PAS = Presión Arterial Sistólica y PAD = Presión Arterial Diastólica)*.

--- 

* **M9: Índice de Adiposidad Visceral (VAI) o _Visceral Adiposity Index_ (VAI):** Es un indicador empírico que estima la función del tejido adiposo visceral y el riesgo cardiometabólico. Combina medidas físicas (IMC y CC) con parámetros lipídicos (Triglicéridos y HDL).
  
    **Fórmulas:**  
        - **Hombres:** $VAI = \left( \frac{CC}{39.68 + (1.88 \times IMC)} \right) \times \left( \frac{TG}{1.03} \right) \times \left( \frac{1.31}{HDL} \right)$  
        - **Mujeres:** $VAI = \left( \frac{CC}{36.58 + (1.89 \times IMC)} \right) \times \left( \frac{TG}{0.81} \right) \times \left( \frac{1.52}{HDL} \right)$  
    *(Donde CC = Circunferencia de Cintura en cm, TG = Triglicéridos y HDL en mmol/L)*.

--- 

* **M10: Tasa de Filtración Glomerular Estimada (eGFR) o _Estimated Glomerular Filtration Rate_ (eGFR):** Es el "estándar de oro" para evaluar qué tan bien están filtrando la sangre los riñones. Es vital para la detección de la Enfermedad Renal Crónica (ERC) y para ajustar dosis de fármacos.
  
    **Fórmulas Comunes:**  
      * **Cockcroft-Gault (Clásica):** $\frac{(140 - \text{edad}) \times \text{peso}}{72 \times \text{creatinina}} \times (0.85 \text{ si es mujer})$.  
      * **CKD-EPI (Moderna):** Utiliza logaritmos y variables de raza/sexo para mayor precisión (es la recomendada actualmente en software clínico).  
    * **Entradas necesarias:** Creatinina sérica (mg/dL), edad, sexo y etnia.  

--- 

* **M11: Escala NEWS2 o _National Early Warning Score 2_:** Es un sistema de puntuación estandarizado para detectar el deterioro clínico agudo en pacientes adultos. En lugar de una fórmula aritmética simple, es un **sistema de puntos acumulativo** basado en rangos fisiológicos.
  
    **Parámetros Evaluados (7):**
      1. Frecuencia respiratoria.
      2. Saturación de oxígeno.
      3. Uso de oxígeno suplementario (Sí/No).
      4. Presión arterial sistólica.
      5. Frecuencia cardíaca (Pulso).
      6. Nivel de conciencia (Escala ACVPU).
      7. Temperatura.
    * **Lógica de Software:** El sistema suma puntos (0 a 3) por cada parámetro que se desvíe de lo normal. Un puntaje de 5 o más es una "Alerta Roja" que requiere respuesta urgente.

</details>


## Plan de pruebas

Para garantizar que la calculadora sea fiable y segura, se han definido los siguientes casos de prueba divididos por categorías:

<details>
<summary><b>Pruebas de Cálculo del Índice de Masa Corporal (IMC o BMI)</b></summary>

* **Cálculo correcto:** Se comprueba que, al introducir un peso y altura normales, el resultado sea el esperado matemáticamente.
* **Protección ante datos imposibles:**
    * El sistema debe rechazar pesos menores a 1 kg o mayores a 700 kg.
    * El sistema debe rechazar alturas menores a 30 cm o mayores a 300 cm.
* **Protección ante errores de escritura:** Se verifica que no se permitan valores negativos o iguales a cero.

</details>

<details>
<summary><b>Pruebas de Cálculo del Peso Corporal Ideal (IBW - Lorentz)</b></summary>

* **Cálculos correctos:** Se comprueba que el cálculo devuelve el valor esperado usando la fórmula de Lorentz tanto para hombres como para mujeres con una altura estándar.
* **Validación de sexo:** El sistema debe lanzar un error si se introduce un identificador de sexo no válido (distinto de 'm' o 'f', como letras mayúsculas, otros caracteres o cadenas vacías).
* **Protección ante datos biológicamente imposibles:**
    * El sistema rechaza alturas por debajo del límite biológico mínimo (menores a 30 cm).
    * El sistema rechaza alturas por encima del límite biológico máximo (mayores a 300 cm).

</details>

<details>
<summary><b>Pruebas de Cálculo del Índice de Adiposidad Visceral (VAI)</b></summary>

* **Cálculos correctos:** Se verifica que la fórmula se aplique correctamente tanto para hombres como para mujeres con datos de salud normales.
* **Validación de sexo:** Al igual que en el IBW, se rechazan entradas de sexo que no sean estrictamente 'm' o 'f'.
* **Protección ante valores nulos o negativos:** Se verifica que el sistema lance un error si el IMC, la circunferencia de la cintura (CC), los triglicéridos (TG) o el colesterol (HDL) son iguales o menores a cero.
* **Protección ante límites biológicos excedidos:** El sistema debe bloquear el cálculo y avisar al usuario si los parámetros superan los límites humanos realistas:
    * IMC mayor a 150.
    * Circunferencia de cintura mayor a 300 cm.
    * Triglicéridos mayores a 20 mmol/L.
    * Colesterol HDL mayor a 5 mmol/L.

</details>

<details>
<summary><b>Pruebas de Clasificación del Estado de Salud basado en el IMC/BMI</b></summary>

* **Clasificación correcta por rangos:** Se comprueba que, al introducir valores de IMC dentro de cada intervalo, la etiqueta devuelta sea la adecuada según la clasificación.

    * **Delgadez severa (Severe thinness):** IMC menor que 16.
    * **Delgadez moderada (Moderate thinness):** IMC desde 16 hasta justo antes de 17.
    * **Delgadez leve (Mild thinness):** IMC desde 17 hasta justo antes de 18.5.
    * **Normal (Normal):** IMC desde 18.5 hasta justo antes de 25.
    * **Sobrepeso (Overweight):** IMC desde 25 hasta justo antes de 30.
    * **Obesidad tipo I (Obese class I):** IMC desde 30 hasta justo antes de 35.
    * **Obesidad tipo II (Obese class II):** IMC desde 35 hasta justo antes de 40.
    * **Obesidad tipo III (Obese class III):** IMC desde 40 en adelante.

* **Pruebas de valores frontera:** Se verifica que el sistema cambie correctamente de etiqueta cuando el IMC está exactamente en los límites entre categorías (por ejemplo, 16, 18.5, 25, 30, 35 y 40).

* **Pruebas de valores intermedios:** Se comprueba el comportamiento con valores representativos dentro del interior de cada intervalo para asegurar la estabilidad de la clasificación.

* **Protección ante datos inválidos:**  
    * El sistema debe rechazar valores de IMC negativos.  
    * El sistema debe rechazar valores de IMC nulos (igual a 0).  
    * El sistema debe rechazar valores de IMC clínicamente incoherentes o absurdamente altos (por ejemplo, superiores a 150).

</details>

<details>
<summary><b>Pruebas de Cálculo del Índice de Adiposidad Visceral (VAI)</b></summary>

* **Cálculo correcto según el sexo del paciente:** Se comprueba que el sistema utiliza la fórmula correcta para cada sexo y devuelve el valor esperado con entradas dentro de rangos normales.

* **Pruebas con valores representativos (interior de rango):** Se prueban varios conjuntos de datos tanto razonables como extremos válidos para asegurar que el valor calculado es el que se pretende obtener.

* **Protección ante errores de escritura:**
    * El sistema debe rechazar **circunferencia de cintura (CC) menor o igual que 0**.
    * El sistema debe rechazar valores de **BMI menor o iguales a 0**.
    * El sistema debe rechazar cualquier valor de **TG menor o igual que 0** .
    * El sistema debe rechazar valores de **HDL menores o iguales a 0** .
    * El sistema debe rechazar valores **extremadamente altos** para las entradas (por ejemplo, BMI > 150), ya que no tienen sentido a nivel clínico.
    * El sistema debe rechazar caracteres de **sexo no válidos** (la entrada debe restringirse a los que hemos definido, se le indicarán expresamente al usuario).


</details>

## Instalación y ejecución

<details>
<summary><b>Python</b></summary>

### Dependencias
- Python 3.13+
- pytest
- coverage
- pytest-cov

### Preparación del entorno
1. Clonar este repositorio: `git clone https://github.com/IngSoftAvanz/healthcalc.git`
2. Desplazarse a la carpeta del proyecto:
   `cd healthcalc/python-project-healthcalc`
3. Crear entorno virtual: `python -m venv env` (esto crea una carpeta `env` para el entorno virtual)
4. Activar el entorno virtual:
    - En Windows: `.\env\Scripts\Activate`
    - En Linux: `. env/bin/activate`
5. Instalar dependencias: `pip install -r requirements.txt`

### Ejecución
- Ejecutar la aplicación: `python main.py <número>`
- Ejecutar los tests: `pytest -v`
- Ejecutar los tests con informe de cobertura: `pytest -v --cov=factorial --cov-report=html tests/`

</details>

<details>
<summary><b>Java</b></summary>

### Dependencias
- Java JDK 18+
- Maven
- JUnit
- Jacoco
  
### Preparación del entorno
1. Clonar este repositorio: `git clone https://github.com/IngSoftAvanz/healthcalc.git`
2. Desplazarse a la carpeta del proyecto:
   `cd healthcalc/java-project-healthcalc`
3. Compilar con Maven: `mvn clean compile`

### Ejecución
- Ejecutar la aplicación: Clic en Run usando el IDE.
- Ejecutar los tests: Clic en Run Tests usando el IDE o con Maven: `mvn test`
- Ejecutar los tests con informe de cobertura (previamente configurado en pom.xml): `mvn test`

</details>

<details>
<summary><b>Ejecutable JAR (Recomendado)</b></summary>

### Requisito
- Java Runtime Environment (JRE) 18 o superior instalado en el sistema

### Ejecución del fichero JAR
El fichero ejecutable `HealthCalc.jar` se encuentra en la **raíz del repositorio** y puede ser ejecutado de dos formas:

1. **Doble clic directo:** 
   - En sistemas operativos con GUI (Windows, macOS, Linux con entorno gráfico), simplemente haz doble clic sobre el fichero `HealthCalc.jar` para lanzar la aplicación.

2. **Terminal (Línea de comandos):**
   ```bash
   java -jar HealthCalc.jar
   ```

Esta es la forma más rápida y conveniente de ejecutar la aplicación, ya que no requiere compilación ni herramientas adicionales, únicamente Java instalado.

</details>

---

## Resultados de pruebas, BDD y cobertura

Siguiendo la metodología TDD y el patrón **AAA (Arrange, Act, Assert)**, el proyecto combina pruebas unitarias (JUnit 5) y pruebas funcionales BDD (Cucumber).

### Tipos de test implementados

1. **Pruebas unitarias (JUnit 5)**
    - Verifican el cálculo matemático de cada métrica.
    - Verifican validaciones de entrada (rangos, negativos, cero, sexo inválido, límites biológicos).
    - Incluyen pruebas de frontera y casos representativos.

2. **Pruebas BDD (Cucumber + Gherkin en español)**
El uso de BDD permite validar el comportamiento del sistema desde el punto de vista del usuario final, asegurando que las funcionalidades implementadas cumplen con los requisitos definidos.
    - Definen comportamiento funcional desde escenarios de usuario.
    - Se ejecutan con `RunCucumberTest` y recorren los tres ficheros:
      - `src/test/resources/features/BMI.feature`
      - `src/test/resources/features/IW.feature`
      - `src/test/resources/features/VAI.feature`
    - Generan informe HTML de ejecución en:
      - `java-project-healthcalc/target/reporte.html`

3. **Cobertura de código (JaCoCo)**
    - Se ejecuta automáticamente al lanzar `mvn test`.
    - Informe HTML disponible en:
      - `java-project-healthcalc/target/site/jacoco/index.html`

### Resumen de ejecución actual (Java)

- Comando: `mvn test`
- Resultado: **BUILD SUCCESS**
- Total: **139 tests**
- Fallos: **0**
- Errores: **0**
- Desglose relevante:
  - **Cucumber (BMI + IW + VAI): 53 escenarios**
  - **JUnit (unitarios): 86 tests**

### Detalle de Métricas: tests realizados

#### BMI (Body Mass Index)

**Enlace al fichero BDD:** [`BMI.feature`](java-project-healthcalc/src/test/resources/features/BMI.feature)

**Historia de Usuario 1: Calcular BMI**
> **As a** healthcare professional,
> **I want** to calculate a patient's BMI using their weight and height,
> **So that** I can obtain a standardized numerical value of their body mass.

*Escenarios BDD:*
- Escenario: Calcular BMI con datos válidos
- Escenario: Calcular BMI con altura nula
- Escenario: Calcular BMI con peso negativo
- Escenario: Calcular BMI con peso biológicamente imposible

**Historia de Usuario 2: Clasificación Completa (Full) del BMI**
> **As a** healthcare professional,
> **I want** to get the full clinical classification based on the BMI value,
> **So that** I can evaluate the patient's nutritional status.

*Escenarios BDD:*
- Esquema del escenario: Clasificación completa del BMI (Cubre: *Severe thinness, Moderate thinness, Mild thinness, Normal weight, Overweight, Obese Class I, Obese Class II, Obese Class III*).

**Unitarios (`BMITest`)**
- Casos correctos matemáticos para el BMI y su clasificación.
- Pruebas de frontera y valores límite exactos para cada rango de clasificación.
- Validaciones de valores nulos, negativos y límites biológicos excedidos.

#### IW (Ideal Weight)

**Enlace al fichero BDD:** [`IW.feature`](java-project-healthcalc/src/test/resources/features/IW.feature)

**Unitarios (`IWTest`)**
- Casos correctos para hombre y mujer.
- Validación de sexo inválido.
- Validación de altura negativa, cero y fuera de rango biológico.
- Casos de frontera y ejemplos representativos.

**BDD (`IW.feature`)**
- Escenarios de éxito (hombre/mujer).
- Escenarios de error por sexo inválido.
- Escenarios de error por altura negativa y límites biológicos.
- Esquemas de escenario con múltiples ejemplos.

#### VAI (Visceral Adiposity Index)

**Enlace al fichero BDD:** [`VAI.feature`](java-project-healthcalc/src/test/resources/features/VAI.feature)

**Unitarios (`VAITest`)**
- Cálculo correcto para hombre y mujer.
- Validaciones de sexo inválido.
- Validaciones de TG, HDL, IMC y cintura con valores nulos/negativos.
- Validaciones de límites biológicos máximos.

**BDD (`VAI.feature`)**
- Escenarios de éxito para ambos sexos.
- Escenarios de error para entradas inválidas.
- Escenarios con ejemplos para límites biológicos (TG, HDL, IMC, cintura).

### Estado pendiente / mejoras recomendadas

Aunque los tests actuales pasan y cubren correctamente BMI, VAI e IW, quedan mejoras posibles:

1. **Soft limits (avisos clínicos)**
    - Actualmente se validan hard limits (bloqueo), pero no hay flujo de aviso para valores inusuales no bloqueantes.

2. **Trazabilidad requisito → test**
    - Añadir una tabla explícita de trazabilidad para mapear cada requisito funcional/no funcional con sus tests unitarios y BDD.

3. **Estandarizar reportes en CI**
    - Publicar automáticamente `target/reporte.html` (Cucumber) y `target/site/jacoco/index.html` (JaCoCo) en pipeline.
### Evidencias de ejecución BDD

A continuación se muestran las capturas de la ejecución exitosa de todos los tests BDD implementados en la Práctica 3:

**1. Ejecución de escenarios BMI:**
![Ejecución BMI](doc/P3/bdd_bmi.png)

**2. Ejecución de escenarios IW:**
![Ejecución IW](doc/P3/bdd_iw.png)

**3. Ejecución de escenarios VAI:**
![Ejecución VAI](doc/P3/bdd_vai.png)

**4. Resumen final (Build Success):**
![Resumen de ejecución](doc/P3/bdd_summary.png)


## Patrones de diseño

### Patrón Singleton

Nos permite garantizar que solo hay una única instancia de la calculadora que sirve como acceso global.

![Singleton](design_patterns/singleton.png)

### Patrón Adapter

Hemos creado un adaptador para poder trabajar con datos en las unidades especificadas por el hospital (y también el formato de respuesta esperado) sin tener que reimplementar nuestra calculadora al completo. Para la respuesta del BMI hemos incluido una clase que hace las veces de tupla, combinando el resultado numérico del cálculo y su clasificación asociada. Todo basandonos en el contrato proporcionado por el hospital.

![Adapter](design_patterns/adapter.png)

### Patrón Decorator

El patrón Decorator permite agregar funcionalidades adicionales a objetos en tiempo de ejecución de manera dinámica, sin modificar su estructura original. En nuestro caso, hemos implementado `HealthHospitalStatsDecorator` para añadir la capacidad de recopilar estadísticas sobre los pacientes (altura media, peso media, IMC medio, etc.) mientras se mantienen todas las funcionalidades del adaptador original.

La clase `HealthHospitalStatsDecorator` envuelve una instancia de `HealthHospital` y registra las estadísticas cada vez que se invoca un método, proporcionando una interfaz adicional `HealthStats` que expone métodos para acceder a estas métricas agregadas.

![Decorator](design_patterns/decorator.png)

### Patrón Strategy
Nos permite definir diferentes versiones de la calculadora (europea y americana) y gestionar varios idiomas de forma intercambiable. Al encapsular cada lógica en una estrategia específica, el sistema puede cambiar su comportamiento en tiempo de ejecución sin modificar el código original, cumpliendo con el principio Open/Closed y reutilizando la instancia única del Singleton.
![Strategy](design_patterns/strategy.png)

### Diagrama Global Combinado
El siguiente diagrama ilustra la arquitectura global de la aplicación, mostrando cómo los diferentes patrones de diseño (Singleton, Adapter, Decorator y Strategy) interactúan entre sí y son orquestados a través de la clase `Main`.

![Diagrama Global](design_patterns/diagrama_global.png)
