/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.model;

import com.penzasoft.uldbs.util.UuidPgConverter;
import javax.persistence.MappedSuperclass;
import org.eclipse.persistence.annotations.Converter;

/**
 *
 * @author ktepin
 */
@MappedSuperclass
@Converter (converterClass =  UuidPgConverter.class, name = "uuidConverter") 
public abstract class AbstractEntity {
    
}
