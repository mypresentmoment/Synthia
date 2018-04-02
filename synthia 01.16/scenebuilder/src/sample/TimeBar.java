package sample;
import javafx.application.Application;
import java.util.EventListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
public class TimeBar implements Runnable{

    float time;
    static public boolean ifTrue;
    static public boolean ifTrueWait;
    public TimeBar()
    {
    time = 0; ifTrue=true; ifTrueWait = false;
    }

    @Override
    public void run()
    {
        ifTrue = true;
        ifTrueWait = false;
        while (ifTrue)
        {
            time += 0.01;
            Controller.floatProperty.set(time);

            while (ifTrueWait)
            {
              //  System.out.println("kasztan");
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        //  System.out.println(time);
            try {
                //usypiamy wątek na 100 milisekund
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        Controller.floatProperty.set(0);
    }




}
