package ex.refactoringsecondeditionstudy.ch01;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Invoice {
    private String customer;
    List<Performance> performances;
}
