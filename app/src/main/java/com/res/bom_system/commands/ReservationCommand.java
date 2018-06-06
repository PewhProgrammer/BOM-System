package com.res.bom_system.commands;

import android.widget.ArrayAdapter;
import com.res.bom_system.data.Model;
import com.res.bom_system.data.Reservation;

public class ReservationCommand extends ICommand {


    private final Reservation reservation;

    public ReservationCommand(Reservation reservation){
        this.reservation = reservation;
    }

    @Override
    public boolean verify(Model m) {
        return true;
    }

    @Override
    public void execute(Model model) throws Exception {
        // create tiles

        ArrayAdapter ad = model.getAdapter();
        model.getReservations().add(reservation);
        ad.add(model.getTiles().size() + " - Neue Reservierung:\n " + reservation);
        ad.notifyDataSetChanged();

    }

    @Override
    public String toString() {
        return "ReservationCommand: " + this.reservation;
    }
}
