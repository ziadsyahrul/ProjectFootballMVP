package com.ziadsyahrul.projectfootballmvp.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ziadsyahrul.projectfootballmvp.model.TeamsItem;

import java.util.List;

@Dao
public interface FootballDao {

    @Insert
    void insertItem(TeamsItem teamsItem);

    @Query("SELECT * FROM teams WHERE idTeam = :id")
    TeamsItem selectItem(String id);

    @Delete
    void delete(TeamsItem teamsItem);

    @Query("SELECT * FROM teams ORDER BY strTeam ASC")
    List<TeamsItem> selectFacorite();

    @Query("SELECT * FROM teams WHERE strTeam = :name")
    TeamsItem selectedItemSearch(String name);
}
