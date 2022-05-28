package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    int queens = 8;
    int[] array = new int[8];

    ArrayList<int[]> posibilidades = new ArrayList<>();
    ArrayList<int[]> entradas = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) {
        Main e = new Main();
        e.check(0);
        e.recibirEntradas();
        e.comparar();

    }

    private void recibirEntradas() {
        Scanner ent = new Scanner(System.in);
        int entrada[] = new int[8];

        try {
            ent = new Scanner(new File("src/entrada.txt"));
        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo");
        }

        while (ent.hasNextInt()) {
            entrada = new int[8];
            for (int i = 0; i < entrada.length; i++) {
                entrada[i] = (ent.nextInt()) - 1;
            }
            entradas.add(entrada);
        }
    }

    private void check(int n) {
        if (n == queens) {
            int posibilidad[] = new int[queens];
            for (int i = 0; i < array.length; i++) {
                posibilidad[i] = array[i];
            }
            posibilidades.add(posibilidad);
            return;
        }

        for (int i = 0; i < queens; i++) {

            array[n] = i;

            if (judge(n)) {

                check(n + 1);
            }
        }
    }

    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private void comparar() {
        int casos[] = new int[entradas.size()];
        int minimo = queens, contador = 0;
        for (int i = 0; i < entradas.size(); i++) {
            minimo = queens;
            for (int j = 0; j < posibilidades.size(); j++) {
                contador = 0;
                for (int k = 0; k < queens; k++) {

                    if (entradas.get(i)[k] == posibilidades.get(j)[k]) {
                        contador++;
                    }
                }
                contador = queens - contador;
                if (contador < minimo) {
                    minimo = contador;
                }
            }
            casos[i] = minimo;
        }

        for (int i = 0; i < casos.length; i++) {
            System.out.println("Case " + (i + 1) + ": " + casos[i]);
        }
    }
}
