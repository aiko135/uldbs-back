/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.util;

import java.util.UUID;
import javax.json.bind.adapter.JsonbAdapter;


/**
 *
 * @author ktepin
 */

public class UuidJsonConverter implements JsonbAdapter<UUID, String> {
    @Override
    public String adaptToJson(UUID guid) {
        return guid == null ? null : guid.toString();
    }

    @Override
    public UUID adaptFromJson(String json) {
        return UUID.fromString(json);
    }
}
