package cspy.online.util;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class SystemInitializer {

    private String dataDirectoryString;
    private String basicDirectoryString;

    private Path dataDirectory;
    private List<Path> basicDirectory;

    public SystemInitializer(String dataDirectoryString, String basicDirectoryString) {
        this.dataDirectoryString = dataDirectoryString;
        this.basicDirectoryString = basicDirectoryString;
    }

    public void initDirectory() {

    }

}
