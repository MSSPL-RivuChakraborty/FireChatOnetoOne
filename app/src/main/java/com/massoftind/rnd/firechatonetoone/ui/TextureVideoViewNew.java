package com.massoftind.rnd.firechatonetoone.ui;

/**
 * Created by Developer on 18-06-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.MediaController;

import java.io.IOException;

public class TextureVideoViewNew extends TextureView {

    private static final String LOG_TAG = "VideoView";

    // all possible internal states
    private static final int STATE_ERROR              = -1;
    private static final int STATE_IDLE               = 0;
    private static final int STATE_PREPARING          = 1;
    private static final int STATE_PREPARED           = 2;
    private static final int STATE_PLAYING            = 3;
    private static final int STATE_PAUSED             = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;

    public int number;

    // currentState is a VideoView object's current state.
    // targetState is the state that a method caller intends to reach.
    // For instance, regardless the VideoView object's current state,
    // calling pause() intends to bring the object to a target state
    // of STATE_PAUSED.
    private int currentState = STATE_IDLE;
    private int targetState = STATE_IDLE;

    // Stuff we need for playing and showing a video
    private MediaPlayer mediaPlayer;
    private int videoWidth;
    private int videoHeight;
    private int surfaceWidth;
    private int surfaceHeight;
    private SurfaceTexture surfaceTexture;
    private Surface surface;
    private MediaController mediaController;
    private MediaPlayer.OnCompletionListener onCompletionListener;
    //private MediaPlayer.OnPreparedListener onPreparedListener;
    private MediaPlayer.OnPreparedListener onPreparedListener;
    private int currentBufferPercentage;
    private MediaPlayer.OnErrorListener onErrorListener;
    private MediaPlayer.OnInfoListener onInfoListener;

    private Uri uri;
    private int length = 0;
    private Context mContext;

    public TextureVideoViewNew(final Context context) {
        super(context);
        mContext = context;
        initVideoView();
    }

    public TextureVideoViewNew(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initVideoView();
    }

    public TextureVideoViewNew(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initVideoView();
    }

    public void initVideoView() {
        // //Log.dLOG_TAG, "Initializing video view.");
        videoHeight = 0;
        videoWidth = 0;
        setFocusable(false);
        setSurfaceTextureListener(surfaceTextureListener);
    }

    public int resolveAdjustedSize(int desiredSize, int measureSpec) {
        // //Log.dLOG_TAG, "Resolve called.");
        int result = desiredSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize =  MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                /* Parent says we can be as big as we want. Just don't be larger
                 * than max size imposed on ourselves.
                 */
                result = desiredSize;
                break;

            case MeasureSpec.AT_MOST:
                /* Parent says we can be as big as we want, up to specSize.
                 * Don't be larger than specSize, and don't be larger than
                 * the max size imposed on ourselves.
                 */
                result = Math.min(desiredSize, specSize);
                break;

            case MeasureSpec.EXACTLY:
                // No choice. Do what we are told.
                result = specSize;
                break;
        }
        return result;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
        onCompletionListener = listener;
    }
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener listener) {
        onPreparedListener = listener;
    }
    public void setOnErrorListener(MediaPlayer.OnErrorListener listener) {
        onErrorListener = listener;
    }


    public void setVideoPath(String path) {
      //Log.dLOG_TAG, "Setting video path to: " + path);
        setVideoURI(Uri.parse(path));
    }

    public void setVideoURI(Uri _videoURI) {
        uri = _videoURI;
        requestLayout();
        invalidate();
        openVideo();
    }

    public void setSurfaceTexture(SurfaceTexture _surfaceTexture) {
        surfaceTexture = _surfaceTexture;
    }

    public void openVideo() {
        if ((uri == null) || (surfaceTexture == null)) {
            // //Log.dLOG_TAG, "Cannot open video, uri or surface is null number " + number);
            return;
        }
        // Tell the music playback service to pause
        // TODO: these constants need to be published somewhere in the framework.
        Intent i = new Intent("com.android.music.musicservicecommand");
        i.putExtra("command", "pause");
        mContext.sendBroadcast(i);
        // //Log.dLOG_TAG, "Opening video.");
        release(false);
        try {
            surface = new Surface(surfaceTexture);
            // //Log.dLOG_TAG, "Creating media player number " + number);
            mediaPlayer = new MediaPlayer();
            // //Log.dLOG_TAG, "Setting surface.");
            mediaPlayer.setSurface(surface);
            // //Log.dLOG_TAG, "Setting data source.");
            mediaPlayer.setDataSource(mContext, uri);
            // //Log.dLOG_TAG, "Setting media player listeners.");
            mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);
            mediaPlayer.setOnCompletionListener(completeListener);
            mediaPlayer.setOnPreparedListener(preparedListener);
            mediaPlayer.setOnErrorListener(errorListener);
            mediaPlayer.setOnVideoSizeChangedListener(videoSizeChangedListener);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // //Log.dLOG_TAG, "Preparing media player.");
            mediaPlayer.prepareAsync();
            currentState = STATE_PREPARING;
        } catch (IllegalStateException e) {
            currentState = STATE_ERROR;
            targetState = STATE_ERROR;
            // //Log.dLOG_TAG, e.getMessage()); //TODO auto-generated catch block
        } catch (IOException e) {
            currentState = STATE_ERROR;
            targetState = STATE_ERROR;
            // //Log.dLOG_TAG, e.getMessage()); //TODO auto-generated catch block
        }
    }

    public void stopPlayback() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    public void pausePlayback() {
        if (isPlaying() && mediaPlayer != null) {
            length = mediaPlayer.getCurrentPosition()+2;
            mediaPlayer.pause();
            // //Log.d"length","end paused state length : "+length);
            currentState = STATE_PAUSED;
            targetState = STATE_PAUSED;
        }
    }

    private void release(boolean cleartargetstate) {
        // //Log.dLOG_TAG, "Releasing media player.");
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            currentState = STATE_IDLE;
            if (cleartargetstate) {
                targetState  = STATE_IDLE;
            }
            // //Log.dLOG_TAG, "Released media player.");
        } else {
            // //Log.dLOG_TAG, "Media player was null, did not release.");
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        // Will resize the view if the video dimensions have been found.
        // video dimensions are found after onPrepared has been called by MediaPlayer
        // //Log.dLOG_TAG, "onMeasure number " + number);
        int width = getDefaultSize(videoWidth, widthMeasureSpec);
        int height = getDefaultSize(videoHeight, heightMeasureSpec);
        if ((videoWidth > 0) && (videoHeight > 0)) {
            if ((videoWidth * height) > (width * videoHeight)) {
                // //Log.dLOG_TAG, "Image too tall, correcting.");
                height = (width * videoHeight) / videoWidth;
            } else if ((videoWidth * height) < (width * videoHeight)) {
                // //Log.dLOG_TAG, "Image too wide, correcting.");
                width = (height * videoWidth) / videoHeight;
            } else {
                // //Log.dLOG_TAG, "Aspect ratio is correct.");
            }
        }
        // //Log.dLOG_TAG, "Setting size: " + width + '/' + height + " for number " + number);
        setMeasuredDimension(width, height);
    }

    public boolean isPlaying() {
        return isInPlaybackState() && mediaPlayer.isPlaying();
    }

    public void start() {
        // This can potentially be called at several points, it will go through when all conditions are ready
        // 1. When setting the video URI
        // 2. When the surface becomes available
        // 3. From the activity
        if(currentState == STATE_PAUSED)
        {
            // //Log.dLOG_TAG, "start from paused state : " + currentState);
            // //Log.dLOG_TAG, "start from paused state length : " + length);
            mediaPlayer.seekTo(length);
            mediaPlayer.start();
            currentState = STATE_PLAYING;
        }
        else {
            if (isInPlaybackState()) {
                // //Log.dLOG_TAG, "Starting media player for number " + number);
                mediaPlayer.start();
                currentState = STATE_PLAYING;
            } else {
                // //Log.dLOG_TAG, "Could not start. Current state " + currentState);
            }
        }
        targetState = STATE_PLAYING;
    }

    private boolean isInPlaybackState() {
        return ((mediaPlayer != null) &&
                (currentState != STATE_ERROR) &&
                (currentState != STATE_IDLE) &&
                (currentState != STATE_PREPARING));
    }

    // Listeners
    private MediaPlayer.OnBufferingUpdateListener bufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(final MediaPlayer mp, final int percent) {
            currentBufferPercentage = percent;
        }
    };

    private MediaPlayer.OnCompletionListener completeListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(final MediaPlayer mp) {
            currentState = STATE_PLAYBACK_COMPLETED;
            targetState = STATE_PLAYBACK_COMPLETED;
            // //Log.dLOG_TAG, "Video completed number " + number);
            surface.release();
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(mp);
            }
        }
    };

    private MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(final MediaPlayer mp) {
            currentState = STATE_PREPARED;
            // //Log.dLOG_TAG, "Video prepared for " + number);
            videoWidth = mp.getVideoWidth();
            videoHeight = mp.getVideoHeight();
            requestLayout();
            invalidate();
            if ((videoWidth != 0) && (videoHeight != 0)) {
                // //Log.dLOG_TAG, "Video size for number " + number + ": " + videoWidth + '/' + videoHeight);
                if (targetState == STATE_PLAYING) {
                    mediaPlayer.start();
                }
            } else {
                if (targetState == STATE_PLAYING) {
                    mediaPlayer.start();
                }
            }
            if(onPreparedListener != null)
                onPreparedListener.onPrepared(mp);

        }
    };

    private MediaPlayer.OnVideoSizeChangedListener videoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(final MediaPlayer mp, final int width, final int height) {
            // //Log.dLOG_TAG, "Video size changed " + width + '/' + height + " number " + number);
        }
    };

    private MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(final MediaPlayer mp, final int what, final int extra) {
            currentState = STATE_ERROR;
            targetState = STATE_ERROR;
            //Log.d(LOG_TAG, "There was an error during video playback.");
            if(onErrorListener != null)
                onErrorListener.onError(mp,what,extra);
            return true;
        }
    };

    SurfaceTextureListener surfaceTextureListener = new SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(final SurfaceTexture surface, final int width, final int height) {
            // //Log.dLOG_TAG, "Surface texture now avaialble.");
            surfaceTexture = surface;
            openVideo();
        }

        @Override
        public void onSurfaceTextureSizeChanged(final SurfaceTexture surface, final int width, final int height) {
            // //Log.dLOG_TAG, "Resized surface texture: " + width + '/' + height);
            surfaceWidth = width;
            surfaceHeight = height;
            boolean isValidState =  (targetState == STATE_PLAYING);
            boolean hasValidSize = (videoWidth == width && videoHeight == height);
            if (mediaPlayer != null && isValidState && hasValidSize) {
                start();
            }
        }

        @Override
        public boolean onSurfaceTextureDestroyed(final SurfaceTexture surface) {
            Log.i(LOG_TAG, "Destroyed surface number " + number);
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(final SurfaceTexture surface) {

        }
    };

    public void setLooping(boolean isLooping)
    {
        mediaPlayer.setLooping(isLooping);
    }
}