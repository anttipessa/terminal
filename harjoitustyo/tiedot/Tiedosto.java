package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Syvakopioituva;
import java.io.Serializable;
import java.io.*;

/**
 * Tiedosto-luokka, joka perii Tieto-luokan ja toteuttaa Syvakopioituva- ja
 * Serializable-rajapinnat.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet 2, kevät 2019.
 * <p>
 * @author Antti Pessa (antti.pessa@tuni.fi), Informaatioteknologian ja
 * viestinnän tiedekunta, Tampereen yliopisto.
 */
public class Tiedosto extends Tieto implements Syvakopioituva<Tiedosto>, Serializable {

    private int koko;

    public Tiedosto() {
        koko = 0;
    }

    /**
     * Tiedoston rakentaja
     *
     * @param n StringBuilder-tyyppinen olio nimen asettamiseksi
     * @param k tiedoston int-tyyppinen koko
     * @throws IllegalArgumentException jos parametreissa oli virhe.
     */
    public Tiedosto(StringBuilder n, int k) throws IllegalArgumentException {
        super.nimi(n);
        koko(k);
    }
    
    public int koko() {
        return koko;
    }

    /**
     * Tiedoston koon asettava metodi.
     *
     * @param k tiedoston koko
     * @throws IllegalArgumentException jos parametri oli vääränlainen.
     */
    public void koko(int k) throws IllegalArgumentException {
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        koko = k;
    }

    /**
     * Syväkopioi tiedoston ja palauttaa viitteen kopioon.
     *
     * @return viite syväkopioituun tiedostoon.
     */
    @Override
    public Tiedosto kopioi() {
        try {
            // Byte-tyyppisten alkioiden (tavujen) taulukkoon kirjoittava virta.
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            // Olion tavuiksi muuntava virta, joka liittyy taulukkoon kirjoittavaan
            // virtaan.
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            // Kirjoitetaan olio tavumuodossa taulukkoon.
            oos.writeObject(this);

            // Tyhjennetään puskuri ja suljetaan virta.
            oos.flush();
            oos.close();

            // Liitetään taulukkoon tavuja lukeva syötevirta.
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

            // Tavut olioksi muuttava virta, joka liittyy taulukosta lukevaan virtaan.
            ObjectInputStream ois = new ObjectInputStream(bis);

            // Kopio saadaan aikaiseksi lukemalla olion tavut taulukosta.
            Object kopio = ois.readObject();

            // Palautetaan oikean tyyppinen viite.
            return (Tiedosto) kopio;
        } // Sarjallistettavan olion oletusrakentaja hukassa.
        catch (InvalidClassException e) {
            e.printStackTrace();
            return null;
        } // Löytyi olio, joka ei sarjallistu.
        catch (NotSerializableException e) {
            e.printStackTrace();
            return null;
        } // Tapahtui jotain yllättävää.
        catch (Exception e) {
            System.out.println("Paniikki!");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Korvattu Object-luokan toString metodi.
     *
     * @return palauttaa yliluokan toStringin ja koon.
     */
    @Override
    public String toString() {
        return super.toString() + " " + koko();
    }

}
