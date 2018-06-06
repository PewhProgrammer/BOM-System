package com.res.bom_system.data;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import com.res.bom_system.commands.ICommand;
import com.res.bom_system.commands.ReservationCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Model {

    private final ListView lst;
    private final AppCompatActivity app;
    private List<String> tiles;

    private Queue<ICommand> commands;
    private ArrayAdapter adapter;

    private List<Reservation> reservations;
    private Reservation tappedReservation;
    private View tappedView;



    public Model(ListView lst, List<String> tiles, final AppCompatActivity app){
        this.lst = lst;
        this.app = app;
        this.tiles = tiles;

        adapter = new ArrayAdapter(app, android.R.layout.simple_list_item_1 , tiles);

        // give adapter to ListView UI element to render
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // select one that currently has focus and unfocus it
                if(tappedReservation != null){
                    //Log.i("Model","index: " + ((TextView)adapter.getView(reservations.indexOf(tappedReservation),null,null)));
                    ((TextView)tappedView).setTextColor(Color.BLACK);
                }

                tappedView = view;
                tappedReservation = getReservations().get((int)id);
                ((TextView)view).setTextColor(Color.BLUE);

            }
        });

        reservations = new LinkedList<>();

        // creating mail queue
        commands = new LinkedList<>();
        commands.add(new ReservationCommand(new Reservation(0,"Martin Krauss", 2,"martin.weigle@yahoo.de", 0172, new Reservation.Date(20,22,3,6,2018) )));
        commands.add(new ReservationCommand(new Reservation(1,"Sascha Freund",5, "martin.weigle@yahoo.de", 0172, new Reservation.Date(19,15,15,6,2018) )));
        commands.add(new ReservationCommand(new Reservation(2,"Fatusch Lamusch",12, "martin.weigle@yahoo.de", 0172, new Reservation.Date(18,55,6,6,2018) )));
    }

    public Reservation getTappedReservation() {
        return tappedReservation;
    }

    public void submitReservation(Reservation r, int id){
        ((TextView)tappedView).setTextColor(Color.BLACK);
        tappedReservation = null;
        int i = reservations.indexOf(r);
        reservations.remove(r);
        adapter.remove(adapter.getItem(i));

        adapter.notifyDataSetChanged();

        //TODO send email to r.email with id table

    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public ArrayAdapter getAdapter() {
        return adapter;
    }

    public ListView getLst() {
        return lst;
    }

    public AppCompatActivity getApp() {
        return app;
    }

    public List<String> getTiles() {
        return tiles;
    }

    public Queue<ICommand> getCommands() {
        return commands;
    }
}
