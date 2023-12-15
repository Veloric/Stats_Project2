/**
 * A seperate class containing just the new stuff added to the StatsLibrary for easy consumption.
 * @author Kyle Geddes
 */

public class UpdatedStatsLibrary {
 // Chapter 4 & 5: Skipping the calc stuff, you're really only left with the Uniform Distribution
   /**
    * Computers a uniform distribution along a bound
    * @param lower_bound - Lower bound of the distribution
    * @param upper_bound - Upper bound of the distribution
    * @return
    */
   public double uniformDist(int lower_bound, int upper_bound){
    double ans = 0;
    int top = upper_bound - lower_bound;
    ans = (double)top / (double)upper_bound;
    return ans;
   }
}
