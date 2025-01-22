package ex.refactoringsecondeditionstudy;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Plays {
    private Map<String, Play> playMap = new HashMap<>();
}
