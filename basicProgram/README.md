# Java en Linux

## 1. Introduccion

En el caso del lenguaje Java, se indicaba en temas anteriores que tras la compilación del código fuente se obtiene otro llamado bytecode. Para que el
bytecode pueda ser interpretado, el equipo deberá tener instalado el JRE (Java Runtime Environment), definido en wikipedia como sigue:

> JRE es un conjunto de utilidades que permite la ejecución de programas Java.
> En su forma más simple, el entorno en tiempo de ejecución de Java está conformado por una Máquina Virtual de Java o JVM, un conjunto de
> bibliotecas Java y otros componentes necesarios para que una aplicación escrita en lenguaje Java pueda ser ejecutada. El JRE actúa como un
> "intermediario" entre el sistema operativo y Java.
> La JVM es el programa que ejecuta el código Java previamente compilado (bytecode) mientras que las librerías de clases estándar son las que
> implementan el API de Java. Ambas JVM y API deben ser consistentes entre sí, de ahí que sean distribuidas de modo conjunto.
> Un usuario sólo necesita el JRE para ejecutar las aplicaciones desarrolladas en lenguaje Java, mientras que para desarrollar nuevas aplicaciones en
> dicho lenguaje es necesario un entorno de desarrollo, denominado JDK, que además del JRE (mínimo imprescindible) incluye, entre otros, un
> compilador para Java.

El JRE es desarrollado y distribuido de forma gratuita por Oracle.
Pero si nuestra voluntad es convertirnos en desarrolladores de código Java, no será suficiente el JRE, tendremos que instalar el JDK (Java Development
Kit), también distribuido por Oracle y que wikipedia define como:

> Java Development Kit (JDK) es un software que provee herramientas de desarrollo para la creación de programas en Java. Puede instalarse en una
> computadora local o en una unidad de red.
> Los programas más importantes que se incluyen
>
> - appletviewer.exe: es un visor de applets para generar sus vistas previas, ya que un applet carece de método main y no se puede ejecutar con el programa java.
> - javac.exe: es el compilador de Java.
> - java.exe: es el masterescuela (intérprete) de Java.
> - javadoc.exe: genera la documentación de las clases Java de un programa.

## 2. Instalacion del JDK

La instalación del JDK se llevará a cabo en la máquina Ubuntu 18.04 LTS. A fecha de noviembre 2019, la versión JDK es [jdk-8u171-linux-x64](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

Nota: probablemente con posterioridad a esta fecha habrá nuevas versiones del JDK que podrás obtener en el área de descargas de Oracle.

### Pasos a seguir para la instalacion del JDK en la máquina Ubuntu

- Abrir terminal linux.
- Ganar privilegios de administrador.

  `sudo su` + Contraseña ubuntu (en nuestra máquina)

- Acceder a la ruta donde está descargado el instalable.

  `cd` + ruta_instalable

- Asignar permisos de ejecución.

  `chmod 755 jdk-8u102-linux-x64.tar.gz`

- Crear la ruta destino de la instalación.

  `mkdir /usr/java`

- Descomprimir el jdk descargado.

  `tar zxvf jdk-8u102-linux-x64.tar.gz -C /usr/java`

- Comprobar que se ha creado la estructura del jdk.

  `ls /usr/java`

## 3. Configuración de las variables de entorno

El JDK podrá ser utilizado por diversas aplicaciones entre las que se encuentran los IDEs. Al ser la ubicación del JDK en el sistema de archivosconfigurable durante la instalación, las aplicaciones que hacen uso del mismo no saben donde localizarlo.

Para resolverlo, se definen variables de entorno, cuyo nombre será de conocimiento público y por tanto común para cualquier aplicación que quierautilizarlas. En particular, las propuestas a continuación tienen como cometido informar de las rutas elegidas en la instalación para el JDK y facilitar elacceso a sus ejecutables.

### Configuración de las variables de entorno en la máquina Debian

_Desde un terminal en **Linux** se dan de alta/modifican las variables de entorno **JAVA_HOME** y **PATH**._

Abrir terminal linux.

- Ganar privilegios de administrador.

  `sudo su +` Contraseña ubuntu (en nuestra máquina)

- Con un editor de texto (por ejemplo nano), acceder al fichero/etc/bash.bashrc.

  `nano /etc/bash.bashrc`

- Introducir las variables de entorno:

  `JAVA_HOME=/usr/java/jdk1.8.0_171`

  `PATH=\$PATH:/usr/java/jdk1.8.0_171/bin`

Tras reiniciar, se puede comprobar que han quedado activadas desde terminal con los comandos:

```bash
echo \$PATH
```

```bash
echo \$JAVA_HOME
```

## 4. Crear, compilar y ejecutar un programa

Vamos a crear, compilar y ejecutar nuestro primer programa en java.

- Utilizando un editor de textos (por ejemplo nano) crear el fichero Hola.java con el siguiente código:

```java
public class Hola {
  public static void main(String[ ] args)
  System.out.println("Hola");
  }
}
```

Salva el archivo en alguna ruta de tu sistema de archivos. Por ejemplo "/home/ubuntu/Escritorio".

- Abrir un terminal en Linux y moverse a la ruta donde se ha creado el fichero Hola.java.
- Ejecutar el comando javac Hola.java para compilar.
- Obtenemos el fichero Hola.classInterpretar Hola.class con el comando java Hola
