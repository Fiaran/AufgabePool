import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Pool {
    public static void main(String[] args) {

        System.out.println("Willkommen in unserem Schwimmbad!\n" +
                "Wenn Sie einen Liegestuhl reservieren möchten, verwenden Sie die folgenden Befehle:\n" +
                "-> kommen - für Reservierungen\n" +
                "-> weggehen - für den Rückzug\n" +
                "-> anzeigen - um den Status der Sonnenbänke anzuzeigen\n" +
                "-> beenden - wenn Sie sich von unserem System abmelden möchten.\n" +
                "Wir wünschen Ihnen einen angenehmen Aufenthalt!");
        System.out.println("-------------------------------------------");
        System.out.println("Geben Sie die Anzahl der Sonnenliegen ein:");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
// new feature-------------------------------------
        HashMap<Integer, int[]> groups = new HashMap();
        int groupID=1;

// end of new feature-------------------------------

        boolean[] deckChairs = new boolean[n];
        for (int i = 0; i < n; i++) {
            deckChairs[i] = false;
        }

        while (true) {
            System.out.println("Geben Sie den Befehl ein: ");
            String command = input.next();

            String income = "kommen";
            String leave = "weggehen";
            String show = "anzeigen";
            String end = "beenden";

            if (command.equals(income)) {

                System.out.println("Geben Sie die Anzahl der Personen ein:");
                int persons = input.nextInt();
                int index = findFreePlaces(deckChairs, persons);

                // new feature---------------------------------------------------------
                int startIndex = findFreePlaces(deckChairs, persons);
                int endIndex = startIndex+persons;
                groups.put(groupID, new int[]{startIndex, endIndex});

                System.out.println("Your ID - "+groupID);

                System.out.println("Anzahl der Personen in der Gruppe:"+ (endIndex-startIndex));
                groupID++;

                // end of new feature-----------------------------------------------------

                if (index == -1) {
                    System.out.println("Keine Plätze gefunden (-_-;)");
                } else {
                    for (int i = 0; i < persons; i++) {
                        if (index + 1 >= deckChairs.length) {
                            System.out.println();
                        }
                        deckChairs[index + i] = true;
                    }
                }

                //show(deckChairs);
            } else if (command.equals(leave)) {
                System.out.println("Bitte geben Sie Ihre Gruppen-ID ein:");
                int id= input.nextInt();
//                int[] startIndex = groups.get(groupID);
//                int[] endIndex = groups.get(groupID);
                int[] indexes  = groups.get(groupID);
                if (id==groupID){
                    for (int i : indexes) {
                        deckChairs[i] = false;
                    }
                }

            }
            else if (command.equals(show)) {
                show(deckChairs);
            } else if (command.equals(end)) {
                System.exit(0);
            } else {
                System.out.println("INVALID Повторите попытку!");
            }
        }

    }


    static int findFreePlaces(boolean[] deckChairs, int personsCount) {
        for (int i = 0; i < deckChairs.length; i++) {
            if (deckChairs[i] == false) {
                boolean abc = false;
                int x = i;
                if (x + personsCount >= deckChairs.length) {
                    for (int k = i; k < deckChairs.length; k++) {
                        if (deckChairs[k] == true) {
                            abc = true;
                            break;
                        }
                    }
                    for (int j = 0; j < deckChairs.length - x; j++) {
                        if (deckChairs[j] == true) {
                            abc = true;
                            break;
                        }
                    }
                }

                if (abc == false) {
                    return i;
                }
            }
        }
        return -1;
    }

    static void show(boolean[] chairs) {
        for (boolean chair : chairs) {
            if (chair) {
                System.out.print("belegt");
            } else {
                System.out.print("frei");
            }
            System.out.print(' ');
        }
        System.out.println("");
    }
}

