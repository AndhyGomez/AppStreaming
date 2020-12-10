/**
 * Author: Andhy Gomez
 *
 * Date Last Modified: 9/15/2020
 *
 * Description: Source code for, *app name in progress*. This app aims to allow individuals
 * to discretely live stream and record public interactions. The UI is a simple black screen with
 * a red button that starts to record and stream when pressed. Upon click the screen will also
 * turn black hiding the button to make it harder for someone to stop recording if the phone
 * is taken from the user.
 */

package io.antmedia.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.Nullable;

import io.antmedia.android.liveVideoBroadcaster.LiveVideoBroadcasterActivity;

public class MainActivity2 extends AppCompatActivity
{
    /**
     * Class Objects
     */
    private static int REQUEST_CAPTURE = 101;
    private Uri videoUri = null;

    Button redBoi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // OnClickListener for record button
        redBoi = (Button) findViewById(R.id.rec_btn);
        redBoi.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                //videoCapture(view);
                Intent i = new Intent(MainActivity2.this, LiveVideoBroadcasterActivity.class);
                startActivity(i);

            }
        });

    }

    public void videoCapture(View view)
    {
        Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if (captureIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(captureIntent, REQUEST_CAPTURE);
            //view.setBackgroundColor(Color.BLACK);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK)
        {
            videoUri = data.getData();
        }
    }

}
