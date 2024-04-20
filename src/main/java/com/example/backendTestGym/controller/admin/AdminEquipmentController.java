package com.example.backendTestGym.controller.admin;

import com.example.backendTestGym.dto.EquipmentDTO;
import com.example.backendTestGym.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자 기구 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminEquipmentController {
    private final EquipmentService equipmentService;

    @Operation(summary = "운동기구 등록")
    @ApiResponse(responseCode = "200", description = "운동기구 등록 성공")
    @PostMapping("/equip")
    public ResponseEntity<EquipmentDTO> registerEquipToGym(
            @RequestBody EquipmentDTO equipmentDTO) {
        equipmentService.addEquipment(equipmentDTO.getName());
        return ResponseEntity.status(HttpStatus.OK).body(equipmentDTO);
    }


    @Operation(summary = "기존 운동기구 삭제")
    @ApiResponse(responseCode = "200", description = "운동기구 삭제 성공")
    @DeleteMapping("/equip")
    public ResponseEntity<EquipmentDTO> DeleteEquip(
            @RequestBody EquipmentDTO equipmentDTO) {
        equipmentService.deleteEquipment(equipmentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentDTO);
    }

}
