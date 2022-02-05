import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Unzip {
    public static void main(String[] args) throws IOException {
        String src = "input.zip";
        String out = "output\\";

        ZipFile zipFile;
        try
        {
            zipFile = new ZipFile(new File(src));
        }
        catch (IOException ex)
        {
            System.out.print("input zip is missing! '"+src+"'");
            return;
        }

        Enumeration<? extends ZipEntry> entryList = zipFile.entries();
        while(entryList.hasMoreElements()) {
            ZipEntry entry = entryList.nextElement();
            String name = entry.getName();

            File outFile = new File(out+name);

            if(!entry.isDirectory())
            {
                InputStream stream = zipFile.getInputStream(entry);

                Files.createDirectories(Path.of(outFile.getParent()));

                java.nio.file.Files.copy(
                        stream,
                        outFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
