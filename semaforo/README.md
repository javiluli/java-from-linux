# Java en Linux avanzado

Se plante el siguiente ejercicio para la posterior explicacion del uso del entorno de desarrollo.

## **Ejercicio de prueba**

Un profesor de autoescuela pretende enseñar a un estudiante la combinación de colores entre los que puede ir cambiando un semáforo. En el aula existe unordenador que simula el comportamiento del semáforo, al que podemos consultar el color que tiene en cada momento.

En la secuencia de ejecución, el profesor preguntará al estudiante el color que tiene el semáforo. Para responder, el estudiante obtiene el resultado delordenador.

## **Solucion propuesta al ejercicio**

Cuando damos solución a un problema propuesto mediante un programa, éste puede ser implementado de muy diversas formas. Cualquiera de ellas seráigualmente válida siempre y cuando cumpla los requisitos que se le piden (funcionales, de rendimiento, disponibilidad ...).

---

_A continuación se propone una de las posibles soluciones._

## 1. Identificando actores / objetos involucrados

De acuerdo a lo solicitado en el enunciado, es posible plantear el problema haciendo uso de tres actores (profesor, estudiante, ordenador). Para cada uno deellos se desarrollará una clase Java.

Se crearán cinco ficheros de código fuente, que una vez compilados nos proporcionarán otros tantos ficheros en código intermedio (bytecode de Java).

Para la ejecución del programa, el intérprete de Java hará uso de los ficheros bytecode creados, junto a otras funciones pertenecientes a las bibliotecasde Java.

Cuando queremos implementar una solución, se crea un “proyecto”, cuyos contenidos quedan almacenados en el sistema de archivos del ordenador en undirectorio (semaforo en nuestro caso).

A partir de aquí, se pueden ver subdirectorios, que organizan los diferentes archivos del proyecto en paquetes (paquetes clases y principal para esteejemplo).

Por último, cada paquete puede contener un conjunto de ficheros, donde se implementan las clases. Podrá ser una o varias por fichero (típicamente fichero.java implementa una clase).

## 2. Descripción de las clases

### Clase clasecolor (clasecolor.java)

```java
package principal;
import clases.profesor;
  // Clase color, el profesor pregunta a un alumno por un color entre (rojo, amarillo y verde)
public class clasecolor {
  public static void main(String[] args) {
    profesor teacher = new profesor();
    String color = teacher.preguntacolor();
    System.out.println("La respuesta recibida es:" + color);
  }
}
```

> _Datos a tener en cuenta con respecto a la clase **clasecolor**:_
>
> - La clase clasecolor está contenida en el fichero clasecolor.java y se incluye en un paquete llamado principal.
> - Como parte del código de la clase, se va a utilizar la clase profesor que forma parte del paquete clases.
> - El inicio del programa se lleva a cabo en esta clase, puesto que incluye la función main.
> - De la clase profesor se crea un objeto llamado teacher.
> - Desde la clase clasecolor se envía un evento a la clase profesor (a través del objeto teacher), para que esta última ejecute el método preguntacolor.
> - También utilizamos funciones proporcionadas en las librerías de Java, como println que se encuentra en el paquete Syste.out.

---

### Clase ordenador (ordenador.java)

```java
package clases;

import java.util.Random;

public class ordenador {
  public ordenador() {
  }

  public String color() {
    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(3);
    if (randomInt == 0) {
      return "rojo";
    } else if (randomInt == 1) {
      return "amarillo";
    } else {
      return "verde";
    }
  }
}
```

> _Datos a tener en cuenta con respecto a la clase **ordenador**:_
>
> - La clase ordenador utiliza la función Random, incluida en la biblioteca java.util.Random para obtener un número aleatorio entre 0 y 2.
> - El método color devuelve una de las siguientes cadenas de caracteres (“rojo”, “amarillo”, “verde”), instrucción return (9, 11, 13).

---

### Clase persona (persona.java)

```java
package clases;

public class persona {
  int i_Edad;
  String s_Nombre;
}
```

> _Datos a tener en cuenta con respecto a la clase **persona**:_
>
> - La clase persona no va a ser instanciada directamente en el programa (crear un objeto de la clase). Se trata de una clase padre, que va a ser utilizada porlas clases estudiante y profesor para heredar sus características.
> - En esta clase se definen las variables de clase int i_Edad y s_Nombre (5, 6).

---

### Clase estudiante (estudiante.java)

```java
package clases;

import clases.ordenador;

public class estudiante extends persona {
  int i_Curso;

  public estudiante() {
    i_Edad = 25;
    s_Nombre = "Luis";
    i_Curso = 1;
  }

  public void presentarse() {
    System.out.println("Soy " + s_Nombre + " Alumno de " + Integer.toString(i_Curso) + " y tengo una edad de " + Integer.toString(i_Edad));
  }

  public String preguntacolor() {
    presentarse();
    ordenador mipc = new ordenador();
    return mipc.color();
  }
}
```

