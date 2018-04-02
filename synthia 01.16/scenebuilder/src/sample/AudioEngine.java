package sample;

import java.util.*;
import net.beadsproject.beads.ugens.Noise;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.core.io.JavaSoundAudioIO;
import net.beadsproject.beads.ugens.*;
import net.beadsproject.beads.data.*;
import net.beadsproject.beads.data.audiofile.AudioFileType;
import javax.sound.sampled.*;

public class AudioEngine {

        //   public  Sample loaded_track;
        JavaSoundAudioIO soundIO;
        AudioContext audiosys;
        Mixer mixer;
        Vector<String> available_mixers;
        String active_mixer;
        AudioPlayer audio_thread;


    public AudioEngine(){ // konstruktor
        available_mixers = this.obtain_mixer(); // sprawdzenie linii wyjscia i formatow
                if(available_mixers.size()!=0) { // inicjacja defaultowego miksera, tak naprawde zostanie on zainicjowany dopiero po kliknieciu
                    active_mixer = available_mixers.get(0);
                    System.out.println(active_mixer);
                }
            soundIO = new JavaSoundAudioIO();
            soundIO.selectMixer(0);

        audio_thread = new AudioPlayer(soundIO, null);
        }



    public Vector<String> obtain_mixer(){ // funkcja sprawdzajaca jakie mamy miksery i wybierajaca na podstawie ilosci obslugiwanych formatow
        Vector<String> names = new Vector<String>();

        Mixer.Info[] mi = AudioSystem.getMixerInfo();
        for (Mixer.Info info : mi) {
            // System.out.println("MIXER INFO: " + info);
            Mixer m = AudioSystem.getMixer(info);
            // System.out.println("MIXER: " + m);
            Line.Info[] sl = m.getSourceLineInfo();
            for (Line.Info info2 : sl) {
                //  System.out.println("    LINE: " + info2);
                Line line;
                    try {
                        line = AudioSystem.getLine(info2);
                    }
                    catch (LineUnavailableException e )
                    {
                        line = null;
                    }
                    if (line instanceof SourceDataLine) {
                        names.addElement(info.toString());
                        SourceDataLine source = (SourceDataLine) line;
                        DataLine.Info i = (DataLine.Info) source.getLineInfo();
                        for (AudioFormat format : i.getFormats()) {
                            //    System.out.println("        FORMAT: " + format);
                    }
                }
            }
        }

        return names;
    }

    public void set_mixer(String name) // zmienia aktywny mixer przy wyborzez chooseboxa
    {
        active_mixer = name;
        System.out.println(active_mixer);

    }

    public void check_IO_before() // funkcja przed akcja zwaizana z np. play spradza aktualnie wybrany mixer
    {


    }

    public void play_sample()
    {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo(); // kod ktory otwiera audiocontext i przypisuje wybrany mixer do tego kontekstu
        int which_one = 0;
        for (int i =0; i < mixers.length; i++)
        {
          //  System.out.println(mixers[i].toString() + " " + active_mixer);
            if(mixers[i].toString().equals(active_mixer))
            {

              which_one = i;
                System.out.println(which_one);

            }
        }
       soundIO.selectMixer(which_one);
       audio_thread.start(soundIO);
    }

    public void stop_sample()
    {
        audio_thread.stop();
    }

    public void pause_sample()
    {
        audio_thread.pause();


    }



    public void read_file(String path) // funkcja wczytujaca
    {
        audio_thread.update_track(SampleManager.sample(path));
        audio_thread.obtain_track_length();
        audio_thread.reset_moment(); // resetujemy gdy wczytujemy nowy track do zera moment w ktorym ma odtwarzac
      //  Buffer buf = new Buffer(44100); nieprzydatny, na jego podstawie jest stworzona klasa sample?



        //  int chan = this.loaded_track.getNumChannels(); float samrate = this.loaded_track.getSampleRate();
        //  SampleAudioFormat format = new SampleAudioFormat(samrate, 16 , chan); // nie wiem jak z rozdzielczoscia // ale chyba sobie zeskaluje
    }

    public String[] file_types() // funkcja zwarcajaca obslugiwane typy plikow
    {
        final int n = 5;
        String[] filetype = new String[5];
        int inter = 0;
            for (AudioFileType c : AudioFileType.values())
            {
                filetype[inter] = "*." + c.toString();
                inter++;
            }
        return filetype;
    }




}

