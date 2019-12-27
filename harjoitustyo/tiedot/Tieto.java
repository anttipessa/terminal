package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Tietoinen;
import java.io.Serializable;

/**
 * Abstrakti yliluokka Tieto, joka toteuttaa Tietoinen, Comparable ja
 * Serializable rajapinnat.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet 2, kevät 2019.
 * <p>
 * @author Antti Pessa (antti.pessa@tuni.fi), Informaatioteknologian ja
 * viestinnän tiedekunta, Tampereen yliopisto.
 */
public abstract class Tieto implements Tietoinen, Comparable<Tieto>, Serializable {

    private StringBuilder nimi;

    public Tieto() {
        nimi = new StringBuilder();
    }

    /**
     * Tiedon paramterillinen rakentaja.
     *
     * @param n StringBuilder-tyyppinen nimi.
     * @throws IllegalArgumentException jos parametri on null-arvoinen tai
     * muodoltaan virheellinen.
     */
    public Tieto(StringBuilder n) throws IllegalArgumentException {
        nimi(n);
    }

    /**
     * Palauttaa nimen
     *
     * @return
     */
    public StringBuilder nimi() {
        return nimi;
    }

    /**
     * Nimen asettava metodi.
     *
     * @param n StringBuilder-tyyppinen nimi.
     * @throws IllegalArgumentException jos parametri on null-arvoinen tai
     * muodoltaan virheellinen.
     */
    public void nimi(StringBuilder n) throws IllegalArgumentException {
        // Tarkistetaan että nimi ei ole null.
        if (n == null) {
            throw new IllegalArgumentException();
        }
        // Regexin avulla tarkistetaan että syöte on muodoltaan oikeellinen.
        // Saa olla a–z, A–Z ja 0–9 sekä alaviiva('_') ja piste.
        if (n.toString().matches("^[./\\w]*$") && !n.toString().matches(".*([.])\\1.*")
                && !n.toString().matches(".")) {
            nimi = n;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Tiedon toString metodi.
     *
     * @return palauttaa nimen Stringinä.
     */
    @Override
    public String toString() {
        return nimi.toString();
    }

    /**
     * Tietojen vertailu metodi.
     *
     * @param obj vertailtava Tieto olio.
     * @return true, jos stringit ovat samat. False, jos obj on null, ei ole
     * Tieto olio tai kun stringit eivät ole samat.
     */
    @Override
    public boolean equals(Object obj) {
        // Palautettaan false, jos obj on null.
        if (obj == null) {
            return false;
        }
        // Palautetaan false, jos obj ei ole Tieto tyyppinen.
        if (!(obj instanceof Tieto)) {
            return false;
        }
        // Asetetaan obj apumuuttujaan ja vertailaan toStringeja.
        Tieto toinen = (Tieto) obj;
        return nimi().toString().equals(toinen.nimi().toString());

    }

    /**
     * Tutkii vastaako tiedon nimi parametrina annettua nimeä tai ilmausta.
     * Ilmaus on muodostettu käyttämällä yhtä tai kahta jokerimerkkiä.
     * Jokerimerkki voi olla ilmauksen alussa tai lopussa tai sekä alussa ja
     * lopussa. Ilmauksen alussa oleva jokerimerkki kohdistaa vertailun nimen
     * loppuun. Lopussa oleva jokerimerkki toimii päinvastoin. Ilmauksen alussa
     * ja lopussa olevat jokerimerkit kohdistavat vertailun nimen keskelle.
     * Kaikkein laajin ilmaus on jokerimerkki itsessään, joka vastaa aina tiedon
     * nimeä.
     *
     * @param hakusana nimi tai ilmaus, johon tiedon nimeä verrataan.
     * @return true, jos tiedon nimi vastaa parametria ja false, jos tiedon nimi
     * ei vastaa parametria, parametrina saatu ilmaus on muodoltaan virheellinen
     * (esimerkiksi kolme tähteä) tai parametri on null-arvoinen.
     */
    @Override
    public boolean equals(String hakusana) {
        // Tarkistetaan että hakusana ei ole null ja
        // ei ole kahta tähtimerkkiä peräkkäin
        if (hakusana != null && !hakusana.contains("**")) {
            String uhakusana = "";
            // Jos hakusana alkaa ja loppuu tähtimerkkiin
            if (hakusana.startsWith("*") && hakusana.endsWith("*")) {
                // Poistetaan tähtimerkit
                for (int i = 1; i < hakusana.length() - 1; i++) {
                    uhakusana += hakusana.charAt(i);
                }
                // Palauttaa true jos nimi sisältää hakusanan
                return nimi().toString().contains(uhakusana);
                // Jos hakusana alkaa tähdellä
            } else if (hakusana.startsWith("*")) {
                // Poistetaan tähtimerkit
                for (int i = 1; i < hakusana.length(); i++) {
                    uhakusana += hakusana.charAt(i);
                }
                // Katsotaan loppuuko nimi hakusanalla
                return nimi().toString().endsWith(uhakusana);
                // Jos hakusana loppuu tähtimerkillä
            } else if (hakusana.endsWith("*")) {
                // Poistetaan tähtimerkit
                for (int i = 0; i < hakusana.length() - 1; i++) {
                    uhakusana += hakusana.charAt(i);
                }
                // Katostaan alkaako nimi hakusanalla
                return nimi().toString().startsWith(uhakusana);
            }
            // Jos ei ole tähtimerkkiä, katsotaan matchesin avulla 
            // ovatko ne samat
            if (!hakusana.contains("*")) {
                return nimi().toString().matches(hakusana);
            }

        }
        return false;
    }

    /**
     * Comparable-rajapinnan toteutus nimien vertailu.
     *
     * @param toinen Tieto tyyppinen olio.
     * @return -1, jos pienempi. 0, jos sama. 1, jos suurempi.
     */
    @Override
    public int compareTo(Tieto toinen) {
        // Käytetään toStringin compareTo metodia.
        int comp = nimi().toString().compareTo(toinen.nimi().toString());
        // Muutetaan arvot halutuiksi arvoiksi 1,-1 ja 0.
        if (comp < 0) {
            return -1;
        } else if (comp == 0) {
            return 0;
        } else {
            return 1;
        }
    }

}
