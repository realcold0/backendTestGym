package com.example.backendTestGym.controller.admin;

import com.example.backendTestGym.dto.DeleteEquipmentOnGymRequestDTO;
import com.example.backendTestGym.dto.GymEquipmentDTO;
import com.example.backendTestGym.dto.ManyEquipmentDTO;
import com.example.backendTestGym.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자 헬스장 기구")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminGymEquipmentController {

    private final EquipmentService equipmentService;

    @Operation(summary = "운동기구 갯수 수정")
    @ApiResponse(responseCode = "200", description = "운동기구 등록 성공")
    @PutMapping("/gym/equip")
    public ResponseEntity<GymEquipmentDTO> editQuantityEquipment(@RequestBody GymEquipmentDTO gymEquipmentDTO) {
        equipmentService.updateQuantityEquipment(gymEquipmentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(gymEquipmentDTO);
    }

    @Operation(summary = "여러 헬스장에 운동기구 등록")
    @ApiResponse(responseCode = "200", description = "모두 운동기구 등록 성공")
    @PostMapping("/gym/equip-many")
    public ResponseEntity<ManyEquipmentDTO> registerEquipToGym(@RequestBody ManyEquipmentDTO manyEquipmentDTO) {
        equipmentService.addGymEquipmentQuantityFromManyGym(manyEquipmentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(manyEquipmentDTO);
    }

    @Operation(summary = "Gym이 보유한 운동기구 삭제")
    @ApiResponse(responseCode = "200", description = "운동기구 삭제 성공")
    @DeleteMapping("/gym/equip")
    public ResponseEntity<DeleteEquipmentOnGymRequestDTO> DeleteEquipFromGym(
            @RequestBody DeleteEquipmentOnGymRequestDTO deleteEquipmentOnGymRequestDTO) {
        equipmentService.deleteEquipmentOnGym(deleteEquipmentOnGymRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(deleteEquipmentOnGymRequestDTO);
    }
}
