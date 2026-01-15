module ec.edu.espoch.analisispendiente
{
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espoch.analisispendiente.controller.interfaces to javafx.fxml;
    opens ec.edu.espoch.analisispendiente to javafx.fxml;

    exports ec.edu.espoch.analisispendiente;
}

