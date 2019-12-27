package harjoitustyo.apulaiset;

/**
 * Luokkahierarkialle yhteiset metodit. Toteuta tämä rajapinta Tieto-luokassa.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet I, kevät 2019.
 * <p>
 * @author Jorma Laurikkala (jorma.laurikkala@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */

public interface Tietoinen {
   /**
    * Tutkii vastaako tiedon nimi parametrina annettua nimeä tai ilmausta.
    * Ilmaus on muodostettu käyttämällä yhtä tai kahta jokerimerkkiä. Jokerimerkki
    * voi olla ilmauksen alussa tai lopussa tai sekä alussa ja lopussa. Ilmauksen
    * alussa oleva jokerimerkki kohdistaa vertailun nimen loppuun. Lopussa oleva
    * jokerimerkki toimii päinvastoin. Ilmauksen alussa ja lopussa olevat
    * jokerimerkit kohdistavat vertailun nimen keskelle. Kaikkein laajin ilmaus
    * on jokerimerkki itsessään, joka vastaa aina tiedon nimeä. Jokerimerkeistä on
    * kerrottu tarkemmin 
    * <a href="https://coursepages.uta.fi/tiea2-1b/kevat-2019/harjoitustyo/">
    * tarkan tehtävänannon</a> luvussa 3.6.
    *
    * @param hakusana nimi tai ilmaus, johon tiedon nimeä verrataan.
    * @return true, jos tiedon nimi vastaa parametria ja false, jos tiedon nimi
    * ei vastaa parametria, parametrina saatu ilmaus on muodoltaan virheellinen
    * (esimerkiksi kolme tähteä) tai parametri on null-arvoinen.
    */
   abstract public boolean equals(String hakusana);
}
