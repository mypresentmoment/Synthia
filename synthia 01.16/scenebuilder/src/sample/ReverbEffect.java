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
import net.beadsproject.beads.ugens.Reverb.*;


public class ReverbEffect extends Effect{
    // tworzę reverb, który będzie korzystać z klasy audio player, czyli z play, pause i stop
    Reverb reverb;

    float size;
    float earlyReflections;
    float damping;

    public ReverbEffect(AudioContext audiosys){

        reverb = new Reverb(audiosys);
        ifOn = false;
        reverb.setSize(1f);
        reverb.setValue(1f);
    }

    @Override
    public void changeIfOn()
    {
        ifOn = !ifOn;
    }

    @Override
    public boolean getIfOn(){
        return ifOn;
    }


    @Override
    UGen addEffect(AudioContext audiosys, UGen ugen)
    {
        if(ifOn==true)
        {
            initEffect(audiosys);
            reverb.addInput(ugen);
            return reverb;

        }
        else
        {
            return ugen;
        }
    }

    @Override
    void initEffect(AudioContext audiosys)
    {
        reverb = new Reverb(audiosys);
        reverb.setValue(earlyReflections);
        reverb.setSize(size);
        reverb.setDamping(damping);
    }




/*
    public void initReverb(AudioContext context){

        reverb = new Reverb(context);
        reverb.setValue(earlyReflections);
        reverb.setSize(size);
        reverb.setDamping(damping);


    }
*/
    public void setSize(float a){
        size = a;
    }

    public void setEarlyReflections(float b){
        earlyReflections=b;
    }

    public void setDamping(float c){
        damping=c;
    }
}