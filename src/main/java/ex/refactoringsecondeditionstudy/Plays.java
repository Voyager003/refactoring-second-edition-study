package ex.refactoringsecondeditionstudy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class Plays {
    private final Map<String, Play> playMap;

    public Play getPlayById(String id) {
        return playMap.get(id);
    }
}