> _Datos a tener en cuenta con respecto a la clase **estudiante**:_
>
> - Esta clase hereda de la clase persona, claúsula extends (2).
> - En la clase estudiante se pueden utilizar las propiedades y métodos definidos en la clase padre (6, 7, 11)
> - Además puede incluir otros métodos y propiedades propias (4)
> - Si hubiéramos sobrescrito una de las propiedades o métodos del padre, en la propia clase, ésta utilizaría sus propios métodos y propiedades en lugar de los heredados.

---

### Clase profesor (profesor.java)

```java
package clases;

import clases.estudiante;
import clases.persona;

public class profesor extends persona {
  public profesor() {
  }

  public String preguntacolor() {
    estudiante alumno = new estudiante();
    String colorRec = alumno.preguntacolor();
    return colorRec;
  }
}
```

---

## 3. Uso del traductor/compilador de Java

Como se indicaba anteriormente, cuando se crea un nuevo proyecto Java todos sus recursos “cuelgan” de un determinado directorio en el sistema dearchivos ("/home/debian/Escritorio/semaforo" en nuestro caso).

A partir de aquí, podemos ir distribuyendo los ficheros en diferentes paquetes, que corresponderán a subdirectorios del directorio proyecto("/home/debian/Escritorio/semaforo"). Así hemos incluido los ficheros ordenador.java, persona.java, profesor.java y estudiante.java en el paquete clases.Además de distribuir los paquetes en el directorio correspondiente, es necesario indicarlo en el código (ver instrucción package).

Para utilizar cada una de estas clases en otros ficheros .java deberemos importarlos (instrucción import). Si dos ficheros forman parte del mismo paquete,no es necesario que sean importados.

El fichero de inicio del programa clasecolor.java (incluye la función main), y forma parte del paquete principal.

Para obtener el código bytecode del fichero clasecolor.java:

- Acceder al directorio de inicio del proyecto desde un terminal Linux.
- Llamar al compilador (traductor) de Java "javac" indicando la ruta relativa del fichero que queremos compilar. En nuestro caso “javacprincipal/clasecolor.java”.
- Como resultado obtenemos el fichero interpretable por Java clasecolor.class

Puedes ver el proceso en la siguiente imagen.

Para que el compilador funcione tal y como se ha descrito en este punto, será necesario que se hayan metido las variables de entorno tal y como se haindicado en otras prácticas ya realizadas.

**NOTA**: la compilación del fichero principal "javac principal/clasecolor.java" puede ya compilar todos los ficheros del proyecto al ser unos dependientes deotros. En caso contrario, compilaremos el resto uno a uno en el siguiente orden: persona.java, ordenador.java, estudiante.java, profesor.java yclasecolor.java.

Una vez disponibles todos los ficheros bytecode, procedemos a la ejecución del programa desde un terminal, vamos al directorio de inicio del proyecto yejecutamos el intérprete java: “java principal/semaforo”.

## 4. Empaquetar .class en fichero .jar

Java nos permite empaquetar todos los ficheros (.class, junto a otros recursos utilizados) del proyecto en uno único con extensión .jar (Java ARchives).Además el formato del fichero .jar es comprimido, por lo que ocupa menos espacio que la información original.

A continuación se indica el procedimiento para llevarlo a cabo.

### 4.1. Crear el fichero MANIFEST.MF

Crear un directorio META-INF **(¡las mayúsculas son importantes!)** y dentro un fichero MANIFEST.MF. Este fichero indica, entre otras cosas, cuál serála clase principal. A menudo el fichero MANIFEST.MF contiene una única línea:

```
Main-Class: principal.clasecolor
```

donde se indica que clasecolor.class (del paquete principal) es la clase que contiene el método main.

**NOTA**: el fichero MANIFEST.MF se puede crear con cualquier editor de texto. Hay que introducir un fin de línea (enter) tras la última línea con texto.

### 4.2. Crear el fichero semaforo.jar

El paquete JDK proporciona una serie de ejecutables localizados su ruta /bin ("/usr/java/jdk1.80_107/bin” en nuestra distribución). Ya se ha trabajado conalgunos de ellos como el compilador de java (javac) y con el intérprete (java) en prácicas anteriores. Otro de los programas a nuestra disposición es jar,que permite empatequetar todos los recursos de un proyectos Java en un fichero con extensión .jar.

Desde consola del sistema, ejecutar el programa jar con los siguientes parámetros:

```
jar cmf META-INF/MANIFEST.MF semaforo.jar clases/*.class principal/*.class
```

---

Observa en la imagen una ejecución del proyecto utilizando el fichero .jar obtenido:

```
java –jar semaforo.jar
```

---

El programa **jar** también nos permite ver el contenido de proyecto comprimidos .jar:

```
jar tf semaforo.jar
```

---

Si deseamos descomprimir la información del fichero .jar, para obtener sus fichero originales:

```
jar xf semaforo.jar
```

---

### 4.3. Script Compilar. sh

Para facilitar la creación de nuestros ficheros interpretables, es útil crear un script que realice todo el proceso de una sola vez. En nuestro proyecto podríaser **compilar. sh**, al que se deberá dar permisos de ejecución. Se considera que al compilar el fichero principal clasecolor.java, el resto se hace en cascadagracias a las dependencias existentes entre clases.
