package integrationtests;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;

/**
 *
 * @author harbu
 */
public class IntegrationTestHelper {
    public static String readInFile(String filename) throws IOException {
        return Resources.toString(Resources.getResource(filename), Charsets.UTF_8);
    }
}
