package harjoitustyo.apulaiset;

// Otetaan käyttöön Javan linkitetty lista.
import java.util.LinkedList;

/**
 * Hakemiston käsittelyyn soveltuvia metodeja. Kiinnitä geneerinen tyyppi T
 * tyypiksi Tieto, kun toteutat tämän rajapinnan metodit Hakemisto-luokassa.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet I, kevät 2019.
 * <p>
 * @author Jorma Laurikkala (jorma.laurikkala@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */

public interface Sailova<T> {
   /**
    * Hakee hakemistosta tiedostoja ja alihakemistoja, joiden nimi vastaa annettua
    * hakusanaa. Hakusana voi olla tiedon nimi sellaisenaan (esimerkiksi
    * "grumpy_cat.jpeg") tai yhden tai kahden jokerimerkin avulla muodostettu ilmaus
    * (esimerkiksi "*.jpeg").
    * <p>
    * Paluuarvona palautettavalla listalla on yksi viite, jos parametri on hakemistossa
    * olevan tiedon nimi. Jokerimerkkien avulla voidaan löytää useampia tietoja.
    * <p>
    * Hyödyntää Tieto-luokassa toteutettavaa {@link Tietoinen#equals(String)}
    * -metodia siten, että palautettavalle listalle lisätään jokainen hakemiston
    * listalla oleva viite x = get(ind), jolle lauseke "x.equals(hakusana)" on totta.
    * Haku alkaa hakemiston ensimmäisestä alkiosta ja päättyy hakemiston viimeiseen
    * alkioon. Viitteet löydettyihin alkioihin ovat palautettavalla listalla samassa
    * järjestyksessä kuin hakemiston listalla.
    * <p>
    * @param hakusana nimi tai ilmaus, johon hakemiston tiedostojen ja alihakemistojen
    * nimiä verrataan.
    * @return lista, jolla on viitteet löydettyihin tietoihin, joiden nimet vastaavat
    * parametria. Lista on tyhjä eli nolla viitettä sisältävä lista, jos hakemistossa
    * ei ole tietoja, joiden nimet vastavat parametria, parametri on null-arvoinen,
    * ilmauksessa on käytetty jokerimerkkejä väärin tai hakemisto on tyhjä.
    * @see Tietoinen#equals(String)
    */
   abstract public LinkedList<T> hae(String hakusana);

   /**
    * Lisää hakemistoon tiedoston tai alihakemiston. Kutsuu hakemiston listan
    * toteuttamaa Ooperoiva-rajapinnan lisaa-metodia, jolla tieto saadaan lisätyksi
    * oikeaan paikkaan listalla. Lisäys onnistuu, jos parametri liittyy olioon,
    * jonka luokalla on Comparable-rajapinnan compareTo-metodin toteutus. Null-arvon
    * lisäys epäonnistuu aina.
    *
    * @param lisattava viite lisättävään tietoon.
    * @return true, jos lisäys onnistui. False, jos lisäys epäonnistui.
    */
   abstract public boolean lisaa(T lisattava);

   /**
    * Poistaa hakemistosta tiedoston tai alihakemiston. Kutsuu hakemiston listan
    * toteuttamaa Ooperoiva-rajapinnan poista-metodia.
    *
    * @param poistettava viite poistettavaan tietoon.
    * @return true, jos alkio poistettiin. False, jos poistettavaa alkiota ei löydetty
    * tai parametri on null.
    */
   abstract public boolean poista(T poistettava);
}
