interface MyListener{
    void somethingHappened();
}

public class MyForm implements MyListener{
    MyClass myClass;
    public MyForm(){
        this.myClass = new MyClass();
        myClass.addListener(this);
    }
    public void somethingHappened(){
       System.out.println("Called me!");
    }
}
public class MyClass{
    private List<MyListener> listeners = new ArrayList<MyListener>();

    public void addListener(MyListener listener) {
        listeners.add(listener);
    }
    void notifySomethingHappened(){
        for(MyListener listener : listeners){
            listener.somethingHappened();
        }
    }
}
