package osp.leobert.android.magicboxsample.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample.test </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> TestObject </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

public class TestObject {
    List<String> strs = new ArrayList<>();

    boolean bool = true;

    Name name = null;



    public List<String> getStrs() {
        return strs;
    }

    public void setStrs(List<String> strs) {
        this.strs = strs;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public static final class Name {
        String first;
        String second;

        public Name(String first, String second) {
            this.first = first;
            this.second = second;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }
    }
}
