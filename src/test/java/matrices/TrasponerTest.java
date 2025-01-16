package matrices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TrasponerTest {

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
