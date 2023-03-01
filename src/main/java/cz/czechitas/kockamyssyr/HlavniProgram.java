package cz.czechitas.kockamyssyr;

import cz.czechitas.kockamyssyr.api.*;

import java.awt.*;
import java.util.Random;

/**
 * Hlaví třída pro hru Kočka–myš–sýr.
 */
public class HlavniProgram {
    private final Random random = new Random();

    private final int VELIKOST_PRVKU = 50;
    private final int SIRKA_OKNA = 1000 - VELIKOST_PRVKU;
    private final int VYSKA_OKNA = 600 - VELIKOST_PRVKU;

    private Cat tom;
    private Mouse jerry;
    private Cheese syr;
    private Meat jitrnice;


    /**
     * Spouštěcí metoda celé aplikace.
     *
     * @param args
     */
    public static void main(String[] args) {
        new HlavniProgram().run();
    }

    /**
     * Hlavní metoda obsahující výkonný kód.
     */
    public void run() {
        tom = vytvorKocku();
        tom.setBrain(new KeyboardBrain(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D));

        jerry = vytvorMys();
        jerry.setBrain(new KeyboardBrain());

        syr = vytvorSyr();
        jitrnice = vytvorJitrnici();


        vytvorVeci(4);
        chytMys();
    }


    int horizontalniRozdil;
    int opakX;
    int vertikalniRozdil;
    int opakY;


    public void chytMys() {

        while (jerry.isAlive() && (jitrnice.isAlive() || syr.isAlive())) {

            prepocitejPozice();

            if (horizontalniRozdil > 0) {
                jdiZaJerrymVpravo(horizontalniRozdil);
            } else if (horizontalniRozdil < 0) {
                jdiZaJerrymDoleva(opakX);
            } else {
                return;
            }
            prepocitejPozice();

            if (vertikalniRozdil > 0) {
                jdiZaJerrymDolu(vertikalniRozdil);
            } else if (vertikalniRozdil < 0) {
                jdiZaJerrymVzuru(opakY);
            } else {
                return;
            }


        }


    }

    private void prepocitejPozice() {
        horizontalniRozdil = jerry.getX() - tom.getX();
        vertikalniRozdil = jerry.getY() - tom.getY();
        opakX = tom.getX() - jerry.getX();
        opakY = tom.getY() - jerry.getY();
    }


    private void jdiZaJerrymVzuru(int opakY) {
        if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnLeft();
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnLeft();
        }
        tom.moveForward(opakY);


    }

    private void jdiZaJerrymDolu(int vertikalniRozdil) {
        if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnLeft();
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnRight();
        }
        tom.moveForward(vertikalniRozdil);


    }


    private void jdiZaJerrymDoleva(int horizontalniRozdil) {
        if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnLeft();
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnRight();
        }
        tom.moveForward(horizontalniRozdil);


    }

    private void jdiZaJerrymVpravo(int opakX) {
        if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnLeft();
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnLeft();
        }
        tom.moveForward(opakX);


    }


    public void vytvorVeci(int pocetStromu) {
        for (int i = 0; i < pocetStromu; i++) {
            vytvorStrom();
        }
        //vytvorSyr();
        // vytvorJitrnici();
    }

    public Tree vytvorStrom() {
        return new Tree(vytvorNahodnyBod());
    }

    public Cat vytvorKocku() {
        return new Cat(vytvorNahodnyBod());
    }

    public Mouse vytvorMys() {
        return new Mouse(vytvorNahodnyBod());
    }

    public Cheese vytvorSyr() {
        return new Cheese(vytvorNahodnyBod());
    }

    public Meat vytvorJitrnici() {
        return new Meat(vytvorNahodnyBod());
    }

    private Point vytvorNahodnyBod() {
        return new Point(random.nextInt(SIRKA_OKNA), random.nextInt(VYSKA_OKNA));
    }

}
