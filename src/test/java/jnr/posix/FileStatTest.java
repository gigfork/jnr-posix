
package jnr.posix;

import jnr.posix.Solaris64FileStat;
import jnr.posix.FileStat;
import jnr.posix.SolarisHeapFileStat;
import jnr.posix.POSIX;
import jnr.posix.POSIXFactory;
import com.kenai.jaffl.struct.StructUtil;
import java.io.File;
import jnr.posix.util.Platform;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileStatTest {

    public FileStatTest() {
    }

    private static POSIX posix;
    @BeforeClass
    public static void setUpClass() throws Exception {
        posix = POSIXFactory.getPOSIX(new DummyPOSIXHandler(), true);
    }


    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test public void filestat() throws Throwable {
        File f = File.createTempFile("stat", null);
        FileStat st = posix.stat(f.getAbsolutePath());
        f.delete();
        assertNotNull("posix.stat failed", st);
    }

    @Test public void structStatSize() throws Throwable {
        if (Platform.IS_SOLARIS) {
            if (Platform.IS_32_BIT) {
                assertEquals("struct size is wrong", 144, StructUtil.getSize(new SolarisHeapFileStat()));
            } else {
                assertEquals("struct size is wrong", 128, StructUtil.getSize(new Solaris64FileStat()));
            }
        }
    }
}