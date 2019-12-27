package harjoitustyo.omalista;

import harjoitustyo.apulaiset.Ooperoiva;
import java.util.LinkedList;

/**
 * OmaLista-luokka, joka perii LinkedList-luokan ja toteuttaa
 * Ooperoiva-rajapinnan.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet 2, kevät 2019.
 * <p>
 * @author Antti Pessa (antti.pessa@tuni.fi), Informaatioteknologian ja
 * viestinnän tiedekunta, Tampereen yliopisto.
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    /**
     * OmaLista lisaa metodi.
     *
     * Listan alkioiden välille muodostuu kasvava suuruusjärjestys, jos lisäys
     * tehdään vain tällä operaatiolla, koska uusi alkion lisätään listalle
     * siten, että alkio sijoittuu kaikkien itseään pienempien tai yhtä suurien
     * alkioiden jälkeen ja ennen kaikkia itseään suurempia alkioita.
     *
     * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
     * toteuttaneen Comparable-rajapinnan.
     * @return true, jos lisäys onnistui. False, jos lisäys epäonnistui, koska
     * uutta alkiota ei voitu vertailla. Vertailu epäonnistuu, kun parametri on
     * null-arvoinen tai siihen liittyvällä oliolla ei ole vastoin oletuksia
     * Comparable-rajapinnan toteutusta.
     */
    @SuppressWarnings({"unchecked"})
    @Override
    public boolean lisaa(E uusi) {
        // Lippumuuttuja, joka katsoo onko lisätty.
        boolean lisatty = false;
        // Katsotaan että uusi toteuttaa Comparable-rajapinnan.
        if (uusi instanceof Comparable) {
            int ind = 0;
            // Jos lista on tyhjä, lisätään uusi sinne.
            if (this.isEmpty()) {
                this.add(uusi);
                lisatty = true;
            }
            // While-loopissa käydään lista läpi, vertaillaan listan alkioita,
            // löytääksemme paikan uudelle oliolle.
            while (ind < size() && lisatty == false) {

                Comparable nykyinen = (Comparable) uusi;
                Comparable seuraava = (Comparable) get(ind);

                // Lisätään listalle jos löytyy sopiva paikka.
                if (nykyinen.compareTo(seuraava) < 0) {
                    this.add(ind, uusi);
                    lisatty = true;
                }

                // Kun tullaan listan loppuun ja ei ole löydetty paikkaa, 
                // lisätään olio listan lopppuun.
                if (ind == size() - 1) {
                    this.add(uusi);
                    lisatty = true;
                }

                ind++;
            }
        }
        return lisatty;
    }

    /**
     * OmaLista poista metodi.
     *
     * Poistaa listalta viitteet, jota liittyvät tietoalkioon, johon parametrina
     * annettu viite liittyy. Tosin sanoen listalta poistetaan viitteet x =
     * get(ind), joille lauseke "poistettava == get(ind)" on totta. Listalta
     * poistetaan yleensä joko yksi tai ei yhtään alkiota.
     *
     * @param poistettava viite tietoalkioon.
     * @return listalta poistettujen viitteiden lukumäärä.
     */
    @Override
    public int poista(E poistettava) {
        int ind = 0;
        int lkm = 0;

        while (ind < size()) {
            if (get(ind) == poistettava) {
                remove(ind);
                lkm++;
            } else {
                ind++;
            }
        }
        return lkm;
    }

}
