package com.res.bom_system.commands;

import com.res.bom_system.data.Model;


public class DeclineAllCommand extends ICommand {


    @Override
    public boolean verify( Model m) {
        if(m.getTiles().size() > 0) return true;
        return false;
    }

    @Override
    public void execute(Model model) throws Exception {
        model.getAdapter().clear();
    }
}
