package persistence;

import org.json.JSONObject;

public interface Writable {

    // EFFECT: Converts object to JSONObject
    JSONObject toJson();
}
