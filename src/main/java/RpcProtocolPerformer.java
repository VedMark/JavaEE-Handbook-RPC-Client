import javafx.scene.control.Alert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.J2EeHandbookService;
import thrift.JavaEETechnology;

import java.util.ArrayList;
import java.util.List;


public class RpcProtocolPerformer implements ProtocolPerformer {
    private static final Logger log = LogManager.getLogger(RpcProtocolPerformer.class);
    private TTransport transport;
    private TProtocol protocol;
    private J2EeHandbookService.Client client;

    public void connect(String address) {
        try {
            String[] tokens = address.split(":");
            if(tokens.length != 2) {
                throw new Exception("Wrong address");
            }
            transport = new TSocket(tokens[0], Integer.parseInt(tokens[1]));
            transport.open();

            protocol = new TBinaryProtocol(transport);
            client = new J2EeHandbookService.Client(protocol);

        } catch (Exception e) {
            log.error(e.getMessage());
            showErrorAlert("Server error", e.getMessage());
        }
    }

    public List<JavaEETechnology> getAllTechnologies() {
        List<JavaEETechnology> returnedTechnologies = new ArrayList<>();

        try {
            returnedTechnologies = client.getAllTechnologies();
        } catch (Exception e) {
            log.error(e.getMessage());
            showErrorAlert("Server error", e.getMessage());
        }
        return returnedTechnologies;
    }

    @Override
    public void insert(JavaEETechnology technology) {
        try {
            client.addTechnology(technology);
        } catch (Exception e) {
            log.error(e.getMessage());
            showErrorAlert("Server error", e.getMessage());
        }
    }

    @Override
    public void delete(JavaEETechnology technology) {
        try {
            client.removeTechnology(technology);
        } catch (Exception e) {
            log.error(e.getMessage());
            showErrorAlert("Server error", e.getMessage());
        }
    }

    @Override
    public void update(JavaEETechnology technology) {
        try {
            client.updateTechnology(technology);
        } catch (Exception e) {
            log.error(e.getMessage());
            showErrorAlert("Server error", e.getMessage());
        }
    }

    static void showErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
