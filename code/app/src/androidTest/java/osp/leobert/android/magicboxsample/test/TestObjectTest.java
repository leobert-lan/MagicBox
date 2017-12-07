package osp.leobert.android.magicboxsample.test;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import osp.leobert.android.magicbox.MagicBox;
import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.type.Type;
import osp.leobert.android.magicboxsample.GsonBoxIOComponent;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample.test </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> TestObjectTest </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */
@RunWith(AndroidJUnit4.class)
public class TestObjectTest {


    private static class Wrapper {
        @KeepState(type = Type.Object,io = GsonBoxIOComponent.class)
        TestObject testObject;

        public void foo() {
            testObject = new TestObject();
            testObject.setStrs(Arrays.asList("a", "b"));
            testObject.setName(new TestObject.Name("Lily", "Allen"));
        }
    }

    MagicBox magicBox;

    Wrapper wrapper1;

    Wrapper wrapper2;

    Bundle bundle;

    @Before
    public void setUp() throws Exception {
        magicBox = MagicBox.getInstance();
        MagicBox.setLogEnable(true);
        bundle = new Bundle();

        wrapper1 = new Wrapper();
        wrapper1.foo();

        magicBox.saveInstanceState(wrapper1,bundle);

    }

    @Test
    public void testRestore() {
        wrapper2 = new Wrapper();
        magicBox.restoreInstanceState(wrapper2,bundle);
        System.out.println(wrapper2);
        Assert.assertTrue(true);


    }

}