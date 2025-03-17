package uk.ac.ucl.util;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import uk.ac.ucl.model.NoteRecord;


public class JSONHandler
{
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static String filePath = "data/noteIndex.json";
    
    public static LinkedHashMap<LocalDateTime, NoteRecord> readJSON()
    {
        LinkedHashMap<LocalDateTime, NoteRecord> noteData = new LinkedHashMap<>();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            rootNode.fields().forEachRemaining(field -> {
                LocalDateTime created = DateFormatter.stringToDate(field.getKey());
                JsonNode value = field.getValue();
                String name = value.get("name").asText();
                String body = value.get("body").asText();
                NoteRecord note = new NoteRecord(name, body);
                noteData.put(created, note);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noteData;
    }
    
    public static void writeJSON(LinkedHashMap<LocalDateTime, NoteRecord> noteData) {
        ObjectNode rootNode = objectMapper.createObjectNode();
        noteData.forEach((created, value) -> {
            String createdString = DateFormatter.dateToString(created);
            ObjectNode noteNode = objectMapper.createObjectNode();
            noteNode.put("name", value.name());
            noteNode.put("body", value.body());
            rootNode.set(createdString, noteNode);
        });
        try {
            objectMapper.writeValue(new File(filePath), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

