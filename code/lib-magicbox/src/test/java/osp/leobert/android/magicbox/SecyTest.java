package osp.leobert.android.magicbox;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SecyTest </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */
public class SecyTest {

    private static class TestClz {
        @KeepState
        boolean testBool = true;

        @KeepState
        Boolean testNullBoxBool;

        @KeepState
        String string = "";

        @KeepState
        boolean[] booleans = new boolean[2];

        @KeepState
        Boolean[] booleans2 = new Boolean[2]; //cannot check

    }

    Secy secy;
    TestClz testClz;

    @Before
    public void setUp() throws Exception {
        secy = Secy.getInstance();
        testClz = new TestClz();
    }

    @Test
    public void genStrategy() throws Exception {
        List<StateField> stateFields = secy.genStrategy(testClz.getClass());
        System.out.print("end");
        Assert.assertTrue(true);
    }

}