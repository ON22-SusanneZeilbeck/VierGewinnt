import java.io.*;

/* ****************
 * Gruppenmitglieder: Nele Ries, Susanne Zeilbeck, Celine Haupenthal, Vivienne Sester, Jacqueline Bohn   * 
 * Studiengang: Onlinemedien                                                                             * 
 * 2022: ON22                                                                                            * 
 *************** */

//Erstellung des Spiels "Vier gewinnt"
public class VIER_Gewinnt {

    // Anzeige der Hilfestellung mit Spielbeschreibung und Spielregeln
    public static void hilfestellung() {

        System.out
                .println("Hilfestellung"
                        + "\n"
                        + "Vier Gewinnt - Spielbeschreibung & Spielregeln"
                        + "\n"
                        + "Spielbeschreibung und Regeln:"
                        + "\n"
                        + "Das Spielbrett besteht aus sieben senkrechten Spalten  und sechs waagerechten Reihen. "
                        + "\n"
                        + "Jeder Spieler besitzt 21 Spielsteine des selben Symbols (hier: Spieler 1 kenntlich durch X und Spieler 2 durch O)."
                        + "\n"
                        + " Beide Spieler lassen abwechselnd  Spielsteine  in eine Spalte fallen (durch Eingabe der gewuenschten Spalte, Bsp. 3 + Enter), "
                        + "\n"
                        + "dieser besetzt den untersten freien Platz der Spalte. Gewinner ist der Spieler, der es als erster schafft, "
                        + "\n"
                        + "vier seiner Spielsteine waagerecht, senkrecht oder diagonal in eine Linie zu bringen. Das Spiel endet unentschieden, "
                        + "\n"
                        + "wenn das Spielbrett komplett voll ist, ohne dass ein Spieler eine Viererlinie gebildet hat.");

    }

    // Nummerierte Zeile Über den Spalten des Spielfeldes
    // Gibt die Zahlen 1 bis 7 mit Leerzeichen aus
    public static void ueberschrift(int n) {

        for (int i = 1; i <= n; i++) {

            System.out.print(i + "   ");
        }
        System.out.println();
    }

    // Festlegung des Spielablaufs
    public static int spielablauf(char spielfeld[][], String name,
            char spielsteinchen, int i) {

        String spalte = spielereingabe();
        int spaltennummer = gueltigeEingabe(spalte);
        volleSpalte(spaltennummer, spielfeld);
        spielfeld = spielzug(spielfeld, spaltennummer, spielsteinchen);
        i = gewonnen(spielfeld, spielsteinchen, name, i);
        spielbrettVoll(spielfeld);

        return i;

    }

    // Erzeugung eines leeren Spielbretts
    // "Geruest" des Spielfelds mit 7 Spalten und 6 Zeilen wird im Rahmen eines
    // zweidimensionalen Arrays erzeugt
    public static char[][] spielbrettleer(char spielfeld[][]) {

        ueberschrift(7);

        for (char y = 0; y < spielfeld.length; y++) {
            for (char x = 0; x < spielfeld[y].length; x++) {
                spielfeld[y][x] = (' ');
                System.out.print(spielfeld[y][x] + " | ");
            }
            System.out.println("");
        }
        return spielfeld;

    }

    // Einlesung aus der Konsole und Rueckgabe als String
    public static String spielereingabe() {

        String eingabe = "";
        try {
            BufferedReader Tast = new BufferedReader(new InputStreamReader(
                    System.in));
            eingabe = Tast.readLine();
        }

        catch (Exception e) {

        }
        return eingabe;

    }

    // Umwandlung von String in Ganzzahl
    // Falls String ungueltig ist (bspw. keine Zahl) wird -1111 zurueckgegeben
    public static int Integer_parseInt(String eingabe) {

        int i;
        try {
            i = Integer.parseInt(eingabe);
        } catch (Exception e) {
            i = -1111;
        }
        return i;
    }

