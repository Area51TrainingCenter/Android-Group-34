package com.jcodee.mod3class7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends YouTubeBaseActivity {

    @BindView(R.id.etVideo)
    EditText etVideo;
    @BindView(R.id.youtubePlayer)
    YouTubePlayerView youtubePlayer;
    @BindView(R.id.lvLista)
    ListView lvLista;

    private VideoAdapter adapter;
    private YouTubePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new VideoAdapter(MainActivity.this, new ArrayList<String>());
        lvLista.setAdapter(adapter);

        youtubePlayer.initialize(
                getResources().getString(R.string.google_api),
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        //youTubePlayer.cueVideo("7JJfJgyHYwU");
                        player = youTubePlayer;
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }

    @OnClick(R.id.btnAgregar)
    public void onViewClicked() {

        String video = etVideo.getText().toString();
        adapter.agregar(video);
        if (player != null)
            player.cueVideos(adapter.getLista());

    }
}
