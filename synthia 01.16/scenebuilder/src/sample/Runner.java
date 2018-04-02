package sample;

public class Runner {



    public void run()
    {
       Runnable runner = new TimeBar();
        Thread thread = new Thread(runner);

        thread.start();
    }


}
