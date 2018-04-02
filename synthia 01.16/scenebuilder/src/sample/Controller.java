package sample;
import java.util.*;
import java.io.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.beans.value.*;
import static java.lang.Thread.currentThread;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import java.util.concurrent.TimeUnit;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
public class Controller {
   // AudioThread myThread;
    AudioEngine audiosys = new AudioEngine(); // obiekt nowy audioengine
    double a;
    double b;
    double c;
    double d;




    @FXML
    private Button chooseButton ;

    @FXML
    private Button playButton ;
    @FXML
    private Button stopButton ;
    @FXML
    private Button pauseButton ;
    @FXML
    private ChoiceBox chooseoutput ;
    @FXML
    private RadioButton TurnOnReverb;
    @FXML
    private RadioButton TurnOnEqualizer;
    @FXML
    private RadioButton TurnOnComp;
    @FXML
    private Slider roomSize;
    @FXML
    private Slider earlyRefl;
    @FXML
    private Slider damping;
    @FXML
    private Slider gainSlider;
    @FXML
    private Slider timebar;
    @FXML
    static public FloatProperty floatProperty;
    @FXML
    private Slider freqHP;
    @FXML
    private Slider qHP;
    @FXML
    private Slider freqLP;
    @FXML
    private Slider attack;
    @FXML
    private Slider ratio;
    @FXML
    private Slider knee;
    @FXML
    private Slider thres;
    @FXML
    private Slider qLP;
    @FXML
    private Slider decay;
    @FXML
    private Slider drywet;



