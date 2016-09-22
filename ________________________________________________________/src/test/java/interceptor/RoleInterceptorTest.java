package interceptor;

import com.d1l.interceptor.RoleInterceptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RoleInterceptorTest {
    private RoleInterceptor interceptor;
    private String listRole;

    @Before
    public void setUp() throws Exception {
        interceptor = new RoleInterceptor();
        listRole = "Admin,Customer";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void handleRejection() throws Exception {
        assertEquals("invalidAdminAccess",interceptor.handleRejection(null,null));
    }

    @Test
    public void stringToList() throws Exception {
        assertNotEquals(Collections.EMPTY_LIST,interceptor.stringToList(listRole));
    }

}