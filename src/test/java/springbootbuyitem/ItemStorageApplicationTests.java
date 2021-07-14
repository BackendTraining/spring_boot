package springbootbuyitem;

import com.training.springbootbuyitem.BuyItemApplication;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BuyItemApplication.class)
class ItemStorageApplicationTests {

    @Test
    void contextLoads() {
        Assert.assertTrue(true);
    }

}
