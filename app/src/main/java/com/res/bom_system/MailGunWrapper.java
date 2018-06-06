package com.res.bom_system;

import com.res.bom_system.commands.ICommand;
import com.res.bom_system.commands.ReservationCommand;
import com.res.bom_system.data.Reservation;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MailGunWrapper {

    public MailGunWrapper(){

    }

    Set<ICommand> fetchMails(){

        Set<ICommand> result = new HashSet<>();
        result.add(new ReservationCommand(new Reservation(0,"Martin Freeman",4, "martin.weigle@yahoo.de", 0172, new Reservation.Date(19,33,3,5,2018) )));
        result.add(new ReservationCommand(new Reservation(1,"Christopher Kloss",2, "martin.weigle@yahoo.de", 0172, new Reservation.Date(18,20,3,5,2018) )));
        return result;
    }

    public void sendRejection(String email, String additionalNote){
        String msg = "Wir haben Ihre Reservierung hiermit bestätigt und freuen uns auf Ihren Besuch in unserem Restaurant!";
        sendMail(email, msg, additionalNote );
    }

    public void sendAcceptance(String email, String additionalNote){
        String msg = "Es tut uns Leid Ihnen mitteilen zu müssen, dass wir bereits voll ausgebucht sind und Ihnen ihren" +
                "angefragten Platz zu der Uhrzeit nicht reservieren können.";
        sendMail(email, msg, additionalNote );
    }

    private void sendMail(String email, String coreMsg, String additionalNote){

    }
}
