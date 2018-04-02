package sample;

import java.util.concurrent.atomic.*;
import java.util.*;
import net.beadsproject.beads.ugens.Noise;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.core.io.JavaSoundAudioIO;
import net.beadsproject.beads.ugens.*;
import net.beadsproject.beads.data.*;
import net.beadsproject.beads.data.audiofile.AudioFileType;
import javax.sound.sampled.*;

public class AudioThread extends Thread {

    public Sample loaded_track;
    public  JavaSoundAudioIO soundIO;

    AudioThread(JavaSoundAudioIO IO,Sample track )
    {
        loaded_track = track;
        soundIO = IO;
    }

    public void update(JavaSoundAudioIO IO, Sample track)
    {
        loaded_track = track;
        soundIO = IO;
    }


    public void run(){
        AudioContext audiosys;
        audiosys = new AudioContext(soundIO);
        SamplePlayer j = new SamplePlayer(audiosys, loaded_track);
        Reverb p = new Reverb(audiosys);
        p.setSize(0.3f);
        p.addInput(j);
        Gain g = new Gain(audiosys, 2, 0.6f);
        g.addInput(j);
        audiosys.out.addInput(g);
        audiosys.start();// obiekt AudioContext w konstruktorze.
        System.out.println("Started");


    }


}

/*

*/

/* public class AudioThread implements Runnable {

    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval;
    public Sample loaded_track;
    public  JavaSoundAudioIO soundIO;


    public AudioThread(int sleepInterval, JavaSoundAudioIO IO,Sample track) {
        interval = sleepInterval;
        loaded_track = track;
        soundIO = IO;
    }

    public void update(JavaSoundAudioIO IO, Sample track)
    {
        loaded_track = track;
        soundIO = IO;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    public void run() {
        while (running.get()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e){
                //
            }
            AudioContext audiosys;
            audiosys = new AudioContext(soundIO);
            SamplePlayer j = new SamplePlayer(audiosys, loaded_track);
            Reverb p = new Reverb(audiosys);
            p.setSize(0.3f);
            p.addInput(j);
            Gain g = new Gain(audiosys, 2, 0.6f);
            g.addInput(p);
            audiosys.out.addInput(g);
            audiosys.start();
        }
    }
}

 */