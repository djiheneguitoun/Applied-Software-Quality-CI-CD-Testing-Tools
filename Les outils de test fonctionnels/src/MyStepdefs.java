import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.exception.NoSquareException;
import com.model.Matrix;
import com.service.MatrixMathematics;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
    Matrix matrix;
    private double determinant;

    @Given("une matrice de taille")
    public void uneMatriceDeTaille(DataTable table) {
        double[][] data=new double[3][3];
        for (int i = 0; i < 3; i++) {
            data[i] = table.row(i).stream().mapToDouble(Integer::parseInt).toArray();
        }
        matrix=new Matrix(data);
    }

    @When("je calcule le déterminant de la matrice")
    public void jeCalculeLeDéterminantDeLaMatrice() {
        try{
            determinant = MatrixMathematics.determinant(matrix);
        }catch(NoSquareException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Then("le résultat devrait être égal à {int}")
    public void leRésultatDevraitÊtreÉgalÀ(double result) {
        assertEquals(result,determinant);
    }

}
