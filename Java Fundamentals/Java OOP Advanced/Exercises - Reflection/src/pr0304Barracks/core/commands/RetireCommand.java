package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;

public class RetireCommand extends BaseCommand {

    private final String UNIT_RETIRED_MSG = "%s retired!";

    public RetireCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = super.getData()[1];

        try {
            super.getRepository().removeUnit(unitType);
        } catch (IllegalArgumentException iae) {
            return iae.getLocalizedMessage();
        }
        return String.format(UNIT_RETIRED_MSG, unitType);
    }
}
