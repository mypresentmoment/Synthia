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
import net.beadsproject.beads.ugens.Gain.*;


public class GainEffect {
    Gain gain;
    float g;



    public GainEffect(AudioContext context, int inout){

        gain = new Gain(context, inout);

        g =0.5f;
        gain.setGain(g);


    }
    public void init(AudioContext context, int inout){
        gain = new Gain(context, inout);
        gain.setGain(g);
    }

    public void initGain(float a){
        g = a;
    }




}
