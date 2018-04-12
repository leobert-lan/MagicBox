package osp.leobert.android.magicboxsample.test.nested;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;

@AutoValue
public abstract class Response{

	@SerializedName("name")
	public abstract String name();

    public static Response create(String name) {
        return builder()
                .name(name)
                .build();
    }

    public static TypeAdapter<Response> typeAdapter(Gson gson) {
		return new AutoValue_Response.GsonTypeAdapter(gson);
	}

    public static Builder builder() {
        return new $AutoValue_Response.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);

        public abstract Response build();
    }
}