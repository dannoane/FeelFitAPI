package api.domain;

import lombok.Getter;
import lombok.Setter;

public enum Activity {
    WALKING (1),
    RUNNING (2),
    BIKING (3)
    ;

    @Getter @Setter private int code;

    Activity(int code) {
        this.code = code;
    }
}
