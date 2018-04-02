package sample;

import java.util.concurrent.atomic.*;
import java.util.*;

import net.beadsproject.beads.core.UGen;
import net.beadsproject.beads.ugens.Noise;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.core.io.JavaSoundAudioIO;
import net.beadsproject.beads.ugens.*;
import net.beadsproject.beads.data.*;
import net.beadsproject.beads.data.audiofile.AudioFileType;
import javax.sound.sampled.*;
import java.util.Arrays;
import java.util.Collections;


import java.lang.Math.*;

import static java.lang.Math.pow;


public class AudioPlayer {
    private boolean ifPause;
    public double moment_in_track;
    public Sample moment_in_loaded_track;
    public float track_length;
    public Sample loaded_track;
    public  JavaSoundAudioIO soundIO;
    public AudioContext audiosys;
    public Runner timer;
    final public double nano = pow(10,9);
    long sT;
    long eT;
    public ReverbEffect reverbEffect;
    public GainEffect gainEffect;
    public CompEffect compressor;
    public FilterEffect filter;


    AudioPlayer(JavaSoundAudioIO IO,Sample track )
    {
        timer = new Runner();
        sT =0; eT = 0;
        loaded_track = track;
        soundIO = IO;
        audiosys  = new AudioContext(IO);
                reverbEffect = new ReverbEffect(audiosys);
                filter = new FilterEffect();
                compressor = new CompEffect();
        gainEffect = new GainEffect(audiosys, 2);
        track_length = 10;
        ifPause = true;


    }

    public void get_moment_of_track()
    {
       //  moment_in_track
    }

    public void reset_moment() {
        moment_in_track = 0;
    }

    public void update_track(Sample track)
    {
        loaded_track = track;
    }

    public void updateIO(JavaSoundAudioIO IO)
    {

        soundIO = IO;
    }

    public void pause()
    {
        audiosys.stop();
        eT = System.currentTimeMillis();
        update_moment();
        TimeBar.ifTrueWait = true;
        ifPause = false;

    }

    public void stop()
    {
        audiosys.stop();
        moment_in_track = 0;
        TimeBar.ifTrueWait = false;
        TimeBar.ifTrue = false;
        ifPause = true;

    }

    public float return_track_length()
    {

        return track_length;

    }

    public void obtain_track_length()
    {
        track_length = loaded_track.getNumFrames()/loaded_track.getSampleRate();

    }

    public void update_moment()
    {
        moment_in_track += eT - sT;
    }

    public void start(JavaSoundAudioIO IO){
        updateIO(IO);
        audiosys = new AudioContext(soundIO);
        SamplePlayer player = new SamplePlayer(audiosys, loaded_track);
        player.start((float)moment_in_track);
        gainEffect.init(audiosys,2);

        UGen ugen1 = filter.addEffect(audiosys, player);
        UGen ugen2 = reverbEffect.addEffect(audiosys, ugen1);
        UGen ugen3 = compressor.addEffect(audiosys, ugen2);
        gainEffect.gain.addInput(ugen3);

        /*
        if (reverbEffect.ifOn==true){

            reverbEffect.initReverb(audiosys);
            reverbEffect.reverb.addInput(j);
            gainEffect.gain.addInput(reverbEffect.reverb);


        }else {
            gainEffect.gain.addInput(j);
        }
        */

        audiosys.out.addInput(gainEffect.gain);
        audiosys.start();// obiekt AudioContext w konstruktorze.
      // poczatek watku z odliczaniem czasu

        TimeBar.ifTrueWait = false;

        sT = System.currentTimeMillis();

        if (ifPause == true) {
            timer.run();
        }
        else
        {
            ifPause = true;
        }


    }
}



