import java.io.IOException;

public class CsvReadException extends IOException {

    private String data;

    public CsvReadException(String line){
        this.data = line;
    }

    @Override
    public String toString(){
        return "CsvReadException: " + data;
    }
}
