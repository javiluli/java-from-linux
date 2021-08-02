
public class estudiante extends persona {
  int i_Curso;

  public estudiante() {
    i_Edad = 25;
    s_Nombre = "Luis";
    i_Curso = 1;
  }

  public void presentarse() {
    System.out.println("Soy " + s_Nombre + " Alumno de " + Integer.toString(i_Curso) + "º y tengo una edad de "
        + Integer.toString(i_Edad) + " años");
  }

  public String preguntacolor() {
    presentarse();
    ordenador mipc = new ordenador();
    return mipc.color();
  }
}