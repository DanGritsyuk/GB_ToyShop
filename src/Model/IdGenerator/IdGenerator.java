package Model.IdGenerator;
import java.io.Serializable;

public class IdGenerator implements Serializable {
    private int id;

    private static IdGenerator idGenerator;

    private IdGenerator() {
    }

    public int GetNewId() {
        return id++;
    }

    public static IdGenerator getIdGenerator() {
        if (idGenerator == null)
            idGenerator = new IdGenerator();
        return idGenerator;
    }
}
