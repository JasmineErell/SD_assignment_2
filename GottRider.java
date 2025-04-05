public class GottRider implements Comparable<GottRider> {

    private String name;
    private Long previousRides;
    private Double averageReview;

    public GottRider(String name, Long previousRides, Double averageReviewGiven) {
        this.name = name;
        this.previousRides = (previousRides != null) ? previousRides : 0L;
        this.averageReview = (averageReviewGiven != null) ? averageReviewGiven : 0.0;
    }

    public void rideCompleted(Long starsGiven) {
        double starsBeforeRide = this.averageReview * this.previousRides;
        starsBeforeRide += starsGiven;
        this.previousRides++;
        this.averageReview = starsBeforeRide / this.previousRides;
    }

    public String getName() {return this.name;}

    public Double getAverageReviewGiven() {return this.averageReview;}

    @Override
    public String toString() {
        return "GottRider{" +
                "name='" + name + '\'' +
                ", previousRides=" + previousRides +
                ", averageReview=" + averageReview +
                '}';
    }

    @Override
    public int compareTo(GottRider otherRider) {
        // Compare average reviews. Lower means "less than."
        return Double.compare(this.averageReview, otherRider.averageReview);
    }
}
