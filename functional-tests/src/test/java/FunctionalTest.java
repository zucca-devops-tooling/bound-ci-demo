import dev.zucca_ops.ServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionalTest {

    @Test
    void testHello() {
        ServiceImpl service = new ServiceImpl();
        assertEquals("Hello from ServiceImpl!", service.hello());
    }
}