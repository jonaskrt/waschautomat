import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Waschautomat {


    int[] wechselgeldInCent(int[] vorhandeneMuenzenInCent,
                            int waschPreisInCent, int[] eingeworfeneMuenzenInCent) {
        int[] neuVorhandeneMuenzenInCent = vorhandeneUmEingeworfeneErgaenzen(
                vorhandeneMuenzenInCent, eingeworfeneMuenzenInCent);
        int retourGeldbetrag = +betrag(eingeworfeneMuenzenInCent)
                - waschPreisInCent;
        if (0 == retourGeldbetrag) {
            System.out.println("Betrag geht auf -> Kein Rückgeld nötig.");
            return null;
        }

        if (0 > retourGeldbetrag) {
            System.out.println("Zu wenig eingeworfen. Bitte "
                    + (-retourGeldbetrag) + " nachzahlen.");
            return null;
        }

        int[] retourGeldMuenzen = betragUmMuenzen(retourGeldbetrag,
                neuVorhandeneMuenzenInCent);
        return retourGeldMuenzen;
    }

    int[] vorhandeneUmEingeworfeneErgaenzen(int[] vorhandeneMuenzenInCent,
                                            int[] eingeworfeneMuenzenInCent) {
        int[] total = new int[vorhandeneMuenzenInCent.length
                + eingeworfeneMuenzenInCent.length];
        int pos = 0;
        while (pos < vorhandeneMuenzenInCent.length) {
            total[pos] = vorhandeneMuenzenInCent[pos];
            pos = pos + 1;
        }
        int shift = pos;
        pos = 0;
        while (pos < eingeworfeneMuenzenInCent.length) {
            total[shift + pos] = eingeworfeneMuenzenInCent[pos];
            pos = pos + 1;
        }
        return total;
    }

    int[] betragUmMuenzen(int betrag, int[] neuVorhandeneMuenzenInCent) {
        List<Integer> muenzen = new ArrayList<Integer>();
        Arrays.sort(neuVorhandeneMuenzenInCent);
        int z = neuVorhandeneMuenzenInCent.length - 1;

        while (z >= 0 && betrag > 0) {
            while (z >= 0 && betrag >= neuVorhandeneMuenzenInCent[z]) {
                muenzen.add(neuVorhandeneMuenzenInCent[z]);
                betrag = betrag - neuVorhandeneMuenzenInCent[z];
                neuVorhandeneMuenzenInCent[z] = 0;
                z = z - 1;
            }
            z = z - 1;
        }
        if (betrag > 0) {
            System.out.println("Fehler: Kann nicht geügend Rückgeld geben.");
        }
        return toIntArray(muenzen);
    }

    int[] toIntArray(List<Integer> muenzen) {
        int[] resultat = new int[muenzen.size()];
        int pos = 0;
        for (Integer i : muenzen) {
            resultat[pos] = i;
            pos = pos + 1;
        }
        return resultat;
    }


    int betrag(int[] eingeworfeneMuenzenInCent) {
        int totalBetrag = 0;
        for (int wert : eingeworfeneMuenzenInCent) {
            totalBetrag = totalBetrag + wert;
        }
        return totalBetrag;
    }

    public static void main(String[] args) {
        new Waschautomat().top();
    }


    void top() {
        int[] vorhandeneMuenzenInCent = { 100, 200, 500, 500, 500 };
        int waschPreisInCent = 400;
        int[] eingeworfeneMuenzenInCent = { 100, 200, 500 };

        int[] rueckgeld;
        rueckgeld = wechselgeldInCent(vorhandeneMuenzenInCent,
                waschPreisInCent, eingeworfeneMuenzenInCent);
        System.out.println("Retourgeld:");
        ausgabeArray(rueckgeld);
    }


    void ausgabeArray(int[] rueckgeld) {
        if (null == rueckgeld) {
            return;
        }
        for (int cent : rueckgeld) {
            System.out.println(cent + " cent");
        }
    }

}