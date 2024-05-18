/**
 * 
 */
/**
 * 
 */
module project1 {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
//    opens project1 to javafx.fxml;
    exports view;
    exports controller;
    opens view;
    opens controller;
    opens application.model;
}
