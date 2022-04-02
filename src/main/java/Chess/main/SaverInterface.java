package Chess.main;

import java.io.FileNotFoundException;

public interface SaverInterface {
    
    public void reset(String fileName);

    public void save(String fileName, boolean turnBack) throws FileNotFoundException;

    public void load(String fileName) throws FileNotFoundException;

}
