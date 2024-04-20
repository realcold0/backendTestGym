package com.example.backendTestGym.controller;

import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.dto.DeleteEquipmentOnGymRequestDTO;
import com.example.backendTestGym.dto.EquipmentDTO;
import com.example.backendTestGym.dto.GymDTO;
import com.example.backendTestGym.dto.GymEquipmentDTO;
import com.example.backendTestGym.dto.ManyEquipmentDTO;
import com.example.backendTestGym.dto.RentalItemDTO;
import com.example.backendTestGym.service.EquipmentService;
import com.example.backendTestGym.service.GymService;
import com.example.backendTestGym.service.RentalItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final GymService gymService;
    private final EquipmentService equipmentService;
    private final RentalItemService rentalItemService;


    @Operation(summary = "헬스장 등록")
    @ApiResponse(responseCode = "200", description = "헬스장 등록 성공")
    @PostMapping("/gym")
    public ResponseEntity<GymDTO> registerGym(@RequestBody GymDTO gymDTO) {
        Gym gym = new Gym(gymDTO.getName());
        gymService.saveGym(gym);
        return ResponseEntity.status(HttpStatus.OK).body(gymDTO);
    }

    @Operation(summary = "헬스장 정보 수정")
    @ApiResponse(responseCode = "200", description = "헬스장 정보 수정 성공")
    @PutMapping("/gym/{id}/{newName}")
    public ResponseEntity<String> editGym(@PathVariable("id") Long id,
                                          @PathVariable("newName") String newName) {
        boolean updated = gymService.updateGym(id, newName);
        if (updated) {
            return ResponseEntity.ok("헬스장 정보 수정 성공");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "헬스장 삭제")
    @ApiResponse(responseCode = "200", description = "헬스장 삭제 성공")
    @DeleteMapping("/gym/{id}")
    public ResponseEntity<String> editGym(@PathVariable("id") Long id) {
        boolean deleted = gymService.deleteGymById(id);
        if (deleted) {
            return ResponseEntity.ok("헬스장 삭제 성공");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "운동기구 등록")
    @ApiResponse(responseCode = "200", description = "운동기구 등록 성공")
    @PostMapping("/equip")
    public ResponseEntity<EquipmentDTO> registerEquipToGym(
            @RequestBody EquipmentDTO equipmentDTO) {
        equipmentService.addEquipment(equipmentDTO.getName());
        return ResponseEntity.status(HttpStatus.OK).body(equipmentDTO);
    }

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

    @Operation(summary = "기존 운동기구 삭제")
    @ApiResponse(responseCode = "200", description = "운동기구 삭제 성공")
    @DeleteMapping("/equip")
    public ResponseEntity<EquipmentDTO> DeleteEquip(
            @RequestBody EquipmentDTO equipmentDTO) {
        equipmentService.deleteEquipment(equipmentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentDTO);
    }

    @Operation(summary = "새로운 대여 물품 등록")
    @ApiResponse(responseCode = "200", description = "새로운 대여 물품 등록 성공")
    @PostMapping("/rental")
    public ResponseEntity<RentalItemDTO> registerRental(@RequestBody RentalItemDTO rentalItemDTO) {
        rentalItemService.addRentalItem(rentalItemDTO.getName());
        return ResponseEntity.status(HttpStatus.OK).body(rentalItemDTO);
    }

}
