package com.aleknik.cdss.cdssservice.model.dto;

import java.util.List;

public class IdListDto {

    private List<Long> ids;

    public IdListDto() {
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
