package hm.circlerhythm;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundMusic {
    private MediaPlayer music;

    public SoundMusic(Context context){
        music = new MediaPlayer();
        music = MediaPlayer.create(context, R.raw.sunburst);
    }

    public void startMusic(){
        music.start();
    }

    public void stopMusic(){
        music.stop();
        music.reset();
    }

}
