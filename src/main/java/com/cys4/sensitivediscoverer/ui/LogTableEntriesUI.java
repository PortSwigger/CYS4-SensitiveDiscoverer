/*
Copyright (C) 2021 CYS4 Srl
See the file 'LICENSE' for copying permission
*/
package com.cys4.sensitivediscoverer.ui;

import com.cys4.sensitivediscoverer.model.LogEntity;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LogTableEntriesUI extends AbstractTableModel {

    // get the reference of the array of entries
    private final List<LogEntity> logEntries;

    public LogTableEntriesUI(List<LogEntity> logEntries) {
        this.logEntries = logEntries;
    }

    @Override
    public int getRowCount() {
        return logEntries.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Proxy #";
            case 1 -> "URL";
            case 2 -> "Regex";
            case 3 -> "Match";
            default -> "";
        };
    }

    public String getColumnNameFormatted(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "request_id";
            case 1 -> "url";
            case 2 -> "regex";
            case 3 -> "match";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        } else {
            return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LogEntity logEntity = logEntries.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> logEntity.getIdRequest();
            case 1 -> logEntity.getURL().toString();
            case 2 -> logEntity.getDescription() + " - "  + logEntity.getRegex();
            case 3 -> logEntity.getMatch();
            default -> "";
        };
    }

    public void addNewRow(int row) {
        fireTableRowsInserted(row, row);
    }

    public void clear() {
        fireTableDataChanged();
    }
}