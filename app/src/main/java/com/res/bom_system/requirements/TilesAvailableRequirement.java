package com.res.bom_system.requirements;

import com.res.bom_system.data.Model;

public class TilesAvailableRequirement implements IRequirements {

    @Override
    public boolean check(Model m) {
        return m.getTiles().size() > 0;
    }
}
