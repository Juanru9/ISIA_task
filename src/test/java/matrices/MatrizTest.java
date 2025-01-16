package matrices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;

public class MatrizTest {

    public MatrizTest() {
    }

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

    @Test
    void traspuestaMatrizShouldOperateCorrectly() {
        //Arrange
        String expectedMatrizString = "1,4,7; 2,5,8; 3,6,9";
        Matriz expectedMatriz = createMatriz(new Dimension(3, 3), expectedMatrizString);

        var dimension = new Dimension(3, 3);
        var data = "1,2,3; 4,5,6; 7,8,9";
        Matriz matriz = createMatriz(dimension, data);

        // Act
        Matriz matrizResultante = Matriz.traspuestaMatriz(matriz);

        // Assert
        Assertions.assertArrayEquals(expectedMatriz.getDatos(), matrizResultante.getDatos());
    }

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

