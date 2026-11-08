import java.util.ArrayList;
import java.util.Scanner;

class CalculadoraException extends Exception {
    public CalculadoraException(String mensaje) {
        super(mensaje);
    }
}

class Operacion {
    private String tipoOperacion;
    private double operando1;
    private double operando2;
    private double resultado;

    public Operacion(String tipoOperacion, double operando1, double operando2, double resultado) {
        this.tipoOperacion = tipoOperacion;
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.resultado = resultado;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public double getOperando1() {
        return operando1;
    }

    public void setOperando1(double operando1) {
        this.operando1 = operando1;
    }

    public double getOperando2() {
        return operando2;
    }

    public void setOperando2(double operando2) {
        this.operando2 = operando2;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public void mostrarInformacion() {
        System.out.println("  -> [" + tipoOperacion.toUpperCase() + "] Operandos: " 
                           + operando1 + " y " + operando2 + " | Resultado: " + resultado);
    }
}

class HistorialCalculo {
    private ArrayList<Operacion> listaOperaciones;

    public HistorialCalculo() {
        this.listaOperaciones = new ArrayList<>();
    }

    public void agregarOperacion(Operacion op) {
        this.listaOperaciones.add(op);
    }

    public void mostrarTodos() {
        if (listaOperaciones.isEmpty()) {
            System.out.println("\n[Historial Vacío] Aún no se han registrado operaciones en esta sesión.");
            return;
        }
        System.out.println("\n==========================================");
        System.out.println("    HISTORIAL DE OPERACIONES REGISTRADAS   ");
        System.out.println("==========================================");
        for (Operacion op : listaOperaciones) {
            op.mostrarInformacion();
        }
        System.out.println("==========================================");
    }
}

class Calculadora {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) throws ArithmeticException, CalculadoraException {
        if (b == 0) {
            throw new ArithmeticException("No es posible dividir entre cero.");
        }
        return a / b;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculadora calculadora = new Calculadora();
        HistorialCalculo historial = new HistorialCalculo();

        String[] categoriasDisponibles = {"Suma", "Resta", "Multiplicación", "División"};

        int opcion = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println("            SISTEMA CALCULADORA           ");
            System.out.println("==========================================");
            
            System.out.print("Módulos activos: ");
            for (int i = 0; i < categoriasDisponibles.length; i++) {
                System.out.print("[" + categoriasDisponibles[i] + "] ");
            }
            System.out.println("\n------------------------------------------");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir (controla el error de dividir entre cero)");
            System.out.println("5. Ver Historial de Operaciones");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción (1-6): ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                if (opcion >= 1 && opcion <= 4) {
                    System.out.print("Ingrese el primer número: ");
                    double num1 = Double.parseDouble(scanner.nextLine());

                    System.out.print("Ingrese el segundo número: ");
                    double num2 = Double.parseDouble(scanner.nextLine());

                    double resultado = 0;
                    String nombreOperacion = "";

                    switch (opcion) {
                        case 1:
                            resultado = calculadora.sumar(num1, num2);
                            nombreOperacion = "Suma";
                            break;
                        case 2:
                            resultado = calculadora.restar(num1, num2);
                            nombreOperacion = "Resta";
                            break;
                        case 3:
                            resultado = calculadora.multiplicar(num1, num2);
                            nombreOperacion = "Multiplicación";
                            break;
                        case 4:
                            resultado = calculadora.dividir(num1, num2);
                            nombreOperacion = "División";
                            break;
                    }

                    System.out.println("\n[RESULTADO] El resultado de la " + nombreOperacion + " es: " + resultado);
                    historial.agregarOperacion(new Operacion(nombreOperacion, num1, num2, resultado));

                } else if (opcion == 5) {
                    historial.mostrarTodos();
                } else if (opcion == 6) {
                    System.out.println("\nEjecución finalizada. Gracias por utilizar la Calculadora.");
                } else {
                    System.out.println("\n[ADVERTENCIA] Opción no válida. Ingrese un valor entero entre 1 y 6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("\n[ERROR DE ENTRADA] Debe ingresar un valor numérico válido (no se permiten letras o espacios).");
            } catch (ArithmeticException e) {
                System.out.println("\n[ERROR ARITMÉTICO] " + e.getMessage());
            } catch (CalculadoraException e) {
                System.out.println("\n[ERROR DE DOMINIO] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n[ERROR INESPERADO] Ocurrió una anomalía en el sistema: " + e.getMessage());
            }

        } while (opcion != 6);

        scanner.close();
    }
}
