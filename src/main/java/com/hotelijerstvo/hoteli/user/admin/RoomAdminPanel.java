package com.hotelijerstvo.hoteli.user.admin;

import com.hotelijerstvo.hoteli.reservation.room.Room;
import com.hotelijerstvo.hoteli.reservation.room.service.RoomServiceLocal;
import com.hotelijerstvo.hoteli.user.User;
import com.hotelijerstvo.hoteli.user.service.UserServiceFactory;
import com.hotelijerstvo.hoteli.user.service.UserServiceLocal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.converter.IntegerStringConverter;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class RoomAdminPanel extends VBox {
    private final Label titleLabel = new Label("Administracija soba");
    private final TableView<Room> roomTableView = new TableView<>();

    private final TextField roomCodeTextField = new TextField();
    private final TextField numberOfBedsTextField = new TextField();
    private final TextField priceTextField = new TextField();
    private final Button addButton = new Button("Dodaj sobu");

    private final Button editButton = new Button("Ažuriraj sobu");
    private final Button deleteButton = new Button("Obriši sobu");

    private ObservableList<Room> observableRoomList;

    public RoomAdminPanel(){
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10));
        bindTableWithData();
        configureTableView();
        getChildren().addAll(titleLabel, roomTableView, getForm());
    }

    private void bindTableWithData() {
        observableRoomList = FXCollections.observableList(RoomServiceLocal.ROOM_SERVICE.findAll());
        roomTableView.setItems(observableRoomList);
    }

    private void configureTableView() {
        roomTableView.setEditable(true);
        TableColumn<Room, String> roomCodeColumn = new TableColumn<>("Code");
        roomCodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        roomCodeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomCodeColumn.setOnEditCommit(event -> onRoomFieldChange(event, r->r.setCode(event.getNewValue())));

        TableColumn<Room, Integer> numberOfBedsColumn = new TableColumn<>("Number Of Beds");
        numberOfBedsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfBeds"));
        numberOfBedsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        numberOfBedsColumn.setOnEditCommit(event->onRoomFieldChange(event, r->r.setNumberOfBeds(event.getNewValue())));

        TableColumn<Room, BigDecimal> roomPriceColumn = new TableColumn<>("Room Price");
        roomPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        roomTableView.getColumns().addAll(roomCodeColumn, numberOfBedsColumn, roomPriceColumn);
    }

//    private void onRoomCodeChange(TableColumn.CellEditEvent<Room, String> event){
//        Room editedRoom = event.getRowValue();
//        editedRoom.setCode(event.getNewValue());
//        RoomServiceLocal.ROOM_SERVICE.update(editedRoom);
//    }
//
//    private void onNumberOfBedsInRoomChange(TableColumn.CellEditEvent<Room, Integer> event){
//        Room editedRow = event.getRowValue();
//        editedRow.setNumberOfBeds(event.getNewValue());
//        RoomServiceLocal.ROOM_SERVICE.update(editedRow);
//    }

    private <F> void onRoomFieldChange(TableColumn.CellEditEvent<Room, F> event, Consumer<Room> roomConsumer){
        Room editedRoom = event.getRowValue();
        roomConsumer.accept(editedRoom);
        RoomServiceLocal.ROOM_SERVICE.update(editedRoom);
    }

    private HBox getForm() {
        HBox formHBox = new HBox();
        return formHBox;
    }
}
