package ex.refactoringsecondeditionstudy.ch04;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Producer {

    private final Province province;
    private final String name;
    private int cost;
    private int production;

    public Producer(Province province, Producer aProducer) {
        this(province, aProducer.getName(), aProducer.getCost(), aProducer.getProduction());
    }

    @JsonCreator
    private Producer(
            @JsonProperty("name") final String name,
            @JsonProperty("cost") final int cost,
            @JsonProperty("production") final int production
    ) {
        this(null, name, cost, production);
    }

    private Producer(
            final Province province,
            final String name,
            final int cost,
            final int production
    ) {
        this.province = province;
        this.name = name;
        this.cost = cost;
        this.production = production;
    }

    public void setCost(final String cost) {
        this.cost = Integer.parseInt(cost);
    }


    public void setProduction(final String amountStr) {
        final int newProduction = Integer.parseInt(amountStr);
        this.province.setTotalProduction(this.province.getTotalProduction() + newProduction - this.production);
        this.production = newProduction;
    }
}