    // Auswertung von eingegebener Spaltennummer und Ausgabe des jeweiligen
    // Spielsteins
    public static char[][] spielzug(char spielfeld[][], int spaltennummer,
            char spielsteinchen) {

        for (int y = 5; y >= 0; y--) {
            if (spielfeld[y][spaltennummer] == ' ') {
                spielfeld[y][spaltennummer] = spielsteinchen;
                y = 0;
            }

        }
        return spielfeld;
    }

    // Anzeige des gewählten Spielsteins
    public static char[][] spielfeldBefuellen(char spielfeld[][]) {
        ueberschrift(7);
        for (char y = 0; y < spielfeld.length; y++) {
            for (char x = 0; x < spielfeld[y].length; x++) {
                System.out.print(spielfeld[y][x] + " | ");
            }
            System.out.println(" ");
        }
        return spielfeld;

    }

    // Ueberpruefung ob Eingabe gueltig ist
    // Gueltige Eingaben: Zahlen zwischen 1 bis 7
    public static int gueltigeEingabe(String spalte) {

        int spaltennummer = Integer_parseInt(spalte);

        spaltennummer = spaltennummer - 1;

        while (0 > spaltennummer || spaltennummer >= 7) {
            System.out
                    .println("Die Eingabe war ungültig! Wählen Sie bitte eine andere Spalte zwischen 1 und 7 aus!");
            spalte = spielereingabe();
            spaltennummer = Integer_parseInt(spalte);
            spaltennummer = spaltennummer - 1;
        }

        return spaltennummer;
    }

    // Gewinner Vertikal
    public static int gewonnen_vertikal(char spielfeld[][],
            char spielsteinchen, String name, int i) {

        for (int z = 5; z >= 3; z--) {
            for (int s = 0; s < spielfeld[z].length; s++) {
                if (spielfeld[z][s] == spielsteinchen
                        && spielfeld[z - 1][s] == spielsteinchen
                        && spielfeld[z - 2][s] == spielsteinchen
                        && spielfeld[z - 3][s] == spielsteinchen) {
                    System.out.println(name + " hat gewonnen! Glückwunsch. :)");
                    i = 43;
                }
            }
        }
        return i;

    }

    // Gewinner Waagerecht
    public static int gewonnen_waagerecht(char spielfeld[][],
            char spielsteinchen, String name, int i) {

        for (int z = 0; z < spielfeld.length; z++) {
            for (int s = 0; s <= 3; s++) {
                if (spielfeld[z][s] == spielsteinchen
                        && spielfeld[z][s + 1] == spielsteinchen
                        && spielfeld[z][s + 2] == spielsteinchen
                        && spielfeld[z][s + 3] == spielsteinchen) {
                    System.out.println(name + " hat gewonnen! Glückwunsch. :)");
                    i = 43;
                }
            }
        }
        return i;
    }

    // Gewinner Diagonal von rechts unten nach links oben
    public static int gewonnen_diagonal_1(char spielfeld[][],
            char spielsteinchen, String name, int i) {

        for (int z = 0; z <= 2; z++) {
            for (int s = 0; s <= 3; s++) {
                if (spielfeld[z][s] == spielsteinchen
                        && spielfeld[z + 1][s + 1] == spielsteinchen
                        && spielfeld[z + 2][s + 2] == spielsteinchen
                        && spielfeld[z + 3][s + 3] == spielsteinchen) {
                    System.out.println(name + " hat gewonnen! Glückwunsch. :)");
                    i = 43;
                }
            }
        }
        return i;
    }

    // Gewinner Diagonal von rechts oben nach links unten
    public static int gewonnen_diagonal_2(char spielfeld[][],
            char spielsteinchen, String name, int i) {

        for (int z = 0; z <= 2; z++) {
            for (int s = 6; s >= 3; s--) {
                if (spielfeld[z][s] == spielsteinchen
                        && spielfeld[z + 1][s - 1] == spielsteinchen
                        && spielfeld[z + 2][s - 2] == spielsteinchen
                        && spielfeld[z + 3][s - 3] == spielsteinchen) {
                    System.out.println(name + " hat gewonnen! Glückwunsch. :) ");
                    i = 43;
                }
            }
        }
        return i;
    }

