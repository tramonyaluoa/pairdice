import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DieTest {
    //expected exception test of one sided die
    final Die oneSidedDie = new Die(0);
    @Test
    void diceSides(){
        assertThrows(Exception.class, oneSidedDie::roll);
    }

    //expected exception test for a die with non-integer types
    final Die probFrac = new Die(4,1,2.5,1.3,1);
    @Test
    void diceProbabilities(){
        assertThrows(Exception.class, probFrac::roll);
    }

    //expected exception test for die with probabilities sum less than one
    final Die addProb = new Die(4,0,0,0,0);
    @Test
    void probabilitiesSum(){
        assertThrows(Exception.class, addProb::roll);
    }

    //expected exception test for a die with negative probabilities
    final Die probableVal = new Die(4,1,1,-1,-1);
    @Test
    void negativeProbabilities() {
        assertThrows(Exception.class, probableVal::roll);
    }

}