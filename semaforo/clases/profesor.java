
public class profesor extends persona {
  public profesor() {
  }

  public String preguntacolor() {
    estudiante alumno = new estudiante();
    String colorRec = alumno.preguntacolor();
    return colorRec;
  }
}