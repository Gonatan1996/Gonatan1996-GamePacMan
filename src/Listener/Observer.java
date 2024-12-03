package Listener;

public interface Observer <T1, T2,R>{
    R notify(T1 obg);
    void drawImages(T1 g, T2 imageObserver,int x,int y);
}
