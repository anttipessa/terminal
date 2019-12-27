package harjoitustyo;

import harjoitustyo.apulaiset.In;

/**
 * Käyttöliittymä-luokka, joka lukee käyttäjän syötteet ja kutsuu Tulkki-
 * luokan metodeja.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet 2, kevät 2019.
 * <p>
 * @author Antti Pessa (antti.pessa@tuni.fi), Informaatioteknologian ja
 * viestinnän tiedekunta, Tampereen yliopisto.
 */
public class Kayttoliittyma {

    public static final String LUOHAKEMISTO = "md";
    public static final String LUOTIEDOSTO = "mf";
    public static final String LISTAA = "ls";
    public static final String SIIRRY = "cd";
    public static final String VAIHDANIMI = "mv";
    public static final String KOPIOI = "cp";
    public static final String POISTA = "rm";
    public static final String HAKEMISTOLISTAUS = "find";
    public static final String LOPETA = "exit";
    public static final String ERROR = "Error!";

    /**
     * Metodi joka pyörittää ohjelmaa.
     */
    
    public void aloita() {

        System.out.println("Welcome to SOS.");
        Tulkki tulk = new Tulkki();
        boolean exit = false;

        while (exit == false) {

            System.out.print(tulk.annaPolku());
            String luettu = In.readString();
            String[] syote = luettu.split(" ");

            try {
                if (syote[0].equals(LUOHAKEMISTO)) {
                    tulk.luoHakemisto(syote);
                } else if (syote[0].equals(LISTAA) && syote.length < 3) {
                    tulk.listaaHakemisto(syote);
                } else if (syote[0].equals(LUOTIEDOSTO)) {
                    tulk.luoTiedosto(syote);
                } else if ((syote[0].equals(SIIRRY)) && syote.length < 3) {
                    tulk.vaihdaHakemisto(syote);
                } else if (syote[0].equals(POISTA)) {
                    tulk.poista(syote);
                } else if (syote[0].equals(VAIHDANIMI) && syote.length == 3) {
                    tulk.nimenVaihto(syote);
                } else if (syote[0].equals(KOPIOI) && syote.length == 3) {
                    tulk.kopioi(syote);
                } else if (syote[0].equals(HAKEMISTOLISTAUS) && syote.length == 1) {
                    tulk.etsi();
                } else if (syote[0].equals(LOPETA) && syote.length == 1) {
                    exit = true;
                    System.out.println("Shell terminated.");
                } else {
                    System.out.println(ERROR);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR);
            }

        }
    }

}
