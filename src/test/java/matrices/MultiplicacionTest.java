package matrices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

public class MultiplicacionTest {

    @ParameterizedTest
    @CsvSource({
            "1,2, 3,4",
            "3,3, 2,2",
            "2,3, 2,2",
    })
    void multiplicarDosMatricesShouldThrowDimensionesIncompatiblesExceptionWithWrongDimension(int filasA, int columnasA, int filasB, int columnasB) {

        // Arrange
        Matriz matrizA = new Matriz(filasA, columnasA, false);
        Matriz matrizB = new Matriz(filasB, columnasB, false);

        // Act & Assert
        Assertions.assertThrows(DimensionesIncompatibles.class, () -> {
            Matriz.multiplicarDosMatrices(matrizA, matrizB);
        }, "El tamaño del array proporcionado no coincide con las dimensiones de la matriz.");

    }

    @Test
    void multiplicarDosMatricesShouldOperateCorrectly() throws DimensionesIncompatibles {
        //Arrange
        String expectedMatrizString = "4904,11688,9876; 4268,9184,5360; 1182,3730,5369";
        Matriz expectedMatriz = createMatriz(new Dimension(3, 3), expectedMatrizString);

        var dimensionA = new Dimension(3, 2);
        var dataA = "8,46; 34,96; 65,48";
        Matriz matrizA = Matriz.traspuestaMatriz(createMatriz(dimensionA, dataA));

        var dimensionB = new Dimension(2, 3);
        var dataB = "84,16,73; 92,90,13";
        Matriz matrizB = Matriz.traspuestaMatriz(createMatriz(dimensionB, dataB));

        // Act
        Matriz matrizResultante = Matriz.multiplicarDosMatrices(matrizA, matrizB);

        // Assert
        Assertions.assertArrayEquals(expectedMatriz.getDatos(), matrizResultante.getDatos());
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
