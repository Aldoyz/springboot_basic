package com.aldiichsantkj3.controller;

import com.aldiichsantkj3.mapper.FunStuffMapper;
import com.aldiichsantkj3.model.FunStuffInsertModel;
import com.aldiichsantkj3.model.FunStuffUpdateModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.UUID;

@RestController
@RefreshScope
@RequestMapping("/v1/fun-stuff")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(description = "API For Fun Stuff", tags = "FUN STUFF BABY!!!")
public class FunStuffController {

    @Autowired
    private FunStuffMapper funStuffMapper;

    @GetMapping(path = "/show-all")
    @ApiOperation("Show All Saved Fun Stuff")
    public ResponseEntity showAllFunStuff() {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status", 200);
        response.put("message", "All Saved Fun Stuff is Shown");
        response.put("total", funStuffMapper.countAllFunStuff());
        response.put("data", funStuffMapper.showAllFunStuff());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/filter-by-type={type}")
    @ApiOperation("Filter Fun Stuff By Type")
    public ResponseEntity showAllFunStuff(@ApiParam(allowableValues = "GAME, BOOK, MOVIE") @PathVariable(value = "type") String type) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status", 200);
        response.put("message", "All Saved Fun Stuff is Shown");
        response.put("total", funStuffMapper.countFilterByType(type));
        response.put("data", funStuffMapper.filterByType(type));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/insert-using-param", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Insert Some Fun Stuff Using Params")
    public ResponseEntity insertFunStuffUsingParam (
            @ApiParam(allowableValues = "GAME, BOOK, MOVIE") @RequestParam(value = "type", defaultValue = "") String type,
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "releaseDate", defaultValue = "") Date releaseDate,
            @RequestParam(value = "rating", defaultValue = "0") Integer rating
    ) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            String id = UUID.randomUUID().toString();
            System.out.println(id);
            System.out.println(type);
            System.out.println(title);
            System.out.println(releaseDate);
            System.out.println(rating);
            funStuffMapper.insertFunStuffUsingParam(id, type, title, releaseDate, rating);
            response.put("status", 200);
            response.put("message", "Fun stuff has been added successfully");
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", 400);
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping(path = "/insert-using-body", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Insert Some Fun Stuff Using Body")
    public ResponseEntity insertFunStuffUsingBody (
            @ApiParam(allowableValues = "GAME, BOOK, MOVIE") @RequestParam(value = "type", defaultValue = "") String type,
            @RequestBody FunStuffInsertModel funStuffInsertModel
    ) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            funStuffInsertModel.setId(UUID.randomUUID().toString());
            funStuffInsertModel.setType(type);
            System.out.println(funStuffInsertModel.getId());
            System.out.println(funStuffInsertModel.getType());
            System.out.println(funStuffInsertModel.getTitle());
            System.out.println(funStuffInsertModel.getReleaseDate());
            System.out.println(funStuffInsertModel.getRating());
            funStuffMapper.insertFunStuffUsingBody(funStuffInsertModel);
            response.put("status", 200);
            response.put("message", "Fun stuff has been added successfully");
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", 400);
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = "/update-using-param", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update Some Fun Stuff Using Params")
    public ResponseEntity updateFunStuffUsingParam (
            @RequestParam(value = "id", defaultValue = "") String id,
            @ApiParam(allowableValues = "GAME, BOOK, MOVIE") @RequestParam(value = "type", defaultValue = "") String type,
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "releaseDate", defaultValue = "") Date releaseDate,
            @RequestParam(value = "rating", defaultValue = "0") Integer rating
    ) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            System.out.println(id);
            System.out.println(type);
            System.out.println(title);
            System.out.println(releaseDate);
            System.out.println(rating);
            funStuffMapper.updateFunStuffUsingParam(id, type, title, releaseDate, rating);
            response.put("status", 200);
            response.put("message", "Fun stuff has been updated successfully");
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", 400);
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = "/update-using-body", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update Some Fun Stuff Using Body")
    public ResponseEntity updateFunStuffUsingBody (
            @ApiParam(allowableValues = "GAME, BOOK, MOVIE") @RequestParam(value = "type", defaultValue = "") String type,
            @RequestBody FunStuffUpdateModel funStuffUpdateModel
            ) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            funStuffUpdateModel.setType(type);
            System.out.println(funStuffUpdateModel.getId());
            System.out.println(funStuffUpdateModel.getType());
            System.out.println(funStuffUpdateModel.getTitle());
            System.out.println(funStuffUpdateModel.getReleaseDate());
            System.out.println(funStuffUpdateModel.getRating());
            funStuffMapper.updateFunStuffUsingBody(funStuffUpdateModel);
            response.put("status", 200);
            response.put("message", "Fun stuff has been updated successfully");
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", 400);
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping(path = "/delete-funstuff")
    @ApiOperation("Delete Some Fun Stuff")
    public ResponseEntity deleteFunStuff (@RequestParam(value = "id", defaultValue = "") String id) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        funStuffMapper.deleteFunStuff(id);
        response.put("status", 200);
        response.put("message", "Fun Stuff Successfully Deleted");
        return ResponseEntity.ok().body(response);
    }
}
