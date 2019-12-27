package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Sailova;
import harjoitustyo.iteraattorit.HakemistoIteraattori;
import harjoitustyo.omalista.OmaLista;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Hakemisto-luokka, joka perii Tieto-luokan ja toteuttaa Sailova ja Iteratable
 * rajapinnat.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet 2, kevät 2019.
 * <p>
 * @author Antti Pessa (antti.pessa@tuni.fi), Informaatioteknologian ja
 * viestinnän tiedekunta, Tampereen yliopisto.
 */
public class Hakemisto extends Tieto implements Sailova<Tieto>, Iterable<Tieto> {

    private OmaLista<Tieto> sisalto;
    private Hakemisto ylihakemisto;


    public Hakemisto() {
        sisalto = new OmaLista<Tieto>();
        ylihakemisto = null;
    }

    /**
     * Hakemiston parametrillinen rakentaja.
     *
     * @param n StringBuilder-tyyppinen nimi.
     * @param yh Hakemiston ylihakemisto.
     * @throws IllegalArgumentException jos parametreissa oli virhe.
     */
    public Hakemisto(StringBuilder n, Hakemisto yh) throws IllegalArgumentException {
        super.nimi(n);
        ylihakemisto = yh;
        sisalto = new OmaLista<Tieto>();
    }

    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }

    public void ylihakemisto(Hakemisto yh) {
        ylihakemisto = yh;
    }

    public OmaLista<Tieto> sisalto() {
        return sisalto;
    }

    public void sisalto(OmaLista<Tieto> sis) {
        sisalto = sis;
    }

    /**
     * Sailova rajapinnan metodit
     */
    /**
     * Hakee hakemistosta tiedostoja ja alihakemistoja, joiden nimi vastaa
     * annettua hakusanaa. Hakusana voi olla tiedon nimi sellaisenaan
     * (esimerkiksi "grumpy_cat.jpeg") tai yhden tai kahden jokerimerkin avulla
     * muodostettu ilmaus (esimerkiksi "*.jpeg").
     *
     * @param hakusana nimi tai ilmaus, johon hakemiston tiedostojen ja
     * alihakemistojen nimiä verrataan.
     * @return lista, jolla on viitteet löydettyihin tietoihin, joiden nimet
     * vastaavat parametria. Lista on tyhjä eli nolla viitettä sisältävä lista,
     * jos hakemistossa ei ole tietoja, joiden nimet vastavat parametria,
     * parametri on null-arvoinen, ilmauksessa on käytetty jokerimerkkejä väärin
     * tai hakemisto on tyhjä.
     */
    @Override
    public LinkedList<Tieto> hae(String hakusana) {
        OmaLista<Tieto> haettava = new OmaLista<Tieto>();
        int ind = 0;
        // Käydään hakemisto läpi ja etsitään hakusanaa.
        while (ind < sisalto.size()) {
            Tieto apu = sisalto.get(ind);
            // Jos löytyy hakemistosta, lisätään listalle.
            if (apu.equals(hakusana)) {
                haettava.add(apu);
            }
            ind++;
        }
        return haettava;
    }

    /**
     * Lisää hakemistoon tiedoston tai alihakemiston. Kutsuu hakemiston listan
     * toteuttamaa Ooperoiva-rajapinnan lisaa-metodia, jolla tieto saadaan
     * lisätyksi oikeaan paikkaan listalla. Lisäys onnistuu, jos parametri
     * liittyy olioon, jonka luokalla on Comparable-rajapinnan compareTo-metodin
     * toteutus. Null-arvon lisäys epäonnistuu aina.
     *
     * @param lisattava viite lisättävään tietoon.
     * @return true, jos lisäys onnistui. False, jos lisäys epäonnistui.
     */
    @Override
    public boolean lisaa(Tieto lisattava) {
        return sisalto.lisaa(lisattava);
    }

    /**
     * Poistaa hakemistosta tiedoston tai alihakemiston. Kutsuu hakemiston
     * listan toteuttamaa Ooperoiva-rajapinnan poista-metodia.
     *
     * @param poistettava viite poistettavaan tietoon.
     * @return true, jos alkio poistettiin. False, jos poistettavaa alkiota ei
     * löydetty tai parametri on null.
     */
    @Override
    public boolean poista(Tieto poistettava) {
        return sisalto.poista(poistettava) > 0;
    }

    /**
     * Hakemiston toString-metodi.
     *
     * @return kutsuu yliluokan toString-metodia ja lisää sisällön koon.
     */
    @Override
    public String toString() {
        return super.toString() + "/ " + sisalto.size();
    }

    /**
     * Hakemistoiteraattorin toteutus.
     *
     * @return palauttaa viitteen kokoelman iterointiin tehdyn luokan olioon.
     */
    @Override
    public Iterator<Tieto> iterator() {
        return new HakemistoIteraattori<>(this);
    }

}
