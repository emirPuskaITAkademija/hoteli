module com.hotelijerstvo.hoteli {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.sql.rowset;
    requires org.controlsfx.controls;

    opens com.hotelijerstvo.hoteli to javafx.fxml;
    opens com.hotelijerstvo.hoteli.gui.components to javafx.base;
    opens com.hotelijerstvo.hoteli.country.town.address to org.hibernate.orm.core;
    opens com.hotelijerstvo.hoteli.country.town to org.hibernate.orm.core;
    opens com.hotelijerstvo.hoteli.country to org.hibernate.orm.core;
    opens com.hotelijerstvo.hoteli.user to org.hibernate.orm.core, javafx.base;
    opens com.hotelijerstvo.hoteli.user.privilege to org.hibernate.orm.core;
    opens com.hotelijerstvo.hoteli.reservation to org.hibernate.orm.core;
    opens com.hotelijerstvo.hoteli.reservation.guest to org.hibernate.orm.core;
    opens com.hotelijerstvo.hoteli.reservation.room to org.hibernate.orm.core, javafx.base;

    exports com.hotelijerstvo.hoteli;
    opens com.hotelijerstvo.hoteli.user.service to org.hibernate.orm.core;
}