package com.res.bom_system;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.res.bom_system.commands.DeclineAllCommand;
import com.res.bom_system.commands.ICommand;
import com.res.bom_system.data.Model;
import com.res.bom_system.data.Reservation;
import com.res.bom_system.extension.DrawView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView core;
    private ImageView droid;
    private Button button_declineAll;


    private View.OnClickListener droidTapListener;
    private View.OnClickListener tableTapListener;
    private View.OnClickListener declineAllTapListener;

    private int counter = 0;

    final MailGunWrapper mgw = new MailGunWrapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lst = findViewById(R.id.Tilescolumn);
        List<String> tiles = new ArrayList<>();
        final Model m = new Model(lst,tiles, this);

        InitializeApp(m);
        launch(m);

        DrawView drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.BLACK);
        //setContentView(drawView);
        ((GridLayout)findViewById(R.id.gridLayout)).addView(drawView);

        Log.i("SIZE", "width: " + ((GridLayout)findViewById(R.id.gridLayout)).getWidth());
        Log.i("SIZE", "height: " + ((GridLayout)findViewById(R.id.gridLayout)).getHeight());

    }

    private void InitializeApp(final Model m){
        button_declineAll = findViewById(R.id.button_declineAll);

        // Define and attach listeners
        droidTapListener = new View.OnClickListener()  {
            public void onClick(View v) {
                TapDroid(m);
            }
        };

        tableTapListener = new View.OnClickListener()  {
            public void onClick(View v) {
                int id = v.getId();

                Reservation r = m.getTappedReservation();
                if(r != null){


                    //((ImageButton)findViewById(id)).setImageBitmap(getImageBitmap("../../res/user-default.png"));//.setColorFilter(Color.argb(255, 255, 12, 12));
                    ((ImageButton)findViewById(id)).setColorFilter(Color.argb(255, 255, 12, 12));
                    m.submitReservation(r,Integer.parseInt(getResources().getResourceEntryName(id).substring(11)));
                }
            }
        };

        declineAllTapListener = new View.OnClickListener()  {
            public void onClick(View v) {
                // decline all
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Bestätigungsaufforderung");
                builder.setMessage("Sind Sie sicher, dass sie alle angehenden Reservierungen ablehnen möchten?");
                builder.setPositiveButton("Ja",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                m.getCommands().add(new DeclineAllCommand());
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        };

        core = findViewById(R.id.core);
        droid = findViewById(R.id.droid);

        droid.setOnClickListener(droidTapListener);
        button_declineAll.setOnClickListener(declineAllTapListener);


        findViewById(R.id.imageButton1).setOnClickListener(tableTapListener);
        findViewById(R.id.imageButton2).setOnClickListener(tableTapListener);
        findViewById(R.id.imageButton3).setOnClickListener(tableTapListener);
        findViewById(R.id.imageButton4).setOnClickListener(tableTapListener);
    }


    /**
     * core run instance; fetches all commands etc.
     */
    private void launch(final Model m){


        Thread t1 = new Thread(new Runnable() {
            public void run()
            {
                for(;;){

                    final ICommand c = getMail(m);
                    if(c == null){
                        try
                        {
                            Thread.sleep(10000);
                            //Log.i("Pause", "pausing...");
                            m.getCommands().addAll(mgw.fetchMails());
                        }
                        catch(InterruptedException ex)
                        {
                            Thread.currentThread().interrupt();
                        }
                        continue;
                    }
                    Log.i("Main" , "Processing : " + c.toString());
                    if(c.verify(m)){
                        try{
                            // run gui thread on all commands
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        c.execute(m);
                                    }catch(Exception e){
                                        Log.e("tap",e.getMessage());
                                    }

                                }
                            });

                        }catch(Exception e){

                        }
                    }else{
                        Log.e("Verification", "Command verification failed.");
                    }
                }
            }});

        t1.start();
    }

    private ICommand getMail(Model m){

        ICommand c = m.getCommands().poll();
        return c;
    }



    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {

            URL aURL = new File(url).toURI().toURL();
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("TAG", "Error getting bitmap", e);
        }
        return bm;
    }




    private void TapDroid(Model m) {
        counter++;
        String temp;
        switch (counter)
        {
            case 1:
                temp = "once";
                break;
            case 2:
                temp = "twice";
                break;
            default:
                temp = String.format("%d times", counter);
        }
        core.setText(String.format("You touched the droid %s", temp));
    }
}
