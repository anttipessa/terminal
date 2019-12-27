package harjoitustyo.apulaiset;

/**
 * Tiedoston syväkopiointiin soveltuva metodi. Kiinnitä geneerinen tyyppi T
 * tyypiksi Tiedosto, kun toteutat tämän rajapinnan Tiedosto-luokassa.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet I, kevät 2019.
 * <p>
 * @author Jorma Laurikkala (jorma.laurikkala@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */

public interface Syvakopioituva<T> {
   /**
    * Syväkopioi tiedoston ja palauttaa viitteen kopioon. Muista toteuttaa
    * Serializable-rajapinta Tieto- ja Tiedosto-luokissa, jos teet syväkopioinnin
    * kurssilla esitellyllä menetelmällä (luentorungon luku 19). Serializable-
    * rajapinnan toteukseen riittää tässä työssä pelkkä avain sanalla implements
    * tehty sopimus Tieto- ja Tiedosto-luokkien otsikossa. Serializable-rajapinnan
    * metodien toteutusta ei tarvita.
    *
    * @return viite syväkopioituun tiedostoon.
    */
   abstract public T kopioi();
}
