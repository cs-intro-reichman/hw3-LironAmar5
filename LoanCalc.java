// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		
        
		
		
		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double sum = loan;
		
		for (int i = 1; i <= n; i++) {
			sum = (sum - payment) * (1 + (rate / 100));
			
		}
		
		return sum;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double g = loan / n;
		iterationCounter = 0;
		while ((endBalance(loan, rate, n, g)) >= epsilon){
        g = g + epsilon;
		iterationCounter++;
		}
		return g;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;

    // 1. Find an interval [L, H] that brackets the root:
    //    endBalance(L) > 0 and endBalance(H) < 0.
    double L = 0.0;          // payment = 0 → definitely too small ⇒ balance > 0
    double H = loan;         // start with H = loan, might be enough
    double fL = endBalance(loan, rate, n, L);
    double fH = endBalance(loan, rate, n, H);

    // If H is still too small (fH > 0), grow H until f(H) < 0.
    while (fH > 0) {
        H *= 2.0;            // increase upper bound
        fH = endBalance(loan, rate, n, H);
    }

    // 2. Bisection loop: shrink [L, H] until it’s small enough.
    double mid = 0.0;
    while (H - L > epsilon) {
        iterationCounter++;
        mid = (L + H) / 2.0;

        double fMid = endBalance(loan, rate, n, mid);

        // If fMid has the same sign as fL, move L up to mid
        if (fMid * fL > 0) {
            L = mid;
            fL = fMid;
        } else {
            // Otherwise the root is between L and mid
            H = mid;
            fH = fMid;
        }
    }

    // 3. Return any point in [L, H]; the midpoint is standard
    return (L + H) / 2.0;
    }
}