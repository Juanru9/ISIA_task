import matrices.MultiplicacionTest;
import matrices.OperacionCompuestaTest;
import matrices.TrasponerTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        MultiplicacionTest.class,
        OperacionCompuestaTest.class,
        TrasponerTest.class
})
public class AllTests {
}