package com.example.taxraid.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxSlab {
    private double min, max, rate;
    public TaxSlab(double min, double max, double rate) {
        this.min = min;
        this.max = max;
        this.rate = rate;
    }

    public boolean isNotMaxValue() {
        return this.max != Double.MAX_VALUE;
    }

    public boolean isMaxValue() {
        return this.max == Double.MAX_VALUE;
    }
}
