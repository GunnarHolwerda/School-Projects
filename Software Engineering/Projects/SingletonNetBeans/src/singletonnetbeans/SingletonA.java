package singletonnetbeans;

public class SingletonA extends Singleton{
    private static SingletonA instance;
    
    private SingletonA() {
        singletonnetbeans.Singleton.Register("A", this);
    }
    
    public static SingletonA getInstance() {
        if (instance == null) {
            instance = new SingletonA();
        }
        return instance;
    }
    
    @Override
    protected void dance() {
        System.out.println("We are doing the macarena");
    }
}
