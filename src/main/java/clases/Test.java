package clases;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.FileNotFoundException;

public class Test {

    public static void main(String[] args) {

        //Inicializacion de las variables
        ArrayList<Integer> vector = new ArrayList<Integer>();
        TSBHeap<Integer> hpAsc = new TSBHeap<>(10, true);
        TSBHeap<Integer> hpDesc = new TSBHeap<>(10, false);

        File arch = new File("src/list[100000].txt");
        try {
            Scanner miScanner = new Scanner(arch);
            while (miScanner.hasNext()) {
                vector.add(miScanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no está");
        }

        for (int i : vector.subList(1, 75000)) {
            if (hpAsc.isEmpty() && hpDesc.isEmpty()) {
                hpDesc.add(i);
            }

            if (hpAsc.size() != hpDesc.size()) {

                if (hpAsc.size() > hpDesc.size()) {
                    if (hpDesc.isEmpty() || i <= hpAsc.get()) {
                        hpDesc.add(i);
                    } else {
                        int aux = hpAsc.get();
                        hpAsc.remove();
                        hpDesc.add(aux);
                        hpAsc.add(i);
                    }
                } else {
                    if (hpAsc.isEmpty() || i >= hpDesc.get()) {
                        hpAsc.add(i);
                    } else {
                        int aux = hpDesc.get();
                        hpDesc.remove();
                        hpAsc.add(aux);
                        hpDesc.add(i);
                    }
                }
            } else {
                if (i <= hpAsc.get()) {
                    hpDesc.add(i);
                } else {
                    int aux = hpAsc.get();
                    hpAsc.remove();
                    hpDesc.add(aux);
                    hpAsc.add(i);
                }
            }
        }

        //System.out.println(vector);
        System.out.println("Heap acendente " + hpAsc.toString());
        System.out.println("Heap descendente " + hpDesc.toString());

        //Calculo de la mediana
        float mediana;
        if (hpAsc.size() == hpDesc.size()) {
            mediana = (float) (hpAsc.get() + hpDesc.get()) / 2;
        } else {
            if (hpAsc.size() > hpDesc.size()) {
                mediana = hpAsc.get();
            } else {
                mediana = hpDesc.get();
            }
        }
        System.out.println("Mediana: " + mediana);


    }
}
