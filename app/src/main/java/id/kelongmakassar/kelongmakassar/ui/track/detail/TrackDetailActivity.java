package id.kelongmakassar.kelongmakassar.ui.track.detail;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.Track;
import id.kelongmakassar.kelongmakassar.services.MediaPlayerService;

public class TrackDetailActivity extends AppCompatActivity {

    private static final String SERVICE_STATE = "SERVICE_STATE";
    private static final String TRACK = "id.kelongmakassar.kelongmakassar.ui.track.detail.TrackDetailActivity.TRACK";

    public static final String Broadcast_PLAY_NEW_AUDIO = "id.kelongmakassar.kelongmakassar.PlayNewAudio";

    @BindView(R.id.seekbar_status_song) SeekBar statusSongSeekBar;

    private MediaPlayerService mMediaPlayerService;
    private boolean isServiceBound = false;
    private Track mTrack;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // We've bound to the Localservice, cast the IBinder and get Localservice instance
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) iBinder;
            mMediaPlayerService = binder.getService();
            isServiceBound = true;

            Toast.makeText(TrackDetailActivity.this, "Service Bound!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isServiceBound = false;
        }
    };

    public static Intent getStartIntent(Context context, Track track) {
        Intent intent = new Intent(context, TrackDetailActivity.class);
        intent.putExtra(TRACK, track);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_detail);

        if (savedInstanceState != null) {
            mTrack = savedInstanceState.getParcelable(TRACK);
        } else {
            mTrack = getIntent().getParcelableExtra(TRACK);
        }

        statusSongSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        onPlayClick();
    }

    @OnClick(R.id.button_control)
    void onMediaControlClick() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(SERVICE_STATE, isServiceBound);
        outState.putParcelable(TRACK, mTrack);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isServiceBound = savedInstanceState.getBoolean(SERVICE_STATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isServiceBound) {
            unbindService(serviceConnection);

            mMediaPlayerService.stopSelf();
        }
    }

    public void onPlayClick() {
        playAudio(resolveLocalMediaResourceUri(mTrack.getResId()));
    }

    private Uri resolveLocalMediaResourceUri(int resId) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(getResources().getResourcePackageName(resId))
                .appendPath(getResources().getResourceTypeName(resId))
                .appendPath(getResources().getResourceEntryName(resId))
                .build();

        return uri;
    }

    private void playAudio(Uri media) {
        // check if service is active
        if (!isServiceBound) {
            Intent playerIntent = MediaPlayerService.getStartIntent(this, media);
            startService(playerIntent);
            bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else {
            //Service is active
            //Send media with BroadcastReceiver
            Intent broadcastIntent = new Intent(Broadcast_PLAY_NEW_AUDIO);
            sendBroadcast(broadcastIntent);
        }
    }
}
