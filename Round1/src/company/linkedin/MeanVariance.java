package company.linkedin;

import java.util.Iterator;
/**
 * mean = sum(elements)／ len(elements)
variance = sum([elem^2 for elem in elements]) / len(elements) - mean^2  

 */
public class MeanVariance {

	public static void main(String[] args) {

	}

	double[] getMeanAndVariance(Iterator<Double> it) {
		int count = 0;
		double sum = 0;
		double squareSum = 0;
		double mean = 0;
		double variance = 0;
		
		while (it.hasNext()) {
			double now = it.next();
			count++;
			
			sum += now;
			squareSum += now * now;
			
			mean = sum / count;
			variance = squareSum / count - mean * mean;
		}
		
		return new double[]{mean, variance};
	}
	
	// What if sum is too big, need to calculate dynamically
	double[] getMeanAndVarianceBig(Iterator<Double> it) {
		int count = 0;
		double mean = 0;
		double variance = 0;
		
		if (it.hasNext()) {
			double now = it.next();
			count++;
			
			mean = now;
			variance = now * now / count - mean * mean;
		}
		
		while (it.hasNext()) {
			double now = it.next();
			
			double newMean = (mean * count + now) / (count + 1);
			double newVariance = (((variance + mean * mean) * count) + now * now) / (count + 1) - newMean * newMean;
					
			count++;
			mean = newMean;
			variance = newVariance;
		}
		
		return new double[]{mean, variance};
	}
}
