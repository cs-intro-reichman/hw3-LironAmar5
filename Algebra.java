
import com.sun.jdi.AbsentInformationException;

// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		int sum = x1;
        if (x2 == 0){
			 return sum;}
		else if( x2 > 0 ){
        while(x2 > 0){
			sum ++;
			x2--;
		}
		return sum;
		}
		else{
			while (x2 < 0){
				sum --;
				x2 ++;
			}
			return sum;
		}
		
		
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		int sum = x1;
		if ( x2 == 0 ){ return sum; }
		else if ( x2 > 0 ){
          while ( x2 > 0){
			sum--;
			x2--;
		  }
		  return sum;
		}
		else {
			while (x2 < 0){
			sum ++;
			x2++;
		}
	    return sum;
	    }
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
	    int result = x1;

		if ((x2 == 0) || ( result == 0)){ return 0;}
		else if ( (x2 > 0) && ( x1 > 0) ){
			for (int i = 1; (i < x2) && (x2 != 1) ; i++) {
				result = plus( result, x1 );
			}
			return result;
		}
		else if ((x2 > 0) && (x1 < 0)){
			for (int i = 1; (i < x2) && (x2 != 1); i++) {
				result = plus( result, x1);
			}
			return result;
		}
		else if ((x2 < 0) && ( x1 < 0)) {
			result = minus( 0, x1 );
            for (int i = (-1); (i > x2) && (x2 != (-1)); i--) {
				result = plus( result, minus(0, x1));
			}
			return result;

		}
		else {
         result = x2;
		 for (int i = 1; (i < x1) && (x1 != 1); i++) {
				result = plus( result, x2);
			}
         return result;
		}

		
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int power = x;
		if (x == 0){
			return 0;
		}
		else if ( n == 0){
			return 1;
		}
		else{
			for (int i = 1; i < n; i++) {
				power = times( power, x);
			}
			return power;
		}
		
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		if (x2 == 0){
			throw new ArithmeticException("divison by zero");
		}
		boolean sign = (x1 < 0) ^ (x2 < 0);
		x1 = Math.abs(x1);
		x2 = Math.abs(x2);

		int count = 0;

		while (x1 >= x2){
			x1 = x1 - x2;
			count++;
		}

		return sign ? -count : count;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int modulo = x1;
		if (x2 == 0){return 0;}
		else if ((x2 > 0) && (x1 > 0)){
			if( x2 > x1){ return modulo;}
			else{
				while(modulo >= x2){
					modulo = minus(modulo,x2);
				}
				return modulo;
			}
		}
		else if(( x2 < 0 ) && ( x1 < 0 )){
            if(x2 < x1){ return modulo;}
			while(modulo <= x2){
				modulo = minus(modulo, x2);
            }
			return modulo;
		}
        else if ((x1 > 0) && ( x2 < 0)) {
			x2 = minus(0,x2);
			if(x2 > x1){return modulo;}
			else{
			while (modulo >= x2){
				modulo = minus(modulo,x2);
			}
			return modulo;
		}
		}
		else {
			modulo = minus(0,x1);
			if(x2 >= modulo){return minus(0, modulo);}
			else{
			while(modulo >= x2 ){
				modulo = minus(modulo,x2);
			}
			return minus(0,modulo);
		}
		}
		
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		if(x == 0 ){return 0;}
		else if (x < 0) { return -1;}
		else{
     int temp = 1;
	 while (times(temp,temp) < x){
		temp++;
	 }
	
	return temp;
    }
    }
}