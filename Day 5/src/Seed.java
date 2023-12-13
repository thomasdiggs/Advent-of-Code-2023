public class Seed {

    long seed;
    long soil;
    long fertilizer;
    long water;
    long light;
    long temperature;
    long humidity;
    long location;

    public Seed(long seed) {
        this.seed = seed;
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

    @Override
    public String toString() {
        return "Seed: " + this.seed + ", Soil: " + this.soil + ", Fertilizer: " + this.fertilizer + ", Water: " + this.water + ", Light: " + this.light + ", Temperature: " + this.temperature + ", Humidity: " + this.humidity + ", Location: " + this.location;
    }

}