    // Allgemeine "Gewonnen" Methode
    // Aufgerufen wird die passende Methode für den Gewinner:
    // Das Spielfeld wird mit ausgefülten Spielsteinchen gezeigt,
    // der Name des Gewinners wird gezeigt und das Spiel kann erneut starten
    public static int gewonnen(char spielfeld[][], char spielsteinchen,
            String name, int i) {

        i = gewonnen_vertikal(spielfeld, spielsteinchen, name, i);
        i = gewonnen_waagerecht(spielfeld, spielsteinchen, name, i);
        i = gewonnen_diagonal_1(spielfeld, spielsteinchen, name, i);
        i = gewonnen_diagonal_2(spielfeld, spielsteinchen, name, i);

        return i;
    }

    // Pruefung ob Spalte voll ist
    public static int volleSpalte(int spaltennummer, char spielfeld[][]) {
        boolean spaltevoll = spielfeld[0][spaltennummer] != ' ';
        if (spaltevoll) {
            System.out
                    .println(
                            "Die von dir gewählte Spalte ist leider voll. Wähle eine andere Spalte zwischen 1 und 7 aus!");
            String spalte = spielereingabe();
            spaltennummer = gueltigeEingabe(spalte);
            return spaltennummer;
        }
        return spaltennummer;
    }

    // Pruefung ob Spielbrett voll ist
    public static void spielbrettVoll(char spielfeld[][]) {

        boolean vollesBrett = spielfeld[0][0] != ' ' && spielfeld[0][1] != ' '
                && spielfeld[0][2] != ' ' && spielfeld[0][3] != ' '
                && spielfeld[0][4] != ' ' && spielfeld[0][4] != ' '
                && spielfeld[0][5] != ' ' && spielfeld[0][6] != ' ';
        if (vollesBrett) {
            System.out
                    .println("Das Spielbrett ist voll! Das Spiel endet unentschieden!");
        }

    }

    // Hauptmehtode
    // ruft zuvor beschriebene Methoden ab
    // Begrueßungsausgabe in der Konsole
    public static void main(String args[]) {

        char spielfeld[][];
        spielfeld = new char[6][7];
        int j = 0;
        while (j != 2) {
            System.out
                    .println("Herzlich Willkommen zu Vier Gewinnt."
                            + "\n"
                            + "Bitte geben Sie eine 1 ein und bestätigen Sie diese mit Enter um das Spiel zu beginnen."
                            + "\n"
                            + "Bitte geben Sie eine 2 ein und bestätigen Sie diese mit Enter um das Spiel zu verlassen"
                            + "\n"
                            + "Bitte geben Sie eine 3 ein und bestätigen Sie diese mit Enter um die Hilfestellung aufzurufen"
                            + "\n" + "Viel Erfolg und vorallem viel Spass!");

            String eingabe = spielereingabe();
            j = Integer.parseInt(eingabe);

            // Gibt aus, welcher Spieler an der Reihe ist
            // Fordert Spieler zur Eingabe eines Spaltenzahl
            if (j == 1) {
                spielfeld = spielbrettleer(spielfeld);
                for (int i = 0; 43 > i; i++) {
                    if (i % 2 == 0) {
                        System.out
                                .println(
                                        "Spieler 1 ist am Zug, bitte geben Sie die gewünschte Spalte zwischen 1 und 7 ein, und bestätigen diese mit Enter.");
                        i = spielablauf(spielfeld, "Spieler 1", 'X', i);

                    } else if (i % 2 != 0) {
                        System.out
                                .println(
                                        "Spieler 2 ist am Zug, bitte geben Sie die gewünschte Spalte zwischen 1 und 7 ein, und bestätigen diese mit Enter.");
                        i = spielablauf(spielfeld, "Spieler 2", 'O', i);
                    }
                    spielfeld = spielfeldBefuellen(spielfeld);
                }
            } else if (j == 3) {
                hilfestellung();
            } else if (j != 1 && j != 3 && j != 2) {
                System.out.println("Ungültige Eingabe!");
            }
        }
    }
}