package osp.leobert.android.magicbox;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> MException </p>
 * <p><b>Description:</b> Exception of MagicBox </p>
 * Created by leobert on 2017/12/4.
 */

public final class MException extends RuntimeException {
    public MException() {
    }

    public MException(String message) {
        super(message);
    }

    public MException(String message, Throwable cause) {
        super(message, cause);
    }

    public MException(Throwable cause) {
        super(cause);
    }
}
