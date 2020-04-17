import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.in;
import static java.lang.System.out;

public class Die {
    int value;
    public static void main (String[] args) {
        Die die6 = new Die (6, 1,1,1,1,1,2);
        try {
            die6.roll ( );
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        out.println ("Dice value: " + die6.value);

        // changing probabilities of die6
        die6.setProbabilities (new Number[]{3, 1, 2 , 5, 1, 1});
        try {
            die6.roll ( );
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        out.println ("Weighted Dice value: " + die6.value);

        // makes a 20sided die using the dice factory class
        Die die20 = DiceFactory.makeDice (20);
        try {
            die20.roll ( );
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        out.println ("Factory Dice value: " + die20.value);

        // get user input for the dice rolls
        out.println ( );
        out.print ("Type 'Roll' to roll again: \n");
        Scanner input = new Scanner(in);
        String rollagain = input.nextLine();
        out.println ("-------------------------------------------------------");
        main(args); //re run
    }

    //initialize the variables
    final int sides;
    public Number[] probabilities;

    public void setProbabilities(Number[] newProbs){
        probabilities = newProbs;
    }

    //Dice probability calculations constructor
    Die (int diceSides, Number... diceProbabilities) {
        this.sides = diceSides;
        this.probabilities = diceProbabilities;
    }

    // sides must be not be less than or equal to 0
    public void roll () throws Exception{
        if (sides <= 0){
            throw new Exception("sides has to be an integer greater than one");
        }

/*if the length of the probability array is equal to Sides
  and the sum of the probabilities = 0 while i is less than the length
  if value of intValue is greater than number0
  or if the remainder of intValue and doubleValue is greater than number0
 then calculate addProbability add value of intValue
 otherwise throw exceptions if addProbability is equal to number0*/
        if (sides == probabilities.length) {
            int addProb = 0;
            {
                int i = 0, probabilityLength = probabilities.length;
                while (i < probabilityLength) {
                    Number number = probabilities[i];
                    if (number.intValue ( ) == 0 || number.doubleValue ( ) % number.intValue ( ) == 0) {
                        addProb = addProb + number.intValue ( );
                    } else {
                        throw new Exception ("only integer values allowed");
                    }
                    if (addProb == 0) throw new Exception ("probability sum must be greater than 0");
                    i++;
                }
            }

/* probFrac is Probabilty fraction
   addProb is sum of the probabilities

   if i is greater than 0 add the calculation
   currentValue/sum of probabilities + the 0index element -1  to the list
   otherwise add the calculation of add the calculation of currentValue/sum of probabilities
   and then increment i while i is less than length of probabilities array*/
            ArrayList<Double> probFraction = new ArrayList<>();
            int i=0;
            if (i < probabilities.length)
              do {
                int probableValue = probabilities[i].intValue ( );
                if (probableValue < 0) throw new Exception ("negative probabilities not allowed");
                 if (i > 0) {
                     probFraction.add (((double) probableValue) / addProb + probFraction.get (i - 1));
                 } else {
                    probFraction.add (((double) probableValue) / addProb);
                }
                i++;
            } while (i < probabilities.length);

/* when x=1 while x <= sides, add x
   to the randomvalues arraylist and increment x
   if the generated random number
   is less than the probabilty fraction
   get the values added to the randomvalues arraylist
   when x =0 and less than the size of the probFrac arraylist */
            double randoms = Math.random();
            ArrayList<Integer> randomValues = new ArrayList<>();
            {
                int x = 1;
                while (x <= sides) {
                    randomValues.add (x);
                    x++;
                }
            }
            int x = 0;
            while (x < probFraction.size()) {
                if (randoms < probFraction.get (x)) {
                    value = randomValues.get (x);
                }
                x++;
            }
        }
        // Factory dice value is the randomly generated numbers * the value on the sides +1
     value = (int) (Math.random ( ) * sides + 1);
    }
}