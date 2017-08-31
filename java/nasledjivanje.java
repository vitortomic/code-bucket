import java.lang.Math; 

public class Aplikacija
{
  
  public static void main(String[] args)
  {
   Ekser ekser = new Ekser();
    
    //klasa cekic nasledjuje klasu predmet i implemntira interfejs udarac, to znaci da ce imati sve metode interfejsa udarac kao i da ce naslediti sve 
    //metode i polja svoje nadklase predmet, osim apstrktnih metoda nadklase koje mora sam da implemntira cekic
    //cekic se instancira pozivom konstruktora new Cekic() i to ce ga ubaciti u memoriju ali ti da bi pristupio tom objektu koristis reference
    
    //referencu mozes u ovom slucaju da napravis na tri nacina, preko interfejsa, preko nadklase i preko same klase cekic
    Udarac cekicI = new Cekic();
    Predmet cekicN = new Cekic();
    Cekic cekic = new Cekic();
    //ovako imas tri razlicita objekta na koje pokazuju tri razlicite reference
    
    //takodje mozes da instanciras jednom objekat a onda napravis ostale reference na njega
    Cekic cekicCekic = new Cekic();
    Udarac cekicInterfejs = cekicCekic;
    Predmet cekicNadklasa = cekicCekic;
    //ovako imas jedan objekat na koji pokazuju tri razlicite reference
    
    //fora je da ako pristupas cekicu kroz interfejs mozes samod a pozivas metode koje su definisane u interfejsu
    cekicInterfejs.udaraj(ekser);
    //cekicInterfejs.ispisiOpis(); //ovo puca jer iako objekat sam ima tu metodu, ona nije vidljiva preko interfejsa

    //ista prica vazi za referencu preko nadklase, ona vidi samo svoje metode i polja, ne vidi metode specificne za klasu cekic
    cekicNadklasa.ispisiOpis();//radi jer je ovo metoda iz nadklase, tj Predmet
    //cekicNadklasa.udaraj(ekser);//puca jer nadklasa nema implemnntaciju interfejsa udarac a ne vidi metode od svoje podklase
    //cekicNadklasa.cekicevaMetoda();//ista prica, puca jer je ovo metoda podklase koju referenca Predmet ne moze da vidi
    System.out.print(cekicNadklasa.ime);//radi jer je property od nadklase
    //System.out.println(cekicNadklasa.cekicevProperty);//puca jer je property podklase

    //ako koristis referencu Cekic ona vidi sve metode klase cekic, sve metode i polja nadklase Predmet kao i metode interfejsa udarac
    cekic.udaraj(ekser);//metoda interfejsa
    cekic.cekicevaMetoda();//metoda klase cekic
    cekic.ispisiOpis();//metoda nadklase
    System.out.println(cekic.cekicevProperty);//polje od cekica
    System.out.println(cekic.ime);//polje od nadklase

    /*svrha nasledjivanja u ovom primeru je da zajednicke osobine kamena i cekica i eksera napisemo samo na jednom mestu u njihovoj nadklasi
    konkretno, klasa Predmet svojoj deci prosledjuje metodu ispisiOpis koju mozemo onda da koristimo u svim klasama koje nasledjuju predmet bez dodatnog koda
    takodje se nasledjuju i polja tako da samo u predmetu je deklarisano polje ime a setuje se posle u podklasama*/
    
    Predmet ekserNadklasa = ekser;
    ekserNadklasa.ispisiIme();//ovde iako pristupamo preko nadklase metodi ispisiIme, zbog toga sto je u samoj klasi ekser overajdovana ova metoda, desice se ponasanje definisano u podklasi, a ne nadklasi
    
    /*korist od nasledjivanja je najveca upravo da zajednicko ponasanje nekih klasa grupises u nadklasu i definises na tom jednom mestu, specificno ponasanje i osobine svake podklase ostaje
    u podklasama, a uvek podklasa moze da pregazi ponasnaje nadklase ako je to potrebno, u ovom primeru svi predmeti imaju ime i metodu da se ispise ime te stoga to smo stavili u nadklasu predmet,
    a posto ocemo da ekser ne prati standardno ponasanje nadklase onda smo overajdovali tj pregazili metodu ispsi ime */ 

    /*svrha interfejsa je da nametne klasi koja ga implementira tacnu specifkaciju koje ponasanje ta klasa treba da ima, u slucaju interfejsa Udarac kazemo da svaka klasa koja ga implementira
    mora da ima definisanu metodu udaraj.*/

    Udarac glava = new Glava();//isto bi bilo da smo rekli Glava glava = new Glava();

    //klasa Glava ne nasledjuje klasu Predmet i nema nikakvih dodirnih tacaka sa klasama Kamen i Cekic osim sto ima istu metodu jednu a to je udaraj i tako implementira interfejs udarac
    //Interfejs nije nista drugo nego specifikacija da neka klasa obezbedjuje neko ponasanje
    
    Kamen kamen = new Kamen();

    //metodi zakucajEkserSaBiloCime treba Udarac, ne zanima ga koju ces mu klasu dati, jedino je bitno da ona ispunjava protokol definisan u interfejsu a to je da ima metodu udaraj()
    Majstor.zakucajEkserSaBiloCime(ekser, glava);
    Majstor.zakucajEkserSaBiloCime(ekser, cekicInterfejs);
    Majstor.zakucajEkserSaBiloCime(ekser, cekic);
    Majstor.zakucajEkserSaBiloCime(ekser, kamen);

    /*u principu bi uvek trebalo teziti da se programira na interfejs, to znaci da u metodi kao sto je zakucajEkserSaBiloCime kao parametre koristis interfejse koji specificraju tacno ono ponasanje
    koje ti treba za metodu zakucajEkserSaBiloCime. Na taj nacin omogucavas da vrlo lako svaka klasa moze da se prilagodi i prosledi toj metodi tako sto ce implementirati interfejs koji si ti definisao
    To dalje obezbedjue da imas kod koji je lako prosirivati tj dodavati nove klase koje implementiraju ovaj interfejs a takodje i smanjuje potrebu za dupliranjem koda.
    pogledaj na dnu u klasi majstor primer konkretan
    */
    
    //jos jedna napomena, uvek mozes da izvuces celu klasu iz interfejsa tako sto ces castovati uz pomoc (ImeKlase)objekat
    Cekic nekiCekic = (Cekic)cekicInterfejs;
    nekiCekic.cekicevaMetoda();
  }
}

