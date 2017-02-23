import java.util.Date;
import java.lang.Thread;

public class HelloWorld{
     static int control = 0;
     static void writeTimeEverySecond() throws InterruptedException{
         while(true){
             if(control++ == 10){
                 Thread t = Thread.currentThread();
                 t.stop();
             }
             Date vreme = new Date();
             System.out.println(vreme);
             Thread.sleep(1000);
         }
     }
     public static void main(String []args){
        System.out.println("Hello World");
            
            Thread t = new Thread(() -> {
                try{
                    writeTimeEverySecond();
                }
                catch(Exception e){
                    Thread tr = Thread.currentThread();
                    tr.getUncaughtExceptionHandler().uncaughtException(tr, e);
                }
                
            });
            t.start();
        
        
        
     }
}