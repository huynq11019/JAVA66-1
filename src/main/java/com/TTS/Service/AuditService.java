package com.TTS.Service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;


public interface AuditService <T,ID>{
     T create(T entty) throws SQLException;
    T update (T update);
    T Delete (ID delete);
    T getbyID (ID id);
}
