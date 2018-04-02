package sample;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.UGen;
import net.beadsproject.beads.ugens.BiquadFilter;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.*;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.ugens.SamplePlayer;

public class FilterEffect extends Effect {
    BiquadFilter LP;
    BiquadFilter HP;

    float qLP;
    float  fLP;

    float  qHP;
    float  fHP;

    public void FilterEffect(){
        ifOn = false;
        qLP=0.5f;
        fLP=15050;
        qHP=0.5f;
        fHP=50;
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
    UGen addEffect(AudioContext audiosys,UGen ugen)
    {
        if(ifOn==true)
        {

            initEffect(audiosys);
            LP.addInput(ugen);
            HP.addInput(LP);
            return HP;

        }
        else
        {
            return ugen;
        }
    }


    @Override
    void initEffect(AudioContext audiosys)
    {
        HP = new BiquadFilter(audiosys,BiquadFilter.BESSEL_HP, fHP, qHP);
        LP = new BiquadFilter(audiosys,BiquadFilter.BESSEL_LP, fLP, qLP);
    }



    public void setFreqLP (float val)
    {
        fLP = val;
    }

    public void setFreqHP (float val)
    {
        fHP = val;
    }

    public void setQLP (float val)
    {
        qLP = val;
    }

    public void setQHP (float val)
    {
        qLP = val;
    }
}
