import java.lang.Thread;
public class HelloWorld{
    
    static void createTask(String poruka){
        class Task implements Runnable{
            String poruka;
            Task(String param){
              poruka = param;  
            }
            public void run(){
                System.out.println(poruka);
                Thread current = Thread.currentThread();
                current.stop();
            }
        }
        Thread t = new Thread(new Task(poruka));
        t.start();
    }
     public static void main(String []args){
        createTask("helou");
        createTask("world");
     }
}
