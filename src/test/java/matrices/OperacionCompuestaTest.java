package matrices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;

public class OperacionCompuestaTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "1,4,7; 2,5,8; 3,6,9",
            "5,6,6; 4,4,2; 4,1,0"
    })
    void traspuestaMatrizOfTraspuestaMatrizIsTheOriginalMatriz(String matrizData) {
        //Arrange
        Matriz originalMatriz = createMatriz(new Dimension(3, 3), matrizData);

        // Act
        Matriz matrizResultante = Matriz.traspuestaMatriz(Matriz.traspuestaMatriz(originalMatriz));

        // Assert
        Assertions.assertArrayEquals(originalMatriz.getDatos(), matrizResultante.getDatos());
    }

    @Test
    void multiplicarDosMatricesTraspuestasShouldBeEqualToMultiplicarLaTraspuestaDeDosMatrices() throws DimensionesIncompatibles {
        //Arrange
        var dimensionA = new Dimension(3, 3);
        var dataA = "8,46,99; 34,96,77; 65,48,33";
        Matriz matrizA = Matriz.traspuestaMatriz(createMatriz(dimensionA, dataA));

        var dimensionB = new Dimension(3, 3);
        var dataB = "84,16,73; 92,90,13; 2,3,4";
        Matriz matrizB = Matriz.traspuestaMatriz(createMatriz(dimensionB, dataB));

        // Act
        Matriz matrizResultante1 = Matriz.multiplicarDosMatrices(Matriz.traspuestaMatriz(matrizA), Matriz.traspuestaMatriz(matrizB));
        Matriz matrizResultante2 = Matriz.traspuestaMatriz(Matriz.multiplicarDosMatrices(matrizB, matrizA));

        // Assert
        Assertions.assertArrayEquals(matrizResultante1.getDatos(), matrizResultante2.getDatos());
    }

    private Matriz createMatriz(Dimension dimension, String data) {
        Matriz matriz = new Matriz(dimension.height, dimension.width, false);
        int[][] datos = new int[dimension.width][dimension.height];

        // Divide la cadena en filas eliminando espacios en blanco
        String[] rows = data.replaceAll("\\s+", "").split(";");
        for (int i = 0; i < rows.length; i++) {
            // Divide cada fila en columnas
            String[] columns = rows[i].split(",");
            for (int j = 0; j < columns.length; j++) {
                datos[i][j] = Integer.parseInt(columns[j]);
            }
        }

        // Asigna los datos a la matriz
        matriz.setDatos(datos);
        return matriz;
    }
}
