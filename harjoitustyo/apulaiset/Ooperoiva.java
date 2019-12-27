package harjoitustyo.apulaiset;

/**
 * Pakolliset listaoperaatiot määrittelevä rajapinta. Toteuta OmaLista-luokassa.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet I, kevät 2019.
 * <p>
 * @author Jorma Laurikkala (jorma.laurikkala@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */

public interface Ooperoiva<E> {
   /**
    * Listan alkioiden välille muodostuu kasvava suuruusjärjestys, jos lisäys
    * tehdään vain tällä operaatiolla, koska uusi alkion lisätään listalle siten,
    * että alkio sijoittuu kaikkien itseään pienempien tai yhtä suurien alkioiden
    * jälkeen ja ennen kaikkia itseään suurempia alkioita. Alkioiden suuruusjärjestys
    * selvitetään Comparable-rajapinnan compareTo-metodilla.
    * <p>
    * Jos parametri liittyy esimerkiksi kokonaislukuun 2 ja listan tietoalkiot ovat
    * [ 0, 3 ], on listan sisältö lisäyksen jälkeen [ 0, 2, 3 ], koska {@literal 0 < 2 < 3}.
    * <p>
    * Käytä toteutuksessa SuppressWarnings-annotaatiota, jotta kääntäjä ei valittaisi
    * vaarallisesta tyyppimuunnoksesta.
    *
    * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
    * toteuttaneen Comparable-rajapinnan.
    * @return true, jos lisäys onnistui. False, jos lisäys epäonnistui, koska uutta
    * alkiota ei voitu vertailla. Vertailu epäonnistuu, kun parametri on null-arvoinen
    * tai siihen liittyvällä oliolla ei ole vastoin oletuksia Comparable-rajapinnan
    * toteutusta.
    */
   @SuppressWarnings({"unchecked"})
   public abstract boolean lisaa(E uusi);

   /**
    * Poistaa listalta viitteet, jota liittyvät tietoalkioon, johon parametrina
    * annettu viite liittyy. Tosin sanoen listalta poistetaan viitteet x = get(ind),
    * joille lauseke "poistettava == get(ind)" on totta. Listalta poistetaan yleensä
    * joko yksi tai ei yhtään alkiota. Useita alkioita poistetaan, kun parametri liittyy
    * tietoalkioon, jonka kaksi tai useampaa viitettä jakaa. Listalla on jaettuja
    * alkioita esimerkiksi, jos viite on lisätty listalle monta kertaa. Löydät
    * operaation toimintaa havainnollistavia esimerkkejä Olio-ohjelmoinnin perusteet I -kurssin
    * <a href="https://coursepages.uta.fi/tiea2-1a/kevat-2019/tentit/">mallitentistä</a>.
    *
    * @param poistettava viite tietoalkioon.
    * @return listalta poistettujen viitteiden lukumäärä.
    */
   public abstract int poista(E poistettava);
}
