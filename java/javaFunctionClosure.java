import java.util.function.Function;


public class Main
{
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
