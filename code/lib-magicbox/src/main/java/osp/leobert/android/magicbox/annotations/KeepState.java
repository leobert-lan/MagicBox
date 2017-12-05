package osp.leobert.android.magicbox.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import osp.leobert.android.magicbox.BoxIOComponent;
import osp.leobert.android.magicbox.type.Type;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.annotations </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> KeepState </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/3.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface KeepState {

    Type type() default Type.Infer;

    /**
     * @return a class implement {@link BoxIOComponent} with a public empty constructor
     */
    Class<? extends BoxIOComponent> io() default BoxIOComponent.DefaultComponent.class;


//    BoxIO io() default @BoxIO();
}
