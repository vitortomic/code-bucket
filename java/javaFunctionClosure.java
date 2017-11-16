//*******************************************************************
// Dear CompileJava users,
//
// CompileJava has been operating since 2013 completely free. If you
// find this site useful, or would otherwise like to contribute, then
// please consider a donation (link in 'More Info' tab) to support
// development of the new CompileJava website (stay tuned!).
//
// Most sincerely, Z.
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class
import java.util.function.Function;

// one class needs to have a main() method
public class HelloWorld
{
  // arguments are passed using the text field below this editor
  public static void main(String[] args)
  {	
    Osoba o = new Osoba();
    o.ime = "Vitor";
    Function<Integer, Integer> square = x -> {
      System.out.println("test");
      System.out.println(o.ime);
      return x*x;
    };
    System.out.println(Test.test(square,4));
  }
}

public class Test{
  public static Integer test(Function<Integer, Integer> square, Integer x){
    try{
    	Thread.sleep(4000);
  		return square.apply(x);
    }
    catch (Exception e){return 0;}
  }
}

public class Osoba{
  public String ime;
}
