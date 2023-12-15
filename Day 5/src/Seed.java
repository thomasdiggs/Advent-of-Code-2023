public class Seed {

    long seed;
    long soil = 0;
    long fertilizer = 0;
    long water = 0;
    long light = 0;
    long temperature = 0;
    long humidity = 0;
    long location = 0;
    long range = 0;

    public Seed(long seed, long range) {
        this.seed = seed;
        this.range = range;
    }

    public void setSoil(long soil) {
        this.soil = soil;
    }

    public void setFertilizer(long fertilizer) {
        this.fertilizer = fertilizer;
    }

    public void setWater(long water) {
        this.water = water;
    }

    public void setLight(long light) {
        this.light = light;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public void setLocation(long location) {
        this.location = location;
    }

    public void setRange(long range) { this.range = range; }

    public long getSeed() {
        return this.seed;
    }

    public long getSoil() {
        return this.soil;
    }

    public long getFertilizer() {
        return this.fertilizer;
    }

    public long getWater() {
        return this.water;
    }

    public long getLight() {
        return this.light;
    }

    public long getTemperature() {
        return this.temperature;
    }

    public long getHumidity() {
        return this.humidity;
    }

    public long getLocation() { return this.location; }

    public long getRange() { return this.range; }

    @Override
    public String toString() {
        return "Seed: " + this.seed + ", Soil: " + this.soil + ", Fertilizer: " + this.fertilizer + ", Water: " + this.water + ", Light: " + this.light + ", Temperature: " + this.temperature + ", Humidity: " + this.humidity + ", Location: " + this.location;
    }

    public void callSetMethod(String setMethod, long value) {
        switch (setMethod) {
            case "setSoil" -> this.setSoil(value);
            case "setFertilizer" -> this.setFertilizer(value);
            case "setWater" -> this.setWater(value);
            case "setLight" -> this.setLight(value);
            case "setTemperature" -> this.setTemperature(value);
            case "setHumidity" -> this.setHumidity(value);
            case "setLocation" -> this.setLocation(value);
            default -> System.out.println("Invalid set method");
        };
    }

    public Long callGetMethod(String getMethod) {
        return switch (getMethod) {
            case "getSeed" -> this.getSeed();
            case "getSoil" -> this.getSoil();
            case "getFertilizer" -> this.getFertilizer();
            case "getWater" -> this.getWater();
            case "getLight" -> this.getLight();
            case "getTemperature" -> this.getTemperature();
            case "getHumidity" -> this.getHumidity();
            case "getLocation" -> this.getLocation();
            default -> null;
        };
    }
}