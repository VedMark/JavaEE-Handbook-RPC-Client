import thrift.JavaEETechnology;

import java.util.List;

interface ProtocolPerformer {

    void connect(String address);
    List<JavaEETechnology> getAllTechnologies();
    void insert(JavaEETechnology technology);
    void delete(JavaEETechnology technology);
    void update(JavaEETechnology technology);
}
