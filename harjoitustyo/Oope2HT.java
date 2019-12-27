package harjoitustyo;

/**
 * Oope2HT-luokka, jossa ohjelma suoritettaan.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet 2, kevät 2019.
 * <p>
 * @author Antti Pessa (antti.pessa@tuni.fi), Informaatioteknologian ja
 * viestinnän tiedekunta, Tampereen yliopisto.
 */
public class Oope2HT {

    public static void main(String[] args) {

        // Luodaan käyttöliittymä ja kutsutaan aloita() metodia.
        
        Kayttoliittyma cmd = new Kayttoliittyma();
        cmd.aloita();
    }
}
