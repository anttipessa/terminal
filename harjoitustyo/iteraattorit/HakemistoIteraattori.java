package harjoitustyo.iteraattorit;

import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tieto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * HakemistoIteraattori-luokka, joka toteuttaa Iteratable-rajapinnan.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet 2, kevät 2019.
 * <p>
 * @author Antti Pessa (antti.pessa@tuni.fi), Informaatioteknologian ja
 * viestinnän tiedekunta, Tampereen yliopisto.
 */
public class HakemistoIteraattori<E> implements Iterator<E> {

    private Hakemisto sijainti;
    private int ind;
    private List<E> lista;

    /**
     * Iteraattorin rakentaja.
     *
     * @param hak tämänhetkinen Hakemisto.
     */
    public HakemistoIteraattori(Hakemisto hak) {
        sijainti = hak;
        lista = new ArrayList<>();
        ind = 0;
        esijärjestys(sijainti, lista);
    }

    /**
     * HakemistoIteraattorin hasNext() toteutus. Kertoo onko kokelmasta vielä
     * saatavilla alkioita.
     *
     * @return true, jos löytyy alkioita. False, jos ei ole.
     */
    @Override
    public boolean hasNext() {
        return ind < lista.size();
    }

    /**
     * HakemistoIteraattorin next() toteutus.
     *
     * @return Palauttaa viitteen seuraavaan alkioon, jos sellainen löytyy.
     */
    @Override
    public E next() {
        E tieto = lista.get(ind);
        ind++;
        return tieto;
    }

    /**
     * Esijärjestys algoritmi.
     *
     * @param hakemisto esijärjestettävä Hakemisto.
     * @param tiedot lista minne tiedot lisätään.
     */
    public void esijärjestys(Hakemisto hakemisto, List tiedot) {
        // Käydään hakemiston sisältö läpi yksi tieto kerrallaan.
        int i = 0;
        while (i < hakemisto.sisalto().size()) {
            Tieto tieto = hakemisto.sisalto().get(i);
            tiedot.add((E) tieto);
            // Lisätään alihakemiston tiedot listalle rekursiivisesti.
            if (tieto instanceof Hakemisto) {
                esijärjestys((Hakemisto) tieto, tiedot);
            }
            i++;
        }
    }

}
