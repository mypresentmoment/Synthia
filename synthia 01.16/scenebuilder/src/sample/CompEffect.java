package sample;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.BiquadFilter;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.SamplePlayer;
import net.beadsproject.beads.ugens.Compressor;
import net.beadsproject.beads.core.UGen;
public class CompEffect extends Effect {

    public float attack;
    public float decay;
    float knee;
    float ratio;
    float threshold;

    Compressor compressor;


    public void CompEffect()

    {
        attack = 100;
        decay = 0.5f;
        knee = 0.5f;
        ratio = 2;
        threshold = 0.5f;
        ifOn = false;
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
            compressor.addInput(ugen);
            return compressor;
        }
        else
        {
            return ugen;
        }
    }

    @Override
    void initEffect(AudioContext audiosys)
    {
        compressor = new Compressor(audiosys, 2);
        compressor.setAttack(attack);
        compressor.setDecay(decay);
        compressor.setKnee(knee);
        compressor.setRatio(ratio);
        compressor.setThreshold(threshold);
    }

    public void setAttack (float val)
    {
        attack = val;
    }

    public void setRatio (float val)
    {
        ratio = val;
    }

    public void setKnee (float val)
    {
        knee = val;
    }

    public void setThres (float val)
    {
        threshold = val;
    }
    public void setDecay (float val)
    {
        decay = val;
    }
}
