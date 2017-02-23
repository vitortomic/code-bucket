import java.util.concurrent.*;
public class HelloWorld{
    
     String brutalnaOperacija(String input){
         //neka dugacka operacija
         return "rezultat dugacke operacije";
     }
     
     CompletableFuture<String> uradiAsinhrono(String input){
         Executor eg = Executors.newSingleThreadExecutor();
         CompletableFuture<String> rezultat = 
            CompletableFuture.supplyAsync(() -> brutalnaOperacija(input),eg);
         return rezultat;
     }
     
     String deljiGaDalje(String s){
         //radi jos nesto
         return "rezultat";
     }
     
     public static void main(String []args){
        HelloWorld app = new HelloWorld();
        CompletableFuture<String> ispis = app.uradiAsinhrono("stagod");
        
        //ovo traj npr 10 sec
        ispis.thenAccept(s-> System.out.println(s));
        ispis.thenApply(app::deljiGaDalje);
        
        //blokirajuci poziv
        try{
            String rezultat = ispis.get();
            System.out.println(rezultat);
        }
        catch (Exception e){ 
            
        }
        
     }
}