package sample;
import net.beadsproject.beads.ugens.SamplePlayer;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.*;
import net.beadsproject.beads.ugens.*;

public abstract class Effect {
    boolean ifOn;
    UGen output;
    abstract UGen addEffect(AudioContext audiosys,UGen ugen);
    abstract void initEffect(AudioContext audiosys);
    abstract public void changeIfOn();
    abstract public boolean getIfOn();

}
