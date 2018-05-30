package com.nerkingames.mineclicker.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.nerkingames.mineclicker.R;

/**
 * Created by vladyslav on 22.02.18.
 */

public class MusicPlayer implements MediaPlayer.OnCompletionListener, AudioManager.OnAudioFocusChangeListener {


    Context context;
    private static MusicPlayer instanse;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    private boolean init = false;

    private MusicPlayer(Context context) {
        this.context = context;
    }

    public static synchronized MusicPlayer getInstanse(Context context){
        if (instanse == null){
            instanse = new MusicPlayer(context);
        }
        return instanse;
    }

    public void setInit(boolean init) {
        Log.d("Initting Player","somevone_set_new_value");
        this.init = init;
    }

    public boolean isInit(String tag){
        Log.d("Initting Player",tag);
        return init;
    }

    public boolean dontWork(){
        Log.d("MusicPlayer","dontWorkMetod");
        if(mediaPlayer != null) {
            Log.d("Method dontWork","passed");
            return !mediaPlayer.isPlaying();
        } else {return false;}
    }

    public void init(){
        Log.d("Initting Player","inside_player");
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void pause(){

        if(mediaPlayer != null) {
            mediaPlayer.pause();
        }
        if(mediaPlayer != null) {
            audioManager.abandonAudioFocus(this);
        }

        Log.d("player_info","i release audio focus");
    }



    public void start(){
        int requestAudioFocusResult = audioManager.requestAudioFocus(this,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
        if(requestAudioFocusResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            Log.d("player_info","i get audio focus");
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(this);
    }



    public void destroy() {
        Log.d("destroy","destroying player");
        if (mediaPlayer != null) {
            Log.d("media player","release");
            mediaPlayer.release();
        }
        if (this != null && audioManager != null ) {
            Log.d("audio manager","abandon audio focus");
            audioManager.abandonAudioFocus(this);
        }
        if(isInit("destroy method"))
        setInit(!isInit("Destroy_Method_Change_Value_Now_Player_Not_Init"));
    }

    public void playSong(int song)  {

            mediaPlayer = MediaPlayer.create(context,song);
            audioManager.requestAudioFocus(this,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this);


    }



    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d("onCompletion","next_song");
        this.playSong(R.raw.mainmusic);
    }




    @Override
    public void onAudioFocusChange(int i) {
        switch (i) {
            case AudioManager.AUDIOFOCUS_LOSS:
                Log.d("destroy","destroing player");
                if (mediaPlayer != null)
                    mediaPlayer.release();
                if (this != null)
                    audioManager.abandonAudioFocus(this);
                setInit(!isInit("Player_Init_Now_false_player_destroyed"));
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                mediaPlayer.pause();
                Log.d("Focus","AUDIOFOCUS_LOSS_TRANSIENT");

                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                Log.d("Focus","AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                mediaPlayer.setVolume(0.2f, 0.2f);
                break;
            case AudioManager.AUDIOFOCUS_GAIN:
                Log.d("Focus","AUDIOFOCUS_GAIN");
                mediaPlayer.setVolume(1.0f, 1.0f);
                break;
        }

    }
}

