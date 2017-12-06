package osp.leobert.android.magicbox;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.type.GenericUtils;
import osp.leobert.android.magicbox.type.TypeInferUtils;

import static org.junit.Assert.*;

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

    private static class Temp {
        @KeepState
        List<String> ls = new ArrayList<>();

        @KeepState
        String s;

        @KeepState
        Map<String,Object> map;
    }

    @Test
    public void temp() throws Exception {
        Field[] fields = Temp.class.getDeclaredFields();
        List<Field> todos = new ArrayList<>();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof KeepState) {
                    todos.add(field);
                    break;
                }
            }
        }

        for (Field field : todos) {
            inferParamterizedType(field);
        }
    }

    private void inferParamterizedType(Field field) {
        String fieldName = field.getName();

        Type type = field.getType();
        Type genericType = field.getGenericType();

        if (genericType instanceof ParameterizedType) {
            int size = ((ParameterizedType) genericType).getActualTypeArguments().length;
            for (int i = 0;i<size;i++) {
                Class clz = GenericUtils.getGenericClass((ParameterizedType) genericType,i);
                System.out.println(clz);
            }
        }

//        getClass(type,0);

        System.out.println("fieldName:" + fieldName + "   ; type:" + type +
                "\n+genericType:" + genericType);
//        Class clz = getClass(genericType,1);
//        System.out.println(clz);
        System.out.println();
    }


//    @Test
//    public void genStrategy() throws Exception {
//        List<StateField> stateFields = secy.genStrategy(testClz.getClass());
//        System.out.print("end");
//        Assert.assertTrue(true);
//    }

}