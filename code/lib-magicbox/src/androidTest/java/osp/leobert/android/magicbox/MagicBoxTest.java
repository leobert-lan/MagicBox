package osp.leobert.android.magicbox;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.type.Type;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> MagicBoxTest </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */
@RunWith(AndroidJUnit4.class)
public class MagicBoxTest {
    private static class TestClz {

        @KeepState
        boolean testBool = true;

        @KeepState
        Boolean testNullBoxBool = true;

        byte testByte1;

        Byte testByte2;


        @KeepState
        String testString = "this is a test string";

        @KeepState
        String testNullString = null;

        @KeepState(type = Type.BooleanArray)
        boolean[] booleans1;

        @KeepState
        Boolean[] booleans2;

        public void foo() {
            testBool = false;
            testNullBoxBool = null;
            testByte1 = 'a';

            booleans1 = new boolean[2];
            booleans1[0] = true;
            booleans1[1] = false;

            booleans2 = new Boolean[2];
            booleans2[0] = null;
            booleans2[1] = true;

        }
    }

    MagicBox magicBox;
    TestClz test1;
    TestClz test2;
    Bundle bundle;

    @Before
    public void setUp() throws Exception {
        magicBox = MagicBox.getInstance();
        bundle = new Bundle();
        test1 = new TestClz();
        test1.foo();

        test2 = new TestClz();

        saveInstanceState();
    }

    //    @Test
    public void saveInstanceState() throws Exception {
        magicBox.saveInstanceState(test1, bundle);
    }

    @Test
    public void restoreInstanceState() throws Exception {
        magicBox.restoreInstanceState(test2, bundle);
        System.out.println(test2);
    }


}