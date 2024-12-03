package Listener;

public interface Observer <T1, T2,R>{
    R notify(T1 obg);
    R notify(T1 obg, T2 t2);
}
