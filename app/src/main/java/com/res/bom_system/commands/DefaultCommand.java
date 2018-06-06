package com.res.bom_system.commands;

import com.res.bom_system.data.Model;
import com.res.bom_system.requirements.IRequirements;

import java.util.List;

public class DefaultCommand extends ICommand {

    @Override
    public boolean verify( Model m) {
        return false;
    }

    @Override
    public void execute(Model model) throws Exception {

    }
}
