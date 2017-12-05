package osp.leobert.android.magicbox.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import osp.leobert.android.magicbox.BoxIOComponent;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.annotations </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> BoxIO </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface BoxIO {
    /**
     * @return a class implement {@link BoxIOComponent} with a public empty constructor
     */
    Class<? extends BoxIOComponent> component() default BoxIOComponent.DefaultComponent.class;
}