    @FXML
    public void initialize() {


        floatProperty = new SimpleFloatProperty(0);
        floatProperty.addListener((observable, oldValue, newValue) -> {
           timebar.setValue(newValue.doubleValue());
           if(newValue.doubleValue() > audiosys.audio_thread.return_track_length() )
           {
               audiosys.stop_sample();
               playButton.setDisable(false);
               TurnOnReverb.setDisable(false);
               roomSize.setDisable(false);
               earlyRefl.setDisable(false);
               damping.setDisable(false);
               stopButton.setDisable(true);
               gainSlider.setDisable(false);
               pauseButton.setDisable(true);
           }
        });
        //obrazki buttonow



        chooseoutput.getItems().addAll(audiosys.obtain_mixer());
        chooseoutput.setValue(audiosys.obtain_mixer().get(0));
        chooseoutput.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                Vector<String> mixer_names = audiosys.obtain_mixer();
                String[] items = mixer_names.toArray(new String[mixer_names.size()]);
                audiosys.set_mixer(items[new_value.intValue()]);
            }
        });

        playButton.setDisable(true);
        stopButton.setDisable(true);
        pauseButton.setDisable(true);
        TurnOnReverb.setDisable(true);
        damping.setDisable(true);
        roomSize.setDisable(true);
        earlyRefl.setDisable(true);
        attack.setDisable(true);
        decay.setDisable(true);
        ratio.setDisable(true);
        thres.setDisable(true);
        knee.setDisable(true);
        freqHP.setDisable(true);
        freqLP.setDisable(true);
        qHP.setDisable(true);
        qLP.setDisable(true);
        TurnOnComp.setDisable(true);
        TurnOnEqualizer.setDisable(true);
        gainSlider.setDisable(true);
        drywet.setDisable(true);
        //damping.disableProperty().bind(Bindings.createBooleanBinding(() -> ));

        drywet.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean wasChanging,
                    Boolean changing) {


                if (changing) {


                } else {
                    a = roomSize.getValue();
                    float f = (float) a;
                  //  audiosys.audio_thread.reverbEffect.setSize(f);
                   // System.out.println(f);
                }
            }
        });

        roomSize.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean wasChanging,
                    Boolean changing) {


                if (changing) {


                    } else {
                    a = roomSize.getValue();
                    float f = (float) a;
                    audiosys.audio_thread.reverbEffect.setSize(f);

                    }
                    }
        });

        earlyRefl.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean wasChanging,
                    Boolean changing) {


                if (changing) {


                } else {
                    b = earlyRefl.getValue();
                    float g = (float)b;
                    audiosys.audio_thread.reverbEffect.setEarlyReflections(g);
                }
            }
        });

        damping.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean wasChanging,
                    Boolean changing) {


                if (changing) {


                } else {
                    c = damping.getValue();
                    float h = (float)c;
                    audiosys.audio_thread.reverbEffect.setDamping(h);
                }
            }
        });

        gainSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = gainSlider.getValue();
                    float j= (float) d;
                    audiosys.audio_thread.gainEffect.initGain(j);
                }
            }
        });

        freqHP.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = freqHP.getValue();
                    float j = (float) d;
                    audiosys.audio_thread.filter.setFreqHP(j);
                }
            }
        });

        freqLP.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = freqLP.getValue();
                    float j = (float) d;
                    audiosys.audio_thread.filter.setFreqLP(j);
                }
            }
        });

        qLP.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = qLP.getValue();
                    float j= (float) d;
                    audiosys.audio_thread.filter.setQLP(j);
                }
            }
        });

        qHP.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = qHP.getValue();
                    float j= (float) d;
                    audiosys.audio_thread.filter.setQHP(j);
                }
            }
        });

        attack.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = attack.getValue();
                    float j= (float) d;
                    audiosys.audio_thread.compressor.setAttack(j);
                }
            }
        });

       ratio.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = ratio.getValue();
                    float j= (float) d;
                    audiosys.audio_thread.compressor.setRatio(j);
                }
            }
        });

        thres.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = thres.getValue();
                    float j= (float) d;
                    audiosys.audio_thread.compressor.setThres(j);
                }
            }
        });

        knee.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = knee.getValue();
                    float j= (float) d;
                    audiosys.audio_thread.compressor.setKnee(j);
                }
            }
        });

        decay.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean wasChanging,
                                Boolean changing) {
                if (changing) {


                } else {
                    d = decay.getValue();
                    float j= (float) d;
                    audiosys.audio_thread.compressor.setDecay(j);
                }
            }
        });

        pauseButton.setOnAction(this::onPauseClicked);
        playButton.setOnAction(this::onPlayClicked);
        stopButton.setOnAction(this::onStopClicked);


    }



    @FXML
    public void onPlayClicked(ActionEvent e) {
        audiosys.play_sample();

        playButton.setDisable(true);
        TurnOnReverb.setDisable(true);
        stopButton.setDisable(false);
        pauseButton.setDisable(false);
        damping.setDisable(true);
        earlyRefl.setDisable(true);
        roomSize.setDisable(true);
        gainSlider.setDisable(true);

        attack.setDisable(true);
        decay.setDisable(true);
        ratio.setDisable(true);
        thres.setDisable(true);
        knee.setDisable(true);
        freqHP.setDisable(true);
        freqLP.setDisable(true);
        qHP.setDisable(true);
        qLP.setDisable(true);
        TurnOnComp.setDisable(true);
        TurnOnEqualizer.setDisable(true);
        drywet.setDisable(true);
    }

    @FXML
    public void onStopClicked(ActionEvent e) {

        audiosys.stop_sample();

        playButton.setDisable(false);
        TurnOnReverb.setDisable(false);
        roomSize.setDisable(false);
        earlyRefl.setDisable(false);
        damping.setDisable(false);
        stopButton.setDisable(true);
        gainSlider.setDisable(false);
        pauseButton.setDisable(true);

        attack.setDisable(false);
        decay.setDisable(false);
        ratio.setDisable(false);
        thres.setDisable(false);
        knee.setDisable(false);
        freqHP.setDisable(false);
        freqLP.setDisable(false);
        qHP.setDisable(false);
        qLP.setDisable(false);
        TurnOnComp.setDisable(false);
        TurnOnEqualizer.setDisable(false);
        drywet.setDisable(false);


    }
    @FXML
    public void onPauseClicked(ActionEvent e )
    {
        audiosys.pause_sample();

        TurnOnReverb.setDisable(false);
        playButton.setDisable(false);
        stopButton.setDisable(false);
        gainSlider.setDisable(false);
        roomSize.setDisable(false);
        earlyRefl.setDisable(false);
        damping.setDisable(false);
        pauseButton.setDisable(true);

        attack.setDisable(false);
        decay.setDisable(false);
        ratio.setDisable(false);
        thres.setDisable(false);
        knee.setDisable(false);
        freqHP.setDisable(false);
        freqLP.setDisable(false);
        qHP.setDisable(false);
        qLP.setDisable(false);
        TurnOnComp.setDisable(false);
        TurnOnEqualizer.setDisable(false);
        drywet.setDisable(false);
    }

    @FXML
    public void onButtonClicked(ActionEvent e) {
        String[] types = audiosys.file_types(); // trzowy wektor plikow wav, audio engine

                    audiosys.stop_sample();



        Label chosen = new Label();
        if (e.getSource() == chooseButton) {
            FileChooser chooser = new FileChooser();

            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Audio", types));

            File file = chooser.showOpenDialog(chooseButton.getScene().getWindow());

            if (file != null ) {
                audiosys.read_file(file.getPath());

                chosen.setText("Chosen: " + file.getName());

                playButton.setDisable(false);
                TurnOnReverb.setDisable(false);
                roomSize.setDisable(false);
                earlyRefl.setDisable(false);
                damping.setDisable(false);
                stopButton.setDisable(true);
                gainSlider.setDisable(false);
                pauseButton.setDisable(true);

                attack.setDisable(false);
                decay.setDisable(false);
                ratio.setDisable(false);
                thres.setDisable(false);
                knee.setDisable(false);
                freqHP.setDisable(false);
                freqLP.setDisable(false);
                qHP.setDisable(false);
                qLP.setDisable(false);
                TurnOnComp.setDisable(false);
                TurnOnEqualizer.setDisable(false);
                drywet.setDisable(false);

                audiosys.audio_thread.compressor.setAttack(1);
                audiosys.audio_thread.compressor.setRatio(1);
                audiosys.audio_thread.compressor.setDecay(0.5f);
                audiosys.audio_thread.compressor.setThres(0.5f);
                audiosys.audio_thread.compressor.setKnee(0.5f);

                audiosys.audio_thread.filter.setFreqHP(50);
                audiosys.audio_thread.filter.setFreqLP(15050);
                audiosys.audio_thread.filter.setQLP(0.5f);
                audiosys.audio_thread.filter.setQHP(0.5f);

                audiosys.audio_thread.reverbEffect.setDamping(0.5f);
                audiosys.audio_thread.reverbEffect.setEarlyReflections(0.5f);
                audiosys.audio_thread.reverbEffect.setSize(0.5f);

                ;
            } else {
                chosen.setText(null);
            }
        }
        timebar.setMax(audiosys.audio_thread.return_track_length());
        playButton.setDisable(false);

    }
    @FXML
    public void onReverbRadioClicked(ActionEvent e) {

        audiosys.audio_thread.reverbEffect.changeIfOn();
        if(audiosys.audio_thread.reverbEffect.getIfOn()==true){
            damping.setDisable(false);
            roomSize.setDisable(false);
            earlyRefl.setDisable(false);
        }


    }

    @FXML
    public void onFilterRadioClicked(ActionEvent e) {

        audiosys.audio_thread.filter.changeIfOn();
        if(audiosys.audio_thread.filter.getIfOn()==true){
            freqLP.setDisable(false);
            qLP.setDisable(false);
            freqHP.setDisable(false);
            qHP.setDisable(false);

        }

    }

    @FXML
    public void onCompRadioClicked(ActionEvent e) {

        audiosys.audio_thread.compressor.changeIfOn();
        if(audiosys.audio_thread.compressor.getIfOn()==true){
            attack.setDisable(false);
            knee.setDisable(false);
            ratio.setDisable(false);
            decay.setDisable(false);
            thres.setDisable(false);
        }


    }




}
