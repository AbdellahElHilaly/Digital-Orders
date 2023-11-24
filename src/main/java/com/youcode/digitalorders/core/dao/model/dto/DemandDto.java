package com.youcode.digitalorders.core.dao.model.dto;

import java.util.Date;
import java.util.UUID;

public class DemandDto {
    public static class Request {

        private UUID userId;
        private UUID equipmentId;
        private Date startDate;
        private Date endDate;

    }
}
