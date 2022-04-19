module br.femass.edu.prova_prog3_n1_julio {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.dataformat.xml;
    requires xstream;

    opens br.femass.edu.prova_prog3_n1_julio.dao to xstream;
    opens br.femass.edu.prova_prog3_n1_julio.Model to xstream;


    opens br.femass.edu.prova_prog3_n1_julio to javafx.fxml;
    exports br.femass.edu.prova_prog3_n1_julio;
    exports br.femass.edu.prova_prog3_n1_julio.gui;
    opens br.femass.edu.prova_prog3_n1_julio.gui to javafx.fxml;
}