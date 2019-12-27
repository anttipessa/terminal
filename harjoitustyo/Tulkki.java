package harjoitustyo;

import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tiedosto;
import harjoitustyo.tiedot.Tieto;
import static java.lang.Integer.parseInt;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Tulkki-luokka, joka suorittaa eri komennot ohjelmassa.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet 2, kevät 2019.
 * <p>
 * @author Antti Pessa (antti.pessa@tuni.fi), Informaatioteknologian ja
 * viestinnän tiedekunta, Tampereen yliopisto.
 */
public class Tulkki {

    private Hakemisto juuri;
    private Hakemisto nyt;

    public Tulkki() {
        juuri = new Hakemisto();
        nyt = juuri;
    }

    /**
     * Metodi luo nykyisen hakemiston sisälle alihakemiston, jos samannimistä
     * hakemistoa/tiedosta ei ole.
     *
     * @param nimi käyttöliittymästä saatu String taulukko, joka sisältää nimen.
     * @throws IllegalArgumentException jos parametrissa on virhe.
     */
    public void luoHakemisto(String[] nimi) throws IllegalArgumentException {
        if (nimi.length == 2 && nyt.hae(nimi[1]).isEmpty()) {
            Hakemisto uusiH = new Hakemisto(new StringBuilder(nimi[1]), nyt);
            nyt.lisaa(uusiH);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Hakemiston sisällön listaus. Listauksen kohteena voi olla yksi tieto tai
     * useampia tietoja jokerimerkkien (*) avulla.
     *
     * @param nimi käyttöliittymästä saatu String taulukko, joka sisältää nimen.
     * @throws IllegalArgumentException jos parametrissa on virhe.
     */
    public void listaaHakemisto(String[] nimi) throws IllegalArgumentException {
        // Jos syöte on pelkkä "ls" tulostetaan kaikki hakemiston sisältö
        if (nimi.length == 1) {
            nyt.sisalto().forEach(System.out::println);
        } else {
            if (!nyt.hae(nimi[1]).isEmpty()) {
                nyt.hae(nimi[1]).forEach(System.out::println);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Hakemiston vaihto.
     *
     * @param nimi käyttöliittymästä saatu String taulukko, joka sisältää
     * hakemiston nimen.
     * @throws IllegalArgumentException jos parametrissa on virhe.
     */
    public void vaihdaHakemisto(String[] nimi) throws IllegalArgumentException {
        // Jos nimi on pelkkä "cd" palataan juureen
        if (nimi.length == 1) {
            nyt = juuri;
            // Jos nimi on "cd ..", niin vaihdetaan ylihakemistoon
        } else if (nimi[1].equals("..") && !(nyt == juuri)) {
            nyt = nyt.ylihakemisto();
        } else {
            // Lisätään siirryttävä hakemisto listaan
            LinkedList<Tieto> lista = nyt.hae(nimi[1]);
            // Tarkistetaan että lista ei ole tyhjä ja viite on Hakemisto tyyppinen
            if (!lista.isEmpty() && lista.getFirst() instanceof Hakemisto) {
                nyt = ((Hakemisto) lista.getFirst());
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Metodi luo nykyisen hakemiston sisälle tiedoston, jos samannimistä
     * hakemistoa/tiedosta ei ole.
     *
     * @param nimi käyttöliittymästä saatu String taulukko, joka sisältää nimen.
     * @throws IllegalArgumentException jos parametrissa on virhe.
     */
    public void luoTiedosto(String[] nimi) throws IllegalArgumentException {
        // Tarkistetaan, että taulukko on oikean pituinen ja saman nimistä tiedostoa
        // ei ole olemassa
        if (nimi.length == 3 && nyt.hae(nimi[1]).isEmpty()) {
            // Luodaan uusi tiedosto ja lisätään se hakemistoon
            Tiedosto uusiT = new Tiedosto(new StringBuilder(nimi[1]), parseInt(nimi[2]));
            nyt.lisaa(uusiT);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Tiedoston ja hakemiston poisto.
     *
     * @param nimi käyttöliittymästä saatu String taulukko, joka sisältää
     * poistettavan nimen.
     * @throws IllegalArgumentException jos parametrissa on virhe.
     */
    public void poista(String[] nimi) throws IllegalArgumentException {
        // Tarkistetaan että taulukko on oikean pituinen ja poistettava tieto löytyy
        if (nimi.length == 2 && !(nyt.hae(nimi[1]).isEmpty())) {
            // Poistetaan hakemistosta kunnes ei löydy enempää
            while (!nyt.hae(nimi[1]).isEmpty()) {
                Tieto poistettava = nyt.hae(nimi[1]).get(0);
                nyt.poista(poistettava);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Nimenvaihto. Nimen muuttaminen ei onnistu, jos hakemistossa ei ole etsittyä nimeä tai
     * hakemistossa on jo uuden niminen kohde valmiiksi olemassa.
     *
     * @param nimi käyttöliittymästä saatu String taulukko, joka sisältää vanhan
     * ja uuden nimen.
     * @throws IllegalArgumentException jos parametrissa on virhe.
     */
    public void nimenVaihto(String[] nimi) throws IllegalArgumentException {
        // Katsotaan että tieto löytyy ja ei löydy mitään uudelle nimelle
        if (!(nyt.hae(nimi[1]).isEmpty()) && (nyt.hae(nimi[2]).isEmpty())) {
            // Haetaan uudelleen nimettävä tieto muuttujaan ja vaihdetaan
            // nimi asettajaa käyttäen.
            Tieto uusi = nyt.hae(nimi[1]).get(0);
            uusi.nimi(new StringBuilder(nimi[2]));
            // Järjestetään hakemisto uudelleennimeämisen jälkeen Collections.sortilla
            Collections.sort(nyt.sisalto());
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Tiedoston kopiointi. Metodi saa syötteenä kopiotavan tiedon tiedoston nimen ja
     * kopion/kohde hakemiston nimen. Kopiointi voidaan tehdä samaan hakemistoon tai hakemiston, ali-
     * tai ylihakemistoon. Voidaan kopioida useempia tiedostoja jokerimerikillä (*).
     *
     * @param nimi käyttöliittymästä saatu String taulukko, joka sisältää
     * tiedoston nimen ja hakemiston nimen tai kopion nimen.
     * @throws IllegalArgumentException jos parametrissa on virhe.
     */
    public void kopioi(String[] nimi) throws IllegalArgumentException {
        // Lisätään kopioitava ja kopion nimi / hakemisto omiin listoihin
        LinkedList<Tieto> kopioitava = nyt.hae(nimi[1]);
        LinkedList<Tieto> kohde = nyt.hae(nimi[2]);
        // Luodaan alihak muuttuja joka määrittää hakemiston mihin kopio menee
        Hakemisto alihak = nyt;
        // Lippumuuttuja sille tapaukselle että kopioidaan nykyiseen hakemistoon
        boolean nykyinenhakemisto = false;
        // Jos hakemistoksi laitetaan ".." alihak on silloin ylihakemisto jos ei olla juuressa
        if (nimi[2].equals("..") && !(nyt == juuri)) {
            alihak = alihak.ylihakemisto();
            nykyinenhakemisto = true;
            // Tarkistetaan että hakemisto on olemassa ja kopio ei ole tyhjä
        } else if (!kohde.isEmpty() && kohde.get(0) instanceof Hakemisto) {
            // asetetaan alihak haluttu hakemisto
            alihak = (Hakemisto) kohde.get(0);
            nykyinenhakemisto = true;
        }
        // Tässä tapahtuu itse kopiointi, kun kopioidaan tiedosto eri hakemistoon
        if ((nykyinenhakemisto == true) && kopioitava.get(0) instanceof Tiedosto) {
            // Kopioidaan for-loopissa tiedostoja
            for (int i = 0; i < kopioitava.size(); i++) {
                // Jos jokin kopioitava tiedosto löytyy heitetään poikkeus
                if (!alihak.sisalto().contains(kopioitava.get(i))) {
                    alihak.lisaa(((Tiedosto) kopioitava.get(i)).kopioi());
                } else {
                    throw new IllegalArgumentException();
                }
            }
            // Tässä tehdään tiedoston kopiointi samassa hakemistossa
        } else if (!nyt.hae(nimi[1]).isEmpty() && kopioitava.get(0) instanceof Tiedosto
                && nyt.hae(nimi[2]).isEmpty() && nimi[1].contains("*") == false) {
            // Lisätään Tiedosto muuttujaan haettu kopio
            Tiedosto kopio = ((Tiedosto) kopioitava.get(0)).kopioi();
            // Vaihdetaan kopion nimi annettuun nimeen
            kopio.nimi(new StringBuilder(nimi[2]));
            // Lisätään kopio hakemistoon
            nyt.lisaa(kopio);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Rekursiivinen hakemisto listaus.
     */
    public void etsi() {
        Iterator iter = nyt.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

    /**
     * Hakemiston polun luominen.
     *
     * @return String-tyyppisen viitteen polusta.
     */
    public String annaPolku() {
        // Jos ollaan juuressa palautuu pelkkä "/>"
        if (nyt == juuri) {
            return "/>";
        }
        Hakemisto hak = nyt;
        String polku = ">";
        // Luodaan hakemistopolku
        while (hak != null) {
            StringBuilder apu = hak.nimi();
            polku = apu + "/" + polku;
            hak = hak.ylihakemisto();
        }
        return polku;
    }

}
