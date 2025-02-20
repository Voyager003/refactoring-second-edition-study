package ex.refactoringsecondeditionstudy.ch04;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter @Setter
public class Province {
    private final String name;
    private final List<Producer> producers;
    private int totalProduction;
    private int demand;
    private int price;

    public Province(final ProvinceData doc) {
        this(doc.name(), doc.producers(), 0, doc.demand(), doc.price());
    }

    private Province(final String name, final List<Producer> producers, final int totalProduction, final int demand, final int price) {
        this.name = name;
        this.producers = new ArrayList<>();
        this.totalProduction = totalProduction;
        this.demand = demand;
        this.price = price;
        producers.forEach(d -> this.addProducer(new Producer(this, d)));
    }


    public void addProducer(Producer producer) {
        this.producers.add(producer);
        this.totalProduction += producer.getProduction();
    }

    public int getShortfall() {
        return this.demand - this.totalProduction;
    }

    public int getProfit() {
        return this.demandValue() - this.demandCost();
    }

    private int demandValue() {
        return this.satisfiedDemand() * this.price;
    }

    private int satisfiedDemand() {
        return Math.min(this.demand, this.totalProduction);
    }

    private int demandCost() {
        int remainingDemand = this.demand;
        int result = 0;
        final List<Producer> sortedProducers = producers.stream()
                .sorted((a, b) -> a.getCost() - b.getCost())
                .toList();

        for (var p : sortedProducers) {
            final int contribution = Math.min(remainingDemand, p.getProduction());
            remainingDemand -= contribution;
            result += contribution * p.getCost();
        }

        return result;
    }

    public List<Producer> getProducers() {
        return Collections.unmodifiableList(producers);
    }


    public void setDemand(final String demand) {
        this.demand = Integer.parseInt(demand);
    }


    public void setPrice(final String price) {
        this.price = Integer.parseInt(price);
    }
}
