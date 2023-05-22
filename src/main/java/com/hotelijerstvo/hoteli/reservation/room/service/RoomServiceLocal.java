package com.hotelijerstvo.hoteli.reservation.room.service;

import com.hotelijerstvo.hoteli.reservation.room.Room;
import com.hotelijerstvo.hoteli.service.BaseService;

public interface RoomServiceLocal  extends BaseService<Room, Integer> {
    RoomServiceLocal ROOM_SERVICE = new RoomService();
}
