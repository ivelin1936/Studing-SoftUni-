package engine;

import IO.impl.ConsoleReader;
import IO.impl.ConsoleWriter;
import IO.interfaces.Reader;
import IO.interfaces.Writer;
import constants.Config;
import controllers.HealthManager;

public class OrganismEngine {

    private HealthManager healthManager;
    private Reader reader;
    private Writer writer;

    public OrganismEngine() {
        this.healthManager = new HealthManager();
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
    }

    public void run() {
        String command = this.reader.readLine();
        while (true) {
            if (Config.END_INPUT_SEQUENCE.equalsIgnoreCase(command)) {
                return;
            }

            String[] cmdArgs = command.split("\\s+");

            switch (cmdArgs[0]){
                case "checkCondition":
                    String result = this.healthManager.checkCondition(cmdArgs[1]);
                    if (!Config.EMPTY_STRING.equals(result)){
                        this.writer.write(result);
                    }
                    break;
                case "createOrganism":
                    this.writer.writeLine(
                            this.healthManager.createOrganism(cmdArgs[1]));
                    break;
                case "addCluster":
                    String organismName = cmdArgs[1];
                    String id = cmdArgs[2];
                    int rows = Integer.parseInt(cmdArgs[3]);
                    int cols = Integer.parseInt(cmdArgs[4]);

                    try {
                        this.writer.writeLine(
                                this.healthManager.addCluster(organismName, id, rows, cols));
                    } catch (IllegalArgumentException ignored) {
                        ;
                    }
                    break;
                case "addCell":
                    String orgName = cmdArgs[1];
                    String clusterId = cmdArgs[2];
                    String cellType = cmdArgs[3];
                    String cellId = cmdArgs[4];
                    int health = Integer.parseInt(cmdArgs[5]);
                    int positionRow = Integer.parseInt(cmdArgs[6]);
                    int positionCol = Integer.parseInt(cmdArgs[7]);
                    int additionalProperty = Integer.parseInt(cmdArgs[8]);

                    try {
                        this.writer.writeLine(
                                this.healthManager.addCell(
                                        orgName,
                                        clusterId,
                                        cellType,
                                        cellId,
                                        health,
                                        positionRow,
                                        positionCol,
                                        additionalProperty));
                    } catch (IllegalArgumentException ignored) {
                        ;
                    }
                    break;
                case "activateCluster":
                    String organism = cmdArgs[1];
                    String resultString = this.healthManager.activateCluster(organism);

                    if (!Config.EMPTY_STRING.equals(resultString)) {
                        this.writer.writeLine(resultString);
                    }
                    break;
            }

            command = this.reader.readLine();
        }
    }
}
