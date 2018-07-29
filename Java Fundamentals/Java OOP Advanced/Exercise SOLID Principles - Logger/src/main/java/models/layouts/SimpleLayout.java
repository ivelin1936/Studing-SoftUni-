package models.layouts;

import interfaces.Layout;
import models.enums.ReportLevel;

public class SimpleLayout implements Layout {
    //displays logs in the format "<date-time> - <report level> - <message>")

    public SimpleLayout() {
    }

    @Override
    public String format(ReportLevel level, String date, String message) {
        return String.format("%s - %s - %s", date, level.name(), message);
    }
}
