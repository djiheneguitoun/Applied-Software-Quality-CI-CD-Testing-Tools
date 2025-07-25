package steps;
import com.exception.NoSquareException;
import com.model.Matrix;
import com.service.MatrixMathematics;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CalculateDeterminant {
    double det;
    Matrix matrix;
    @Given("I have the following squared matrix")
    public void iHaveTheFollowingSquaredMatrix(DataTable table){
        List<List<Double>> list = table.asLists(Double.class);
        double [][] tab = list.stream()
                .map(l -> l.stream()
                        .mapToDouble(Double::doubleValue)
                        .toArray()
                )
                .toArray(double[][]::new);
        matrix = new Matrix(tab);
    }

    @When("I calculate the determinant")
    public void iCalculateTheDeterminant() {
        try{
            det = MatrixMathematics.determinant(matrix);
        }catch(NoSquareException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Then("I should have the result {int}")
    public void iShouldHaveTheResult(int result) {
        assertEquals(result,det);
    }
}
