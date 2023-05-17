package com.aldiichsantkj3.mapper;

import com.aldiichsantkj3.model.FunStuffInsertModel;
import com.aldiichsantkj3.model.FunStuffModel;
import com.aldiichsantkj3.model.FunStuffUpdateModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface FunStuffMapper {
    List<FunStuffModel> showAllFunStuff ();
    Integer countAllFunStuff ();
    List<FunStuffModel> filterByType (String type);
    Integer countFilterByType (String type);
    void insertFunStuffUsingParam (String id, String type, String title, Date releaseDate, Integer rating);
    void insertFunStuffUsingBody (FunStuffInsertModel funStuffInsertModel);
    void updateFunStuffUsingParam (String id, String type, String title, Date releaseDate, Integer rating);
    void updateFunStuffUsingBody (FunStuffUpdateModel funStuffUpdateModel);
    void deleteFunStuff (String id);
}