interface Udarac{
  void udaraj(Predmet predmet);
}


/*ako klasa ima abstraktnu metodu to znaci da i sama postaje apstraktna, ne moze da se instancira, vec samo mogu da se instanciraju njene podklase koje imaju 
    svoju implementaciju te metode, u ovom slucaju ispisiOpis()*/
abstract class Predmet{
  //sve klase koje naslede predmet ce naslediti i ovo polje ime
  String ime;

  //ova metoda je konkretna sto znaci da podklase ne moraju da definisu svoju implementaciju ali mogu(u tom slucaju je one overrajduju kao u slucaju eksera)
  public void ispisiIme(){
    System.out.println(this.ime);
  }
  
  //podklase moraju da definisu svoju implemntaciju ove metode
  public abstract void ispisiOpis();
  
}

class Ekser extends Predmet{
  
  //pri instanciranju klase ekser preko ovog konstruktora setovace se polje ime koje je nasledjeno od nadklase
  public Ekser(){
    this.ime = "ekser";
  }
  
  public void nekaSpecificnaMetodaZaEkser(){
    System.out.print("ja sam specijalan");
  }
  
  //anotacija @Override oznacava da se ovde gazi neka metoda nadklase, nije obavenza ova anotacija ali cini kod razumljivijim i ubrzava kompilaciju
  @Override
  public void ispisiOpis(){
    System.out.println("ja sam ekser");
  }

  @Override
  public void ispisiIme(){
      System.out.println("ovde smo overajdovali da ne ispisuje ime vec ovo sto upravo citas");//pogledaj liniju 49
  }
  
}

class Cekic extends Predmet implements Udarac{
  
  String cekicevProperty = "stagod";
  
  public void cekicevaMetoda(){
    System.out.println("pozvana je cekiceva metoda");
  }
  
  public Cekic(){
    this.ime = "cekic";
  }
  
  @Override
  public void ispisiOpis(){
  	System.out.println("ja sam cekic");
  }
  
  @Override
  public void udaraj(Predmet predmet){
   	System.out.println("Udaramo " + predmet.ime + " sa " + this.ime); 
  }
  
}

class Kamen extends Predmet implements Udarac{
  
  public Kamen(){
    this.ime = "kamen";
  }
  
  @Override
  public void ispisiOpis(){
  	System.out.println("ja sam kamen");
  }
  
  @Override
  public void udaraj(Predmet predmet){
   	System.out.println("Udaramo " + predmet.ime + " sa " + this.ime); 
  }
}

class Glava implements Udarac{
    public void udaraj(Predmet predmet){
        System.out.println("zakucali smo ekser glavom");
    }
}

class Majstor{

  //kljucna rec static znaci da ne mora da se instancira klasa Majstor da bi se pozvala ova metoda, poziva se preko naziva klase Majstor.zakucajEkserSaBiloCime
  public static void zakucajEkserSaBiloCime(Ekser ekser, Udarac alatZaZakucavanje){
    alatZaZakucavanje.udaraj(ekser);
  }
  
  /*bez koriscnja interfejsa morali bi da napravimo po jednu verziju ove metode za svaku mogucu klasu koju hocemo da koristimo, npr:

  public static void zakucajEkserSaBiloCime(Ekser ekser, Glava alatZaZakucavanje){
    alatZaZakucavanje.udaraj(ekser);
  }
  public static void zakucajEkserSaBiloCime(Ekser ekser, Kamen alatZaZakucavanje){
    alatZaZakucavanje.udaraj(ekser);
  }
  public static void zakucajEkserSaBiloCime(Ekser ekser, Cekic alatZaZakucavanje){
    alatZaZakucavanje.udaraj(ekser);
  }
  
  sad zamisli da postoji jos 20 razlicitih klasa koje imaju metodu udarac, za svaku bi morao da napravis verziju iste ove metode koja uzima bas tu klasu, ovo se inace zove method overloading,
  tj pisanje vise verzija iste metode za razlicite parametre.

  Zato sto koristimo interfejs mi smo jednostavno rekli da primamo kao parametra bilo koju klasu na belom svetu koja implementira interfejs Udarac, a naravno taj interfejs cemo definisati tako da sadrzi sve
  metode koje ce nama dalje trebati za nasu metodu koja taj interfejs prima kao parametar.
  */
}