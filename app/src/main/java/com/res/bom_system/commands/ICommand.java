package com.res.bom_system.commands;

import com.res.bom_system.data.Model;
import com.res.bom_system.requirements.IRequirements;

import java.util.List;

public abstract class ICommand {

    public abstract boolean verify(Model m);

    public abstract void execute(Model model) throws Exception;
}
