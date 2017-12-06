package osp.leobert.android.magicbox.type;

import java.lang.reflect.Field;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SupposeType </p>
 * <p><b>Description:</b> check if one class is the predict one </p>
 * Created by leobert on 2017/12/5.
 */

public interface SupposeType extends ICheckable{
    boolean check(Field field);
}
