package com.global.technolabs.foodorderingapp_manoj.fooddb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = Constants.TABLE_NAME_TABLE)
public class Table implements Serializable {

    //Columns
    @PrimaryKey(autoGenerate = true)
    private long table_id;

    @ColumnInfo(name = "table_name")
    private String content;

    private String status;

    public Table(String content, String status) {
        this.content = content;
        this.status = status;
    }

    public Table() {
    }

    public long getTable_id() {
        return table_id;
    }

    public void setTable_id(long table_id) {
        this.table_id = table_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        if (table_id != table.table_id) return false;
        return status != null ? status.equals(table.status) : table.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) table_id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Table{" +
                "table_id=" + table_id +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public static Table[] populateData() {
        return new Table[] {
                new Table("Table 1", "0"),
                new Table("Table 2", "1"),
                new Table("Table 3", "0"),
                new Table("Table 4", "0"),
                new Table("Table 5", "1"),
                new Table("Table 6", "0"),
                new Table("Table 7", "0"),
                new Table("Table 8", "0"),

        };
    }
}
