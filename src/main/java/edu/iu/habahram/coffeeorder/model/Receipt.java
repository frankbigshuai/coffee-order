package edu.iu.habahram.coffeeorder.model;

public record Receipt(int id,float cost, String description) {
        public String toLine() {
            return String.format("%d,%.2f,%s", id, cost, description);
        }
    }

