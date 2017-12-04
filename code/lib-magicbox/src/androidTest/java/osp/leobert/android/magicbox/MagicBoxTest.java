package osp.leobert.android.magicbox;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import osp.leobert.android.magicbox.annotations.KeepState;

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
        Boolean testNullBoxBool;

        public boolean isTestBool() {
            return testBool;
        }

        public void setTestBool(boolean testBool) {
            this.testBool = testBool;
        }

        public Boolean getTestNullBoxBool() {
            return testNullBoxBool;
        }

        public void setTestNullBoxBool(Boolean testNullBoxBool) {
            this.testNullBoxBool = testNullBoxBool;
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
        test1.setTestBool(false);
        test1.setTestNullBoxBool(null);

        test2 = new TestClz();

        saveInstanceState();
    }

    //    @Test
    public void saveInstanceState() throws Exception {
        magicBox.saveInstanceState(test1,bundle);
    }

    @Test
    public void restoreInstanceState() throws Exception {
        magicBox.restoreInstanceState(test2,bundle);
        System.out.println(test2);
    }


}