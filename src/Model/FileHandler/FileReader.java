package Model.FileHandler;

public interface FileReader<T> {
    T read(String filePath);
